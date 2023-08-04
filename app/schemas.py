from datetime import date, datetime, time
from typing import List
from pydantic import BaseModel, validator, confloat

class Location(BaseModel):
    latitude: confloat(ge=-90.00, le=90.00)
    longitude: confloat(ge=-180.00, le=180.00)


class Search(BaseModel):
    location: Location
    check_in: date
    check_out: date
    num_travellers: int
    ratio: int

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

    @validator("ratio")
    def validate_ratio(cls, value):
        if value < 1:
            raise ValueError("Ratio cannot be less than 1.")
        return value   

class Account(BaseModel):
    id: int
    name: str
    last_name: str
    image_url: str
    detail: str

    class Config:
        from_attributes = True




class LodgingMediaBase(BaseModel):
    id: int
    file_url: str
    file_name: str
    file_mime_type: str




class CertificationBase(BaseModel):
    id: int
    file_url: str
    file_name: str
    file_mime_type: str



class ExtraBase(BaseModel):
    has_wheelchair_access: bool
    has_kitchen: bool
    has_internet: bool
    has_tv: bool
    has_laundry: bool
    has_wc_adjust: bool
    has_shower_adjust: bool


class LodgingBase(BaseModel):
    id: int
    guest_capacity: int
    price_per_night: float
    detail: str
    latitude: confloat(ge=-90.00, le=90.00)
    longitude: confloat(ge=-180.00, le=180.00)
    created_at: datetime
    updated_at: datetime
    reputation: float
    check_in_hour: time
    check_out_hour: time
    owner_id: int
    account: Account
    extra: List[ExtraBase] = []
    media: List[LodgingMediaBase] = []
    certification: List[CertificationBase] = []
    

    class Config:
        from_attributes = True


