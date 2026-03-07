INSERT INTO users (email, username, password, bio, demo)
VALUES
    ('demo@example.com', 'demo-user', '$2a$10$demo.demo.demo.demo.demo.demo.demo.demo.demo.demo', 'Demo user for local development', TRUE),
    ('alice@example.com', 'alice', '$2a$10$demo.demo.demo.demo.demo.demo.demo.demo.demo.demo', 'Alice writes about Spring Boot', TRUE),
    ('bob@example.com', 'bob', '$2a$10$demo.demo.demo.demo.demo.demo.demo.demo.demo.demo', 'Bob likes practical backend design', TRUE)
ON CONFLICT (email) DO NOTHING;

INSERT INTO tags (name)
VALUES
    ('java'),
    ('spring'),
    ('postgresql'),
    ('jdbc'),
    ('realworld')
ON CONFLICT (name) DO NOTHING;

INSERT INTO articles (slug, title, description, body, author_id)
SELECT
    'spring-realworld-demo',
    'Spring RealWorld Demo',
    'Seed article for local API checks',
    'This article exists so the local environment has something to query.',
    u.id
FROM users u
WHERE u.email = 'alice@example.com'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO article_tags (article_id, tag_id)
SELECT a.id, t.id
FROM articles a
JOIN tags t ON t.name IN ('java', 'spring', 'realworld')
WHERE a.slug = 'spring-realworld-demo'
ON CONFLICT DO NOTHING;

INSERT INTO comments (body, article_id, author_id)
SELECT
    'This is a seeded comment for local development.',
    a.id,
    u.id
FROM articles a
JOIN users u ON u.email = 'bob@example.com'
WHERE a.slug = 'spring-realworld-demo'
ON CONFLICT DO NOTHING;
