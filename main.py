from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from starlette.requests import Request

from app.models import Search
from sql_app.database import SessionLocal, text

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins = ['*'],
    allow_methods = ['*'],
    allow_headers = ['*']
)

@app.get('/search/')
def search_accommodation(request: Request, search : Search):
    """
    """
    try:
        db = SessionLocal()
        query = text("""SELECT id, ST_Distance(POINT(longitude, latitude), POINT(:longitude_ref, :latitude_ref)) AS ratio FROM lodging 
                     WHERE ST_Distance(POINT(longitude, latitude), POINT(:longitude_ref, :latitude_ref)) <= :max_ratio
                     ORDER BY ratio""")
        result = db.execute(query, {'longitude_ref':search.location.longitude,'latitude_ref':search.location.latitude, 'max_ratio':25}).fetchall()

    except Exception as e:
        raise Exception("Error, we can't connect with database.", str(e))

    finally:
        db.close()

    return {'message':'Hello world'}