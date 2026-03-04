# RealWorld Spring Backend

このプロジェクトは、[RealWorld](https://github.com/realworld-apps/realworld) の API 仕様をもとに Spring Boot でバックエンドを実装する学習用リポジトリです。API 定義はリポジトリ直下の `openapi.yml` にあり、バックエンド本体は `realworld-backend/` にあります。

## ディレクトリ構成

- `../openapi.yml`: RealWorld API の OpenAPI 定義
- `pom.xml`: Maven 設定
- `src/main/java/com/ckja/realworld/`: アプリケーション本体
- `src/main/resources/application.properties`: DB 接続などの設定
- `src/main/resources/db/migration/`: Flyway の migration ファイル

## 前提環境

- Java 21
- Maven
- Docker
- Docker Compose

## PostgreSQL コンテナの起動

PostgreSQL はリポジトリ直下の `docker-compose.yml` で管理しています。まずリポジトリルートへ移動して起動します。

```bash
cd ..
docker compose up -d
```

状態確認:

```bash
docker compose ps
```

停止:

```bash
docker compose down
```

## コンテナに入る方法

シェルに入る場合:

```bash
docker compose exec postgres sh
```

PostgreSQL に直接入る場合:

```bash
docker compose exec postgres psql -U realworld -d realworld
```

## アプリケーション起動

`realworld-backend/` で実行します。

```bash
mvn spring-boot:run
```

DB 接続設定は `src/main/resources/application.properties` にあります。環境変数を使う場合は、`DB_HOST`、`DB_PORT`、`DB_NAME`、`DB_USER`、`DB_PASSWORD` を上書きしてください。

## Flyway によるマイグレーション

Flyway はアプリケーション起動時に自動実行されます。migration ファイルは `src/main/resources/db/migration/` に配置し、次の形式で命名します。

```text
V1__create_initial_schema.sql
V2__add_profiles_table.sql
V3__create_article_indexes.sql
```

新しい migration を追加したら、PostgreSQL コンテナを起動した状態でアプリケーションを再起動してください。

```bash
mvn spring-boot:run
```

適用状況を DB で確認する場合:

```bash
docker compose exec postgres psql -U realworld -d realworld
```

```sql
SELECT installed_rank, version, description, success
FROM flyway_schema_history
ORDER BY installed_rank;
```

## テスト

```bash
mvn test
```

## 補足

現在の初期スキーマは `V1__create_initial_schema.sql` で作成され、`users`、`articles`、`comments`、`tags` と、それらをつなぐ中間テーブルを含みます。
