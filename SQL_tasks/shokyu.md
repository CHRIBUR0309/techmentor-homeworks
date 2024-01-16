# DVD Rental データベースのデータを取得、挿入、更新、削除

## Q. 1

```SQL
SELECT
    title
    , release_year
FROM
    film
;
```

## Q. 2

```SQL
SELECT
    title
FROM
    film
WHERE
    rental_rate >= 4
;
```

## Q. 3

```SQL
INSERT INTO
    customer (
        store_id
        , first_name
        , last_name
        , email
        , address_id
        , active
    )
VALUES
    (
        2
        , "Hogehoge"
        , "Fugafuga"
        , "hogefuga@sakilacustomer.org"
        , 600
        , 1
    )
;
```

## Q. 4

```SQL
UPDATE
    customer
SET
    email = "updated@email.com"
WHERE
    customer_id = 5
;
```
