# team5-backend
## Feature/search
Microservicio para la búsqueda de alojamientos.

## Requisitos de instalación.
### Python
* Instalar Python versión 3.8 o superior, 3.8.10 como versión preferida.
* Instalar todas las dependencias de Python necesarias incluidas en `requirements.txt`
#### Instalación de dependencias.
* Windows:
```bash
python -m pip install -r requirements.txt
```
* Unix/MacOS:
```bash
python3 -m pip install -r requirements.txt 
```
### Archivos.
* Posicionarse en el directorio raíz `app`
* Crear un nuevo fichero llamado `.env`
* Añadir dentro del fihcero el siguiente contenido.
    ```
    MYSQL_USER=<DB_USER>
    MYSQL_PASSWORD=<DB_PASSWORD>
    MYSQL_HOST=<DB_HOST>
    MYSQL_PORT=<DB_PORT>
    MYSQL_DATABASE=<DB_DATABASE>
    TEST=False
    MYSQL_DATABASE_TEST=<DB_DATABASE_TEST>
    ```
* Aplica False a TEST si se realiza este tipo de despliegue
## Despliegue en desarrollo.
### Como lanzar la aplicación.
* Posicionarse en el directorio donde se aloje el archivo `main.py` y lanzar el siguiente comando en la terminal.
### Inicialización del servicor con uvicorn.
```bash
uvicorn main:app --reload
```
### Inicializar el servicio con docker-compose.
#### Modificar plantilla.
Es necesario modificar la plantilla `env.template` situada en el directorio `raíz`, dejando la variable `MYSQL_HOST`, intacta.
    ```
    MYSQL_USER=<DB_USER>
    MYSQL_PASSWORD=<DB_PASSWORDT>
    MYSQL_HOST=host.docker.internal
    MYSQL_PORT=<DB_PORT>
    MYSQL_DATABASE=<DB_DATABASE>
    TEST=<BOOL_TEST>
    MYSQL_DATABASE_TEST=<DB_TEST>
    ```
Aplica False a TEST si se realiza este tipo de despliegue
#### Iniciar servicio.
```bash
docker-compose up -d --build
```


## Endpoints
### Lodging
Método para obtener toda la información de la tabla Lodging por parámetro ID.
* GET /lodging/{id}"

Método para obtener toda la información de la tabla Lodging y que se encuentren disponibles en función de los parámetros de entrada del schema Search.
* POST /searchlodging/

### Documentación
Documentación de la API en formato Swagger/OpenAPI
* /docs