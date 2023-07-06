CREATE TABLE IF NOT EXISTS users (
     redmondId CHAR(20) PRIMARY KEY NOT NULL UNIQUE,
     cuil CHAR(11) NOT NULL,
     cbu CHAR(22) NOT NULL UNIQUE,
     password VARCHAR(128) NOT NULL,
     bank CHAR(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions(
    id SERIAL PRIMARY KEY NOT NULL,
    source CHAR(20) NOT NULL REFERENCES users(redmondId),
    destination CHAR(20) NOT NULL REFERENCES users(redmondId),
    amount NUMERIC(18,2) NOT NULL,
    description VARCHAR(255),
    debit_transaction_id VARCHAR(100),
    credit_transaction_id VARCHAR(100),
    status VARCHAR(20) NOT NULL
);
