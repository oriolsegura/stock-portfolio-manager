CREATE SEQUENCE accounts_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE accounts (
    id BIGINT PRIMARY KEY DEFAULT nextval('accounts_id_seq'),
    user_id BIGINT NOT NULL,
    currency_code CHAR(3) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (currency_code) REFERENCES currencies(code) ON DELETE RESTRICT
);
