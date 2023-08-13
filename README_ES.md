# team5-backend
## Feature/search

Microservicio para la busqueda de alojamientos.

## Requisitos de instalación.
### Python
* Instalar Python versión 3.8 o superior, 3.8.10 como versión preferida.
* Instalar todas las dependencias de Python necesarias incluidas en `requirements.txt`
#### Instalación de dependencias.
* Windows:
```python
python -m pip install -r requirements.txt
```
* Unix/MacOS:
```python
python3 -m pip install -r requirements.txt 
```
### Archivos.
* Posicionarse en el directorio raiz `app`
* Crear un nuevo fichero llamado `.env`
* Añadir dentro del fihcero el siguiente contenido.
    ```
    MYSQL_USER = "<DB_USER>"
    MYSQL_PASSWORD = "<DB_PASSWORD>"
    MYSQL_HOST = "<DB_HOST>"
    MYSQL_PORT = "<DB_PORT>"
    MYSQL_DATABASE = "<DB_DATABASE>"
    ```
## Despliegue en desarrollo.
### Como lanzar la aplicación.
* Posicionarse en el directorio donde se aloje el archivo `main.py` y lanzar el siguiente comando en la terminal.
### Inicialización del servicor con uvicorn.
```python
uvicorn main:app --reload
```
### Inicializar el servicio con docker.
#### Creación de imagen.
```docker
docker build -t "mi-image" .
```
#### Lanzamiendo de imagen.
* Windows:
```docker
docker run -it -p "port":"port" -v %cd%:/app "mi-image"
```
* Unix:
```docker
docker run -it -p "port":"port" -v $PWD:/app "mi-image"
```

## Endpoints
### Lodging
Método para obtener toda la información de Lodging por parámetro ID.
* GET /lodging/{id}"

Método para obtener toda la información de Lodging disponibles en función de los parámetros de entrada del schema Search.
* POST /searchlodging/

### Documentación
Documentación de la API en formato Swagger/OpenAPI
* /docs