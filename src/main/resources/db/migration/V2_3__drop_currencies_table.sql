ALTER TABLE accounts DROP CONSTRAINT accounts_currency_code_fkey;
ALTER TABLE stocks DROP CONSTRAINT stocks_currency_code_fkey;

DROP TABLE currencies;
