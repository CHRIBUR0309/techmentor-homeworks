BEGIN
;

CREATE TABLE IF NOT EXISTS
    users (
        user_id TEXT
            PRIMARY KEY
        , password TEXT
            NOT NULL
    )
;

CREATE TABLE IF NOT EXISTS
    todos (
        todo_id SERIAL
            PRIMARY KEY
        , user_id TEXT
            REFERENCES
                users
            ON DELETE CASCADE
        , status TEXT
            CONSTRAINT
                check_status
            CHECK (
                status IN (
                    'Unprocessed'
                    , 'Proceeding'
                    , 'Finished'
                )
            )
        , details TEXT
            NOT NULL
            DEFAULT ''
    )
;

COMMIT
;
