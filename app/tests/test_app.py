import pytest, httpx
from faker import Faker
from unittest.mock import patch

from sqlalchemy.orm import Session
from fastapi.testclient import TestClient

from ..main import app, get_db
from ..api.schemas.schemas import Search
from ..api.models.models import Lodging
from .test_sql import TestingSessionLocal

fake = Faker()


def override_get_db():
    try:
        db = TestingSessionLocal()
        db.begin()
        yield db
    finally:
        db.rollback()
        db.close()


client = TestClient(app)
app.dependency_overrides[get_db] = override_get_db




#Test for validate get_lodging method in app.
def test_get_lodging():
    #Case 1 (Correct Test): Successful request
    id = 1
    response = client.get(f"/lodging/{id}")
    assert response.status_code == 200,  f'Case 1: Incorrect. ERROR: \n {response.json()}'
    assert response.json()['id'] == id,  f'Case 1: Incorrect. ERROR: \n {response.json()}'


    #Case 2 (Incorrect Test): Object not found
    id = 999
    response = client.get(f"/lodging/{id}")
    assert response.status_code == 404,  f'Case 2: Incorrect. ERROR: \n {response.json()}'


    #Case 3 (Incorrect Test): Bad paramenter in url.
    response = client.get("/lodging/a")
    assert response.status_code == 422,  f'Case 3: Incorrect. ERROR: \n {response.json()}'




#Test for validate search_lodging_available method in app.
def test_search_lodgings_available():
    urn = "/searchlodging/"
    invalid_lodging = [5, 6, 10] #Invalid lodgings because owner is not active.
    

    #Case 1 (Correct Test): Successful request
    search_data = {
        "location": {"longitude": 10.0, "latitude": 20.0},
        "ratio": 100,
        "numTravellers": 2,
        "checkIn": "2223-08-15",
        "checkOut": "2223-08-15"
    }
    response = client.post(urn, json=search_data)
    assert response.status_code == 200,  f'Case 1: Incorrect. ERROR: \n {response.json()}'
    assert len(response.json()) > 1,  f'Case 1: Incorrect. ERROR: \n {response.json()}'
    assert not any(register['id'] in invalid_lodging for register in response.json()),  f'Case 1: Incorrect. ERROR: \n {response.json()}'
    assert (register['guest_capacity'] < search_data['num_travellers'] for register in response.json()),  f'Case 1: Incorrect. ERROR: \n {response.json()}'


    #Case 2 (Correct Test): Lodgings available beetwen reserves
    reserve_lodging = [1]
    search_data = {
        "location": {"longitude": -0.12345, "latitude": 51.98765},
        "ratio": 25,
        "numTravellers": 3,
        "checkIn": "2223-08-15",
        "checkOut": "2223-08-20"
    }
    response = client.post(urn, json=search_data)
    assert response.status_code == 200,  f'Case 2: Incorrect. ERROR: \n {response.json()}'
    assert len(response.json()) > 1,  f'Case 2: Incorrect. ERROR: \n {response.json()}'
    assert not any(register['id'] in invalid_lodging for register in response.json()),  f'Case 2: Incorrect. ERROR: \n {response.json()}'
    assert not any(register['id'] in reserve_lodging for register in response.json()),  f'Case 2: Incorrect. ERROR: \n {response.json()}'
    assert (register['guest_capacity'] < search_data['num_travellers'] for register in response.json()),  f'Case 2: Incorrect. ERROR: \n {response.json()}'


    #Case 3 (Correct Test): Lodgings available with reservation canceled
    reserve_canceled = [8]
    search_data = {
        "location": {"longitude": -122.345678, "latitude": 36.789012},
        "ratio": 25,
        "numTravellers": 3,
        "checkIn": "2224-03-05",
        "checkOut": "2224-03-10"
    }
    response = client.post(urn, json=search_data)
    assert response.status_code == 200,  f'Case 3: Incorrect. ERROR: \n {response.json()}'
    assert len(response.json()) > 1,  f'Case 3: Incorrect. ERROR: \n {response.json()}'
    assert not any(register['id'] in invalid_lodging for register in response.json()),  f'Case 3: Incorrect. ERROR: \n {response.json()}'
    assert any(register['id'] in reserve_canceled for register in response.json()),  f'Case 3: Incorrect. ERROR: \n {response.json()}'
    assert (register['guest_capacity'] < search_data['num_travellers'] for register in response.json()),  f'Case 3: Incorrect. ERROR: \n {response.json()}'




    #Case 4 (Incorrect Test): Lodgings available not found
    search_data = {
        "location": {"longitude": 10.0, "latitude": 20.0},
        "ratio": 1,
        "numTravellers": 1,
        "checkIn": "2223-08-15",
        "checkOut": "2223-08-15"
    }
    response = client.post(urn, json=search_data)
    assert response.status_code == 404,  f'Case 4: Incorrect. ERROR: \n {response.json()}'
    assert 'detail' in response.json().keys() ,  f'Case 4: Incorrect. ERROR: \n {response.json()}'