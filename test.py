import requests
from faker import Faker


LOCAL_URL = "http://localhost:8000"
fake = Faker()

# Test end to end for validate Search and Location model
def test_validate_model():
    urn = "/search/"

    #Case 1 (Correct Test): Correct data
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": "2023-07-28",
        "check_out": "2023-07-30",
        "num_travellers": 2
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 200, f'Case 1: Incorrect. ERROR: \n {response.json()}'
    

    #Case 2 (Incorrect Test): Date check_out before at date check_in 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": "2023-07-30",
        "check_out": "2023-07-28",
        "num_travellers": 2
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case 2: Incorrect. ERROR: \n {response.json()}'


    #Case 3 (Incorrect Test): Number of traveller < 1 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": "2023-07-28",
        "check_out": "2023-07-30",
        "num_travellers": 0 
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case 3: Incorrect. ERROR: \n {response.json()}'


    #Case 4 (Incorrect Test): Bad location data, latitude > 90 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=True, min_value=91, max_value=92), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": "2023-07-28",
        "check_out": "2023-07-30",
        "num_travellers": 0 
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case 4: Incorrect. ERROR: \n {response.json()}'


    #Case 5 (Incorrect Test): Bad location data, longitude < -180 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-183, max_value=-181)},
        "check_in": "2023-07-28",
        "check_out": "2023-07-30",
        "num_travellers": 0 
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case 5: Incorrect. ERROR: \n {response.json()}'


    #Case 6 (Incorrect Test): Bad location data, longitude is str 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.name()},
        "check_in": "2023-07-28",
        "check_out": "2023-07-30",
        "num_travellers": 0 
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case 6: Incorrect. ERROR: \n {response.json()}'

test_validate_model()