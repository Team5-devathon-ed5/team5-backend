import dataclasses
from datetime import datetime, time
from typing import List
from sqlalchemy import Column, Table, func, exists, Boolean, ForeignKey, String, DECIMAL, DateTime, Time, Integer
from sqlalchemy.orm import DeclarativeBase, Session, Mapped, relationship, mapped_column, composite

from ..schemas.schemas import Search


@dataclasses.dataclass
class Point:
    longitude: Mapped[float] = mapped_column("longitude", DECIMAL(9, 6))
    latitude: Mapped[float] = mapped_column("latitude", DECIMAL(8, 6))




class Base(DeclarativeBase):
    pass




#Relationship beetwen Lodging and Certification
certificationGroup = Table("certificationGroup", Base.metadata, 
                            Column("lodgingId",ForeignKey("lodging.id"), primary_key=True),
                            Column("certificationId",ForeignKey("certification.id"), primary_key=True),)




class User(Base):
    __tablename__ = "users"

    id: Mapped[int] = mapped_column("id", primary_key=True)
    name: Mapped[str] = mapped_column("name", String(350))
    detail: Mapped[str] = mapped_column("detail", String(5000))
    imageUrl: Mapped[str] = mapped_column("imageUrl", String(5000))
    userActive: Mapped[bool] = mapped_column("userActive", Boolean)

    #Foreign Key:
    lodging = relationship("Lodging", back_populates="user")




class Certification(Base):
    __tablename__ = "certification"

    id: Mapped[int] = mapped_column("id", primary_key=True)
    fileUrl: Mapped[str] = mapped_column("fileUrl", String(5000))
    fileName: Mapped[str] = mapped_column("fileName", String(250))
    fileMimeType: Mapped[str] = mapped_column("fileMimeType", String(50))
    description: Mapped[str] = mapped_column("description", String(5000))




class Extra(Base):
    __tablename__ = "extra"

    id: Mapped[int] = mapped_column("id", primary_key=True)
    lodgingId: Mapped[int] = mapped_column("lodgingId", ForeignKey("lodging.id"))
    hasWheelchairAccess: Mapped[bool] = mapped_column("hasWheelchairAccess", Boolean)
    hasKitchen: Mapped[bool] = mapped_column("hasKitchen", Boolean)
    hasInternet: Mapped[bool] = mapped_column("hasInternet", Boolean)
    hasTv: Mapped[bool] = mapped_column("hasTv", Boolean)
    hasLaundry: Mapped[bool] = mapped_column("hasLaundry", Boolean)
    hasWcAdjust: Mapped[bool] = mapped_column("hasWcAdjust", Boolean)
    hasShowerAdjust: Mapped[bool] = mapped_column("hasShowerAdjust", Boolean)
    hasParking: Mapped[bool] = mapped_column("hasParking", Boolean)
    hasElevator: Mapped[bool] = mapped_column("hasElevator", Boolean)

    #Foreign Key:
    lodging = relationship("Lodging", back_populates="extra")




class Lodging(Base):
    __tablename__ = "lodging"

    id: Mapped[int] = mapped_column("id", primary_key=True)
    guestCapacity: Mapped[int] = mapped_column("guestCapacity", Integer)
    priceNight: Mapped[float] = mapped_column("priceNight", DECIMAL(6,2))
    description: Mapped[str] = mapped_column("description", String(5000))
    location: Mapped[Point]  = composite(mapped_column("longitude", DECIMAL(9, 6)), mapped_column("latitude", DECIMAL(8, 6)))
    reputation: Mapped[float] = mapped_column("reputation", DECIMAL(2, 1))
    checkInHour: Mapped[time] = mapped_column("checkInHour", Time)
    checkOutHour: Mapped[time] = mapped_column("checkOutHour", Time)
    ownerId: Mapped[int] = mapped_column("ownerId", ForeignKey("users.id"))
    
    #Foreign Key:
    certification: Mapped[List[Certification]] = relationship(secondary=certificationGroup)
    extra = relationship("Extra", back_populates="lodging")
    reservation = relationship("Reservation", back_populates="lodging")
    user = relationship("User", back_populates="lodging")
    media = relationship("LodgingMedia", back_populates="lodging")


    def get_list_lodging_available(search: Search, db: Session, skip: int = 0, limit: int = 100):
        reserve = Reservation.get_reservations_between_dates(search=search, db=db)
        reserve = reserve.subquery()
        lodgings = db.query(Lodging).filter(func.ST_Distance(func.POINT(Lodging.longitude, Lodging.latitude), func.POINT(search.location.longitude, search.location.latitude)) <= search.ratio) \
            .filter(Lodging.guestCapacity >= search.numTravellers) \
            .join(User).filter(User.userActive == True) \
            .filter(~exists().where(Reservation.lodgingId == Lodging.id).where(reserve.c.lodgingId == Lodging.id)) \
            .offset(skip).limit(limit)
        
        return lodgings


    def get_lodging(db: Session, id: int):
        return db.query(Lodging).filter(Lodging.id == id).first()




class LodgingMedia(Base):
    __tablename__ = "media"

    id: Mapped[int] = mapped_column("id", primary_key=True)
    lodgingId: Mapped[int] = mapped_column("lodgingId", ForeignKey("lodging.id"))
    fileUrl: Mapped[str] = mapped_column("fileUrl", String(5000))
    fileName: Mapped[str] = mapped_column("fileName", String(250))
    fileMimeType: Mapped[str] = mapped_column("fileMimeType", String(50))

    #Foreign Key:
    lodging = relationship("Lodging", back_populates="media")




class Reservation(Base):
    __tablename__ = "reservation"

    id: Mapped[int] = mapped_column(primary_key=True)
    lodgingId: Mapped[int] = mapped_column("lodgingId", ForeignKey("lodging.id"))
    checkIn: Mapped[datetime] = mapped_column("checkIn", DateTime)
    checkOut: Mapped[datetime] = mapped_column("checkOut", DateTime)
    hasCanceled: Mapped[bool] = mapped_column("hasCanceled", Boolean)
    
    #Foreign Key:
    lodging = relationship("Lodging", back_populates="reservation")


    def get_reservations_between_dates(search: Search, db: Session, skip: int = 0, limit: int = 100):
        reserve = db.query(Reservation.lodgingId).filter(Reservation.checkIn <= search.checkOut, Reservation.checkOut >= search.checkIn, Reservation.hasCanceled == False)
        
        return reserve