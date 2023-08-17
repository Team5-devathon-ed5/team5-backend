# Tests.
Associated test records to verify the correct functioning of the database and lodging search.

The file `test_register.sql` contains all the test records, these are located in the `app.test` directory.
# How to launch test.
* Navigate to the `app` root directory.
* Update structure in the `.env` file.
    ```
    TEST=True
    MYSQL_DATABASE_TEST=<DB_DATABASE_TEST>
    ```
* Run next command
    ```python
    pytest --pyargs ./tests -s
    ```
# Register
## Users register
|id|userActive|
|--|----------|
|1|true|
|2|true|
|3|false|
|4|true|
|5|true|
|6|false|

## Lodging register
|id|ownerId|guestCapacity|longitude|latitude|
|--|-------|-------------|---------|--------|
|  1 |   1 |           4 |  -0.123456 | 51.987654 |
|  2 |   1 |           2 | -74.006000 | 40.712800 |
|  3 |   2 |           6 |   2.345678 | 48.912345 |
|  4 |   2 |           8 |-122.987654 | 38.765432 |
|  5 |   3 |           3 |  -0.987654 | 52.123456 |
|  6 |   3 |          10 | -75.456789 | 40.567890 |
|  7 |   4 |           4 |   1.234567 | 47.890123 |
|  8 |   4 |           6 |-122.345678 | 36.789012 |
|  9 |   5 |           4 |  -0.678901 | 51.234567 |
| 10 |   6 |           8 | -74.123456 | 41.987654 |

## Reservation register
|id|lodgingId|checkIn|checkOut|hasCanceled|
|--|---------|-------|--------|-----------|
| 1 |      1 | 2223-08-15 14:00:00 | 2223-08-20 11:00:00 | false |
| 2 |      3 | 2223-09-10 15:00:00 | 2223-09-15 11:00:00 | false |
| 3 |      2 | 2223-10-05 16:00:00 | 2223-10-10 12:00:00 | false |
| 4 |      5 | 2223-11-20 14:00:00 | 2223-11-25 11:00:00 | false |
| 5 |      7 | 2223-12-05 15:00:00 | 2223-12-10 12:00:00 | false |
| 6 |      4 | 2224-01-15 14:00:00 | 2224-01-20 10:00:00 | false |
| 7 |      6 | 2224-02-10 15:00:00 | 2224-02-15 11:00:00 | false |
| 8 |      8 | 2224-03-05 16:00:00 | 2224-03-10 12:00:00 | true  |
