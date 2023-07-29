from datetime import date
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from starlette.requests import Request
from pydantic import BaseModel, validator, confloat

class Location(BaseModel):
    latitude: confloat(ge=-90.00, le=90.00)
    longitude: confloat(ge=-180.00, le=180.00)

class Search(BaseModel):
    location: Location
    check_in: date
    check_out: date
    num_travellers: int

    @validator("check_out")
    def check_dates(cls, value, values):
        if "check_in" in values and value < values["check_in"]:
            raise ValueError("Check-out date cannot be before check-in date.")
        return value

    @validator("num_travellers")
    def check_num_travellers(cls, value):
        if value < 1:
            raise ValueError("Number of travellers cannot be less than 1.")
        return value    


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
    print(search)
    return {'message':'Hello world'}