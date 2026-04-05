CREATE SEQUENCE transactions_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY DEFAULT nextval('transactions_id_seq'),
    user_id BIGINT NOT NULL,
    stock_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    shares DECIMAL(19, 4) NOT NULL,
    price DECIMAL(19, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (stock_id) REFERENCES stocks(id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_stock_id ON transactions(stock_id);
