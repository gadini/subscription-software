CREATE TABLE customer (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(150) NOT NULL,
    email           VARCHAR(150) NOT NULL,
    document        VARCHAR(20)  NOT NULL,
    status_id       INT NOT NULL,
    status_name     VARCHAR(20) NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_customer_email UNIQUE (email),
    CONSTRAINT uk_customer_document UNIQUE (document)
);