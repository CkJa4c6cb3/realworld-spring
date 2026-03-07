# Localhost 実機テストケース（User/Auth）

## 0. 事前準備

- 環境変数設定（JWT必須）
  - `export JWT_SECRET="$(openssl rand -base64 64)"`
  - `export JWT_EXPIRATION_MS=3600000`（任意）
- アプリ起動
  - `mvn spring-boot:run`
- ベースURL
  - `BASE_URL=http://localhost:8080`

---

## 1. 新規登録 `POST /users`

### 1-1. 正常登録（201）
```bash
curl -i -X POST "$BASE_URL/users" \
  -H "Content-Type: application/json" \
  -d '{
    "user": {
      "username": "test-user-01",
      "email": "test-user-01@example.com",
      "password": "TestPass123!"
    }
  }'
```
期待:
- `201 Created`
- `user.token` が空でない
- `user.email = test-user-01@example.com`

### 1-2. email重複（409）
同じemailで再登録。  
期待:
- `409 Conflict`
- `errors` を持つ `GenericErrorModel`

### 1-3. username重複（409）
emailだけ変えて同じusernameで登録。  
期待:
- `409 Conflict`

### 1-4. バリデーションエラー（422）
`user` や必須項目を欠けさせる。  
期待:
- `422 Unprocessable Entity`
- `errors.body` にメッセージ

---

## 2. ログイン `POST /users/login`

### 2-1. 正常ログイン（200）
```bash
curl -i -X POST "$BASE_URL/users/login" \
  -H "Content-Type: application/json" \
  -d '{
    "user": {
      "email": "test-user-01@example.com",
      "password": "TestPass123!"
    }
  }'
```
期待:
- `200 OK`
- `user.token` が空でない

### 2-2. パスワード誤り（401）
期待:
- `401 Unauthorized`
- `errors` 形式

### 2-3. email不存在（401）
期待:
- `401 Unauthorized`

---

## 3. 現在ユーザー取得 `GET /user`

### 3-1. 正常（200）
ログインで取得したトークンを使う:
```bash
TOKEN="<ログインで取得したJWT>"
curl -i "$BASE_URL/user" \
  -H "Authorization: Token $TOKEN"
```
期待:
- `200 OK`
- ログインユーザー情報が返る

### 3-2. ヘッダーなし（401）
期待:
- `401 Unauthorized`

### 3-3. 不正トークン（401）
`Authorization: Token invalid.token.value`  
期待:
- `401 Unauthorized`

---

## 4. ユーザー更新 `PUT /user`

### 4-1. email/username更新（200）
```bash
curl -i -X PUT "$BASE_URL/user" \
  -H "Content-Type: application/json" \
  -H "Authorization: Token $TOKEN" \
  -d '{
    "user": {
      "email": "test-user-01-updated@example.com",
      "username": "test-user-01-updated"
    }
  }'
```
期待:
- `200 OK`
- 更新後の `email/username`
- `token` が返る

### 4-2. password更新（200）
```bash
curl -i -X PUT "$BASE_URL/user" \
  -H "Content-Type: application/json" \
  -H "Authorization: Token $TOKEN" \
  -d '{
    "user": {
      "password": "NewPass123!"
    }
  }'
```
期待:
- `200 OK`

### 4-3. 更新項目なし（422）
```bash
curl -i -X PUT "$BASE_URL/user" \
  -H "Content-Type: application/json" \
  -H "Authorization: Token $TOKEN" \
  -d '{"user":{}}'
```
期待:
- `422 Unprocessable Entity`

### 4-4. email重複/username重複（409）
既存ユーザーの値に更新しようとする。  
期待:
- `409 Conflict`

### 4-5. トークンなし/不正（401）
期待:
- `401 Unauthorized`

---

## 5. パスワード更新後の再ログイン確認

### 5-1. 新パスワードでログイン成功（200）
期待:
- `200 OK`

### 5-2. 旧パスワードでログイン失敗（401）
期待:
- `401 Unauthorized`

---

## 6. 追加確認（形式）

- エラー時レスポンスが常に `GenericErrorModel` 形式か
- `Authorization` は `Bearer` ではなく `Token` 形式で通るか
- `token` が `create/login/get/put` で返るか
