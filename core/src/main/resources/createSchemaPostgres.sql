
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    user_id                BIGSERIAL             NOT NULL PRIMARY KEY,
    username               VARCHAR(100)          NOT NULL UNIQUE,
    email                  VARCHAR(100)          NULL,
    email_confirmed        BOOLEAN               NOT NULL,
    password_hash          VARCHAR(200)          NULL,
    security_stamp         VARCHAR(100)          NULL,
    phone_number           VARCHAR(25)           NULL,
    phone_number_confirmed BOOLEAN               NOT NULL,
    lockout_end_date_utc   TIMESTAMP             NULL,
    lockout_enabled        BOOLEAN               NOT NULL,
    access_failed_count    INT                   NOT NULL
);

