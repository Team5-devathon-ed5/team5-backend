from datetime import datetime, time
from sqlalchemy import func, exists, Boolean, ForeignKey, Integer, String, DECIMAL, DateTime, Time
from sqlalchemy.orm import Session, Mapped, relationship, mapped_column

from sql_app.database import Base
from app.schemas import Search

class Account(Base):
    __tablename__ = "account"

    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str] = mapped_column(String(150))
    last_name: Mapped[str] = mapped_column(String(200))
    detail: Mapped[str] = mapped_column(String(5000))
    image_url: Mapped[str] = mapped_column(String(5000))
    account_active: Mapped[bool] = mapped_column(Boolean)

    #Foreign Key:
    lodging = relationship("Lodging", back_populates="account")


class Lodging(Base):
    __tablename__ = "lodging"

    id: Mapped[int] = mapped_column(primary_key=True)
    guest_capacity: Mapped[int]
    price_per_night: Mapped[float] = mapped_column(DECIMAL(6,2))
    detail: Mapped[str] = mapped_column(String(5000))
    longitude: Mapped[float] = mapped_column(DECIMAL(9, 6))
    latitude: Mapped[float] = mapped_column(DECIMAL(8, 6))
    created_at: Mapped[datetime] = mapped_column(DateTime)
    updated_at: Mapped[datetime] = mapped_column(DateTime)
    reputation: Mapped[float] = mapped_column(DECIMAL(2, 1))
    check_in_hour: Mapped[time] = mapped_column(Time)
    check_out_hour: Mapped[time] = mapped_column(Time)
    owner_id: Mapped[int] = mapped_column(ForeignKey("account.id"))

    #Foreign Key:
    reservation = relationship("Reservation", back_populates="lodging")
    account = relationship("Account", back_populates="lodging")

    def get_list_lodging_available(search: Search, db: Session, skip: int = 0, limit: int = 100):
        reserve = Reservation.get_reservations_between_dates(search=search, db=db)
        reserve = reserve.subquery()
        lodgings = db.query(Lodging).filter(func.ST_Distance(func.POINT(Lodging.longitude, Lodging.latitude), func.POINT(search.location.longitude, search.location.latitude)) <= search.ratio) \
            .filter(Lodging.guest_capacity >= search.num_travellers) \
            .join(Account).filter(Account.account_active == True) \
            .filter(~exists().where(Reservation.lodging_id == Lodging.id).where(reserve.c.lodging_id == Lodging.id)) \
            .offset(skip).limit(limit)
        
        return lodgings

class LodgingMedia(Base):
    __tablename__ = "media"

    id: Mapped[int] = mapped_column(primary_key=True)
    lodging_id: Mapped[int] = mapped_column(ForeignKey("lodging.id"))
    file_url: Mapped[str] = mapped_column(String(5000))
    file_name: Mapped[str] = mapped_column(String(250))
    file_mime_type: Mapped[str] = mapped_column(String(50))


class Certification(Base):
    __tablename__ = "certification"

    id: Mapped[int] = mapped_column(primary_key=True)
    file_url: Mapped[str] = mapped_column(String(5000))
    file_name: Mapped[str] = mapped_column(String(250))
    file_mime_type: Mapped[str] = mapped_column(String(50))


class Reservation(Base):
    __tablename__ = "reservation"

    id: Mapped[int] = mapped_column(primary_key=True)
    lodging_id: Mapped[int] = mapped_column(ForeignKey("lodging.id"))
    check_in: Mapped[datetime] = mapped_column(DateTime)
    check_out: Mapped[datetime] = mapped_column(DateTime)
    has_canceled: Mapped[bool] = mapped_column(Boolean)
    
    lodging = relationship("Lodging", back_populates="reservation")

    def get_reservations_between_dates(search: Search, db: Session, skip: int = 0, limit: int = 100):
        reserve = db.query(Reservation.lodging_id).filter(Reservation.check_in <= search.check_out, Reservation.check_out >= search.check_in)
        
        return reserve