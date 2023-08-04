
from typing import List

from fastapi import FastAPI, Depends, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session

from app.models import Lodging
from app.schemas import Search, LodgingBase
from sql_app.database import SessionLocal

app = FastAPI()

# CORS
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


@app.get('/searching_lodgings/',  response_model=List[LodgingBase])
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
        raise HTTPException(status_code=400, detail=f"Bad Reguest :{str(e)}", headers={'content-type':'application/json'})


@app.get("/lodging/{id}", response_model=LodgingBase)
def read_user(id: int, db: Session = Depends(get_db)):
    """
    Method to get information of Lodging from id.
    Input: int(id)
    Output: Object(Lodging)
    """
    try:
        lodging = Lodging.get_lodging(id=id, db=db)
        if lodging is None:
            raise HTTPException(status_code=404, detail="Lodging not found")
        return lodging
    
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Bad Reguest :{str(e)}", headers={'content-type':'application/json'})
    