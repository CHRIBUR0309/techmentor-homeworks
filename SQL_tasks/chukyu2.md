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

```SQL
BEGIN
;


;

ROLLBACK
;
```
