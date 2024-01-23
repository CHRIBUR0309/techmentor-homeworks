# DVD Rental データベースを使ったサブクエリ、トランザクション

## Q. 1

```SQL
SELECT
    film.title
FROM
    rental
    INNER JOIN
        inventory
    ON
        rental.inventory_id = inventory.inventory_id
        AND
        CAST(rental.rental_date AS DATE) = (
            SELECT
                MAX(CAST(rental_date AS DATE))
            FROM
                rental
            GROUP BY
                CAST(rental_date AS DATE)
            LIMIT
                1
        )
    INNER JOIN
        film
    ON
        inventory.film_id = film.film_id
;
```

## Q. 2

前払い、途中払い、後払いのいずれも可とすると、返却プロセスを作り、前払い＝レンタル＋支払い、後払い＝返却＋支払いのストアドプロシージャを作るほうが良さそうですね。

```SQL
BEGIN
;

INSERT INTO
    rental (
        rental_date
        , inventory_id
        , customer_id
        , return_date
        , staff_id
    )
VALUES
    (
        NOW()
        , 1
        , 1
        , NULL
        , 1
    )
;

ROLLBACK
;

BEGIN
;

INSERT INTO
    payment (
        customer_id
        , staff_id
        , rental_id
        , amount
        , payment_date
    )
VALUES
    (
        (
            SELECT
                customer_id
            FROM
                rental
            WHERE
                rental_id = 1
        )
        , 1
        , 1
        , 100
        , NOW()
    )
;

ROLLBACK
;
```
