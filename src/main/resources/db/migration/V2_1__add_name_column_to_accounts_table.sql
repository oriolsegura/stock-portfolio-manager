ALTER TABLE accounts ADD COLUMN name VARCHAR(63) NOT NULL;

ALTER TABLE accounts ADD CONSTRAINT unique_accounts_user_name UNIQUE (user_id, name);
