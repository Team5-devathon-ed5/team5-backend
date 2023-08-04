import requests, json
from datetime import date, timedelta
from faker import Faker


LOCAL_URL = "http://localhost:8000"
fake = Faker()

# Test end to end for validate Search and Location model
def test_validate_model():
    urn = "/searching_lodgings/"
    check_in_today = fake.date_this_year(before_today=False, after_today=False)

    #Case 1 (Correct Test): Correct data
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 200, f'Case 1: Incorrect. ERROR: \n {response.json()}'
    

    #Case Check 1 (Incorrect Test): Date check_out before at date check_in 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.past_date(start_date=(check_in_today+timedelta(days=-5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 1: Incorrect. ERROR: \n {response.json()}'


    #Case Check 2 (Incorrect Test): Check_in < today
    check_in_before_today = fake.date_this_year(before_today=True, after_today=False)
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_before_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 2: Incorrect. ERROR: \n {response.json()}'


    #Case Check 3 (Incorrect Test): Number of traveller < 1 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": 0,
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 3: Incorrect. ERROR: \n {response.json()}'


    #Case Check 4 (Incorrect Test): Ratio < 1 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": 0
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 4: Incorrect. ERROR: \n {response.json()}'


    #Case Check 5 (Incorrect Test): Bad location data, latitude > 90 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=True, min_value=91, max_value=92), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 5: Incorrect. ERROR: \n {response.json()}'


    #Case Check 6 (Incorrect Test): Bad location data, longitude < -180 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-183, max_value=-181)},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 6: Incorrect. ERROR: \n {response.json()}'


    #Case Check 7 (Incorrect Test): Bad location data, longitude is str 
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.name()},
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Check 7: Incorrect. ERROR: \n {response.json()}'


    #Case Data 1 (Incorrect Test): Data without location field
    data = {
        "check_in": check_in_today.isoformat(),
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Data 1: Incorrect. ERROR: \n {response.json()}'


    #Case Data 2 (Incorrect Test): Data without check_in field
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_out": fake.date_between(start_date=(check_in_today+timedelta(days=1)), end_date=(check_in_today+timedelta(days=5))).isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Data 2: Incorrect. ERROR: \n {response.json()}'


    #Case Data 3 (Incorrect Test): Data without check_out field
    data = {
        "location": {"latitude":fake.pyfloat(left_digits=2, right_digits=2, positive=False, min_value=-90, max_value=90), 
                     "longitude":fake.pyfloat(left_digits=3, right_digits=2, positive=False, min_value=-180, max_value=180)},
        "check_in": check_in_today.isoformat(),
        "num_travellers": fake.pyint(min_value=1, max_value=3),
        "ratio": fake.pyint(min_value=1, max_value=3)
    }
    response = requests.get(url=f'{LOCAL_URL}{urn}', json=data)
    assert response.status_code == 422, f'Case Data 3: Incorrect. ERROR: \n {response.json()}'

test_validate_model()