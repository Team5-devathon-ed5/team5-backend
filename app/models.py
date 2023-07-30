from datetime import date
from pydantic import BaseModel, validator, confloat

class Location(BaseModel):
    latitude: confloat(ge=-90.00, le=90.00)
    longitude: confloat(ge=-180.00, le=180.00)

class Search(BaseModel):
    location: Location
    check_in: date
    check_out: date
    num_travellers: int

    @validator("check_in")
    def validate_check_in(cls, value):
        if value < date.today():
            raise ValueError("Check-in date cannot be before today.")
        return value

    @validator("check_out")
    def validate_check_out(cls, value, values):
        if not "check_in" in values:
            raise ValueError("Value Check-in is obligatory.")
        if "check_in" in values and value < values["check_in"]:
            raise ValueError("Check-out date cannot be before check-in date.")
        return value

    @validator("num_travellers")
    def validate_num_travellers(cls, value):
        if value < 1:
            raise ValueError("Number of travellers cannot be less than 1.")
        return value    