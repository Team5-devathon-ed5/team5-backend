
from datetime import datetime, timedelta
from faker import Faker
from sqlalchemy import create_engine
from sqlalchemy.orm import Session, sessionmaker
from sqlalchemy.pool import StaticPool


from api.sql.database import Base, SQLALCHEMY_DATABASE_URL
from api.models.models import Account, Lodging, Reservation

#Test connection
engine = create_engine(
    SQLALCHEMY_DATABASE_URL,
    poolclass=StaticPool,
)
TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base.metadata.create_all(bind=engine)



fake = Faker()

def create_test_account(db: Session, account_active: bool = True):
    #Create and add account in database.
    account = Account(name=fake.name(),
                      last_name=fake.last_name(),
                      detail=fake.sentence(),
                      image_url=fake.image_url(),
                      account_active=account_active)
    db.add(account)
    db.commit()
    db.refresh(account)
    return account.id, account.account_active


def create_test_lodging(db: Session, owner_id: int):
    #Create and add lodging in database.
    lodging = Lodging(detail=fake.sentence(), 
                      longitude=fake.pyfloat(left_digits=3, right_digits=3, positive=False, min_value=-180.00, max_value=180.00), 
                      latitude=fake.pyfloat(left_digits=3, right_digits=3, positive=False, min_value=-90.00, max_value=90.00),
                      guest_capacity=fake.pyint(min_value=1, max_value=4),
                      price_per_night=fake.pyfloat(left_digits=2, right_digits=2, positive=True, min_value=10.00, max_value=90.00), 
                      created_at=datetime.now(), 
                      updated_at=datetime.now(),
                      check_in_hour="13:00", 
                      check_out_hour="11:00", 
                      owner_id=owner_id)
    db.add(lodging)
    db.commit()
    db.refresh(lodging)
    return lodging.id, lodging.owner_id, lodging.longitude, lodging.latitude


def create_test_reservation(db: Session, lodging_id:int, has_canceled: bool = False):
    #Create and add reservation in database.
    check_in_today = fake.date_this_year(before_today=False, after_today=False).isoformat()
    check_out = fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat()
        
    reservation = Reservation(lodging_id=lodging_id, 
                              check_in=check_in_today, 
                              check_out=check_out, 
                              has_canceled=has_canceled)
    db.add(reservation)
    db.commit()
    db.refresh(reservation)
    return reservation.id