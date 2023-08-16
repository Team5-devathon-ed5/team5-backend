from pydantic import BaseModel, confloat, field_validator, FieldValidationInfo, Extra
from datetime import date, time
from typing import List


class Location(BaseModel):
    longitude: confloat(ge=-180.00, le=180.00)
    latitude: confloat(ge=-90.00, le=90.00)




class Search(BaseModel):
    location: Location
    checkIn: date
    checkOut: date
    numTravellers: int
    ratio: int


    @field_validator("checkIn")
    def validate_check_in(cls, value):
        if value < date.today():
            raise ValueError("'checkIn' date cannot be before today.")
        return value


    @field_validator("checkOut")
    def validate_check_out(cls, value, info: FieldValidationInfo):     
        if not 'checkIn' in info.data.keys():
            raise ValueError("Value 'checkIn' is obligatory.")
        if value < info.data.get('checkIn'):
            raise ValueError("'checkOut' date cannot be before check-in date.")
        return value


    @field_validator("numTravellers")
    def validate_num_travellers(cls, value):
        if value < 1:
            raise ValueError("Number of travellers cannot be less than 1.")
        return value    


    @field_validator("ratio")
    def validate_ratio(cls, value):
        if value < 1:
            raise ValueError("Ratio cannot be less than 1.")
        return value   




class UserBase(BaseModel):
    id: int
    name: str
    detail: str
    imageUrl: str
    userActive: bool


    class ConfigDict:
        from_attributes = True




class LodgingMediaBase(BaseModel):
    id: int
    fileUrl: str
    fileName: str
    fileMimeType: str




class CertificationBase(BaseModel):
    id: int
    fileUrl: str
    fileName: str
    fileMimeType: str
    description: str




class ExtraBase(BaseModel):
    id: int
    hasWheelchairAccess: bool
    hasKitchen: bool
    hasInternet: bool
    hasTv: bool
    hasLaundry: bool
    hasWcAdjust: bool
    hasShowerAdjust: bool
    hasParking: bool
    hasElevator: bool


    class ConfigDict:
        from_attributes = True




class LodgingBase(BaseModel):
    id: int
    guestCapacity: int
    priceNight: float
    description: str
    location: Location 
    reputation: float
    checkInHour: time
    checkOutHour: time
    extra: List[ExtraBase] = []
    media: List[LodgingMediaBase] = []
    certification: List[CertificationBase] = []
    

    class ConfigDict:
        from_attributes = True




class LodgingCompleteBase(LodgingBase):
    user: UserBase

    class ConfigDict:
        from_attributes = True