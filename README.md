# team5-backend
## Feature/search
Microservice for accommodations search.

## Prerequisites.
### Python.
* Install Python versión 3.8 or latest version. Python 3.8.10 is preferred version
* Install required Python dependencies in `requirements.txt`.
##### How to install dependencies.
* Windows:
```python
python -m pip install -r requirements.txt
```
* Unix/MacOS:
```python
python3 -m pip install -r requirements.txt 
```
### Files.
* Navigate to the `sql_app` directory.
* Create a new file named `.env`.
* Add the next structure to the file.
    ```
    DB_ROOT = "<DB_ROOT>"
    DB_PASSWORD = "<DB_PASSWORD>"
    DB_HOST = "<DB_HOST>"
    DB_PORT = "<DB_PORT>"
    DB_DATABASE = "<DB_DATABASE>"
    ```
## Deployment for development.
### How to Run the Application.
* Navigate to the directory that has the file `main.py` and launch next command in terminal.
### Launch server with uvicorn.
* uvicorn main:app --reload

