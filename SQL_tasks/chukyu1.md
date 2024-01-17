# DVD Rental データベースを使った JOIN、集計とグルーピング

## Q. 1

```SQL
SELECT
    title
FROM
    film
;

SELECT
    film.title
    , address.address
FROM
    film
    INNER JOIN
        inventory
    ON
        film.film_id = inventory.film_id
        AND
        film.title = 'Academy Dinosaur'
    INNER JOIN
        store
    ON
        inventory.store_id = store.store_id
    INNER JOIN
        address
    ON
        store.address_id = address.address_id
;
```

## Q. 2

### ワンライナー

```SQL
SELECT
    customer.customer_id
    , payment_sub.s AS sum_amount
    , rental_sub.c AS count_customer_rental
FROM
    customer
    INNER JOIN
        (
            SELECT
                customer_id
                , SUM(amount) AS s
            FROM
                payment
            GROUP BY
                customer_id
        ) AS payment_sub
    ON
        customer.customer_id = payment_sub.customer_id
    INNER JOIN
        (
            SELECT
                customer_id
                , COUNT(customer_id) AS c
            FROM
                rental
            GROUP BY
                customer_id
        ) AS rental_sub
    ON
        customer.customer_id = rental_sub.customer_id
ORDER BY
    sum_amount DESC
    , count_customer_rental DESC
LIMIT
    5
;
```

### 別解（順次処理）

```SQL
CREATE VIEW
    customer_rental AS SELECT
        customer.customer_id AS customer_id
        , COUNT(rental.customer_id) AS count_customer_rental
    FROM
        customer
        INNER JOIN
            rental
        ON
            customer.customer_id = rental.customer_id
    GROUP BY
        customer.customer_id
        , rental.customer_id
;

CREATE VIEW
    cr_payment AS SELECT
    customer_rental.customer_id AS customer_id
    , SUM(payment.amount) AS sum_amount
    , customer_rental.count_customer_rental AS count_customer_rental
    FROM
        customer_rental
        INNER JOIN
        payment
        ON
            customer_rental.customer_id = payment.customer_id
    GROUP BY
        customer_rental.customer_id
        , payment.customer_id
        , customer_rental.count_customer_rental
;

SELECT
    *
FROM
    cr_payment
ORDER BY
    sum_amount DESC
    , count_customer_rental DESC
LIMIT
    5
;

DROP VIEW
    cr_payment
;

DROP VIEW
    customer_rental
;
```
