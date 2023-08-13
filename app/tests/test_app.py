import pytest, httpx
from fastapi import Depends
from fastapi.testclient import TestClient

from ..main import app, get_db
from .test_sql import text, TestingSessionLocal, Session, create_test_account, create_test_lodging, create_test_reservation


def override_get_db():
    
    try:
        db = TestingSessionLocal()
        db.begin()
        yield db
    except Exception as e:
        print('DB connection error:', e)
    finally:
        db.rollback()
        db.close()

app.dependency_overrides[get_db] = override_get_db
client = TestClient(app)

def test_create_data(db: Session):
    """
    Method for generating test data and properly verifying the service's functionality.
    Generate:
        2 Account(1 active and 1 disable)
        6 Lodging(3 Available and 3 with Reservation)
        3 reservation(2 complete and 1 cancelled)
    """
    data_test = {'account':[], 'lodging':[], 'reservation': []}
    id_account, account_active = create_test_account(db=db, account_active=False)
    data_test['account'].append({'id_account': id_account, 'account_active': account_active})
    id_account, account_active = create_test_account(db=db, account_active=True)
    data_test['account'].append({'id_account': id_account, 'account_active': account_active})
    num_lodging = 0
    print('data1',data_test)
    while num_lodging < 6:
        num_lodging += 1
        print(num_lodging)
        if num_lodging == 1 or num_lodging == 2 or num_lodging == 3:
            id_lodging, owner_id, longitude, latitude = create_test_lodging(db=db, owner_id=id_account)
            data_test['lodging'].append({'id_lodging':id_lodging, 'owner_id':owner_id, 'longitude':longitude, 'latitude':latitude })
        elif num_lodging == 4 or num_lodging == 5:
            id_lodging, owner_id, longitude, latitude = create_test_lodging(db=db, owner_id=id_account)
            data_test['lodging'].append({'id_lodging':id_lodging, 'owner_id':owner_id, 'longitude':longitude, 'latitude':latitude })
        elif num_lodging == 6:
            id_lodging, owner_id, longitude, latitude = create_test_lodging(db=db, owner_id=id_account)
            data_test['lodging'].append({'id_lodging':id_lodging, 'owner_id':owner_id, 'longitude':longitude, 'latitude':latitude })
        else:
            id_lodging, owner_id, longitude, latitude = create_test_lodging(db=db, owner_id=id_account)
            data_test['lodging'].append({'id_lodging':id_lodging, 'owner_id':owner_id, 'longitude':longitude, 'latitude':latitude })
    print('data2',data_test)
        


def test_post_items(db: Session = Depends(override_get_db)):

    # We grab another session to check 
    # if the items are created
    #db = override_get_db() 
    
    test_create_data(db=db)
    #client.post("/items/", json={"title": "Item 1"})

    #client.post("/items/", json={"title": "Item 2"})

    #items = crud.get_items(db)
    #assert len(items) == 2


    #data = {
    #    "location":{
    #        "longitude":12.05,
    #        "latitude":51.20
    #    },
    #    "check_in" : "2024-02-21",
    #    "check_out" : "2024-02-23",
    #    "num_travellers": 5,
    #    "ratio" : 100
    #}

test_post_items()