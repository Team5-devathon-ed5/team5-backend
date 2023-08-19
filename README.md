# team5-backend
## Feature/search
Microservice for accommodations search.

## Prerequisites.
### Python.
* Install Python versión 3.8 or latest version. Python 3.8.10 is preferred version
* Install required Python dependencies in `requirements.txt`.
##### How to install dependencies.
* Windows:
```bash
python -m pip install -r requirements.txt
```
* Unix/MacOS:
```bash
python3 -m pip install -r requirements.txt 
```
### Files.
* Navigate to the `app` root directory.
* Create a new file named `.env`.
* Add the next structure to the file.
    ```
    MYSQL_USER=<DB_USER>
    MYSQL_PASSWORD=<DB_PASSWORD>
    MYSQL_HOST=<DB_HOST>
    MYSQL_PORT=<DB_PORT>
    MYSQL_DATABASE=<DB_DATABASE>
    TEST=False
    MYSQL_DATABASE_TEST=<DB_DATABASE_TEST>
    ```
* Set test to False for this deploy
## Deployment for development.
### How to Run the Application.
* Navigate to the directory that has the file `main.py` and launch next command in terminal.
### Launch server with uvicorn.
```bash
uvicorn main:app --reload
```
### Launch with docker-compose.
#### Modify Template.
It's necessary to modify the `env.template` template located in the `root` directory, leaving the `MYSQL_HOST` variable untouched.
    ```
    MYSQL_USER=<DB_USER>
    MYSQL_PASSWORD=<DB_PASSWORDT>
    MYSQL_HOST=host.docker.internal
    MYSQL_PORT=<DB_PORT>
    MYSQL_DATABASE=<DB_DATABASE>
    TEST=<BOOL_TEST>
    MYSQL_DATABASE_TEST=<DB_TEST>
    ```
* Set test to False for this deploy
#### Launch service.
```bash
docker-compose up -d --build
```

## Endpoints
### Lodging
Method to retrieve all the information of a Lodging using the parameter ID.
* GET /lodging/{id}

Method to retrieve all the information of available Lodging based on the input parameters of the Search schema.
* POST /searchlodging/

### Documentation
API documentation in Swagger/OpenAPI format.
* /docs