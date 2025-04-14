-- liquibase formatted sql

-- changeset author:init_schema id:2
CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(20)         NOT NULL
);

-- changeset author:init_schema id:3
CREATE TABLE cards
(
    id         UUID PRIMARY KEY,
    owner_id   UUID                NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    amount     DECIMAL(19, 2)      NOT NULL,
    currency   VARCHAR(3),
    number     VARCHAR(255) UNIQUE NOT NULL,
    issue_date DATE                NOT NULL,
    cvv        VARCHAR(10)         NOT NULL,
    status     VARCHAR(20)         NOT NULL,
    type       VARCHAR(20),
    created_at TIMESTAMP           NOT NULL,
    updated_at TIMESTAMP           NOT NULL,
    CONSTRAINT fk_card_owner FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- changeset author:init_schema id:4
CREATE TABLE limits
(
    id          UUID PRIMARY KEY,
    amount      DECIMAL(19, 2) NOT NULL,
    used_amount DECIMAL(19, 2) NOT NULL DEFAULT 0,
    card_id     UUID           NOT NULL,
    type        VARCHAR(20)    NOT NULL,
    CONSTRAINT fk_limit_card FOREIGN KEY (card_id) REFERENCES cards (id)
);

-- changeset author:init_schema id:5
CREATE TABLE transactions
(
    id             UUID PRIMARY KEY,
    amount         DECIMAL(19, 2),
    source_card_id UUID,
    target_card_id UUID,
    description    TEXT,
    status         VARCHAR(20) NOT NULL,
    currency       VARCHAR(3),
    created        TIMESTAMP,
    updated        TIMESTAMP,
    type           VARCHAR(20) NOT NULL,
    CONSTRAINT fk_transaction_source FOREIGN KEY (source_card_id) REFERENCES cards (id),
    CONSTRAINT fk_transaction_target FOREIGN KEY (target_card_id) REFERENCES cards (id)
);

-- changeset author:init_schema id:6
CREATE INDEX idx_cards_owner_id ON cards (owner_id);
CREATE INDEX idx_limits_card_id ON limits (card_id);
CREATE INDEX idx_transactions_source_card ON transactions (source_card_id);
CREATE INDEX idx_transactions_target_card ON transactions (target_card_id);
CREATE INDEX idx_transactions_created ON transactions (created);