from typing import List
from dotenv import dotenv_values

from fastapi import FastAPI, Depends, HTTPException, APIRouter
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session

env = dotenv_values(".env")  
if env.get('TEST') == "True":
    from .api.models.models import Lodging
    from .api.schemas.schemas import Search, LodgingBase
    from .api.sql.database import SessionLocal
else:
    from api.models.models import Lodging
    from api.schemas.schemas import Search, LodgingBase
    from api.sql.database import SessionLocal


app = FastAPI()
router = APIRouter()

# CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins = ['*'],
    allow_methods = ['*'],
    allow_headers = ['*']
)

# Dependency DB
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@router.post("/searchlodging/",  response_model=List[LodgingBase], tags=["searchlodging"])
def search_lodgings_available(search : Search, db: Session = Depends(get_db)):
    """
    Method to query all available lodgings based on the parameters set in Search.
    Input: Object(Search)
    Output: List[Objets(Lodging)]
    """
    try:
        lodgings = Lodging.get_list_lodging_available(search=search, db=db, skip=0, limit=5)
        if lodgings.count() < 1:
            raise HTTPException(status_code=404, detail="Lodgings not found")
        return lodgings
    
    except HTTPException as e:
        raise HTTPException(status_code=e.status_code, detail=e.detail, headers={'content-type':'application/json'})
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Bad Reguest :{str(e)}", headers={'content-type':'application/json'})


@router.get("/lodging/{id}", response_model=LodgingBase, tags=["lodging"])
def get_lodging(id: int, db: Session = Depends(get_db)):
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
    
    except HTTPException as e:
        raise HTTPException(status_code=e.status_code, detail=e.detail, headers={'content-type':'application/json'})
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Bad Reguest :{str(e)}", headers={'content-type':'application/json'})


app.include_router(router)