BEGIN
;

CREATE TABLE IF NOT EXISTS todo_items (
    todo_id serial PRIMARY key,
    title TEXT NOT NULL,
    status TEXT CONSTRAINT status_check CHECK (
        status IN (
            'Unprocessed',
            'Proceeding',
            'Finished'
        )
    ),
    details TEXT NOT NULL DEFAULT ''
)
;

COMMIT
;
