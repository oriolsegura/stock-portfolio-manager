CREATE SEQUENCE stocks_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE stocks (
    id BIGINT PRIMARY KEY DEFAULT nextval('stocks_id_seq'),
    company_name VARCHAR(255) NOT NULL UNIQUE,
    ticker_symbol VARCHAR(7) NOT NULL UNIQUE,
    currency_code CHAR(3) NOT NULL,
    price DECIMAL(19, 4),
    price_updated_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (currency_code) REFERENCES currencies(code) ON DELETE RESTRICT
);

INSERT INTO stocks (company_name, ticker_symbol, currency_code) VALUES
('Apple Inc.', 'AAPL', 'USD'),
('Microsoft Corporation', 'MSFT', 'USD'),
('Amazon.com, Inc.', 'AMZN', 'USD'),
('Alphabet Inc.', 'GOOGL', 'USD'),
('JPMorgan Chase & Co.', 'JPM', 'USD'),
('Johnson & Johnson', 'JNJ', 'USD'),
('Visa Inc.', 'V', 'USD'),
('NVIDIA Corporation', 'NVDA', 'USD');
