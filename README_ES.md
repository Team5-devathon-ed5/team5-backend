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
* Posicionarse en el directorio `api\sql`
* Crear un nuevo fichero llamado `.env`
* Añadir dentro del fihcero el siguiente contenido.
    ```
    DB_ROOT = "<DB_ROOT>"
    DB_PASSWORD = "<DB_PASSWORD>"
    DB_HOST = "<DB_HOST>"
    DB_PORT = "<DB_PORT>"
    DB_DATABASE = "<DB_DATABASE>"
    ```
## Despliegue en desarrollo.
### Como lanzar la aplicación.
* Posicionarse en el directorio donde se aloje el archivo `main.py` y lanzar el siguiente comando en la terminal.
### Inicialización del servicor con uvicorn.
```python
    * uvicorn main:app --reload
```