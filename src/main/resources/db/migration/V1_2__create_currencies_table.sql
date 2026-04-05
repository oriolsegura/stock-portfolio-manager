CREATE TABLE currencies (
    code CHAR(3) PRIMARY KEY,
    name VARCHAR(31) NOT NULL,
    symbol VARCHAR(7) NOT NULL
);

INSERT INTO currencies (code, name, symbol) VALUES
('USD', 'United States Dollar', '$'),
('EUR', 'Euro', '€'),
('JPY', 'Japanese Yen', '¥'),
('GBP', 'British Pound Sterling', '£'),
('AUD', 'Australian Dollar', 'A$'),
('CAD', 'Canadian Dollar', 'C$'),
('CHF', 'Swiss Franc', 'CHF'),
('CNY', 'Chinese Yuan', '¥'),
('SEK', 'Swedish Krona', 'kr'),
('NZD', 'New Zealand Dollar', 'NZ$');
