CREATE SEQUENCE cash_movements_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE cash_movements (
    id BIGINT PRIMARY KEY DEFAULT nextval('cash_movements_id_seq'),
    account_id BIGINT NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE INDEX idx_cash_movements_account_id ON cash_movements(account_id);
