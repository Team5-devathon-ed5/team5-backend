
from typing import List

from fastapi import FastAPI, Depends, Response
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session

from app.models import Lodging
from app.schemas import Search, LodgingBase
from sql_app.database import SessionLocal

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins = ['*'],
    allow_methods = ['*'],
    allow_headers = ['*']
)

# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@app.get('/search/',  response_model=List[LodgingBase])
def search_accommodation(search : Search, db: Session = Depends(get_db)):
    """
    Method to query all available lodgings based on the parameters set in Search.
    Input: Object(Search)
    Output: List[Objets(Lodging)]
    """
    try:
        results = Lodging.get_list_lodging_available(search=search, db=db, skip=0, limit=5)
        return results
    except Exception as e:
        raise Response(content=f'Error, {e}', status_code=400)