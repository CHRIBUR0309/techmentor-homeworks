BEGIN
    ; CREATE TABLE IF NOT EXISTS TODO_ITEMS ( TODO_ID TEXT PRIMARY KEY, TITLE TEXT NOT NULL, STATUS TEXT CONSTRAINT STATUS_CHECK CHECK ( STATUS IN ( 'Unprocessed', 'Proceeding', 'Finished' ) ), DETAILS TEXT NOT NULL DEFAULT '' );
    COMMIT;
