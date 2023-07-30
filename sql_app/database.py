from os import environ as env 
from dotenv import load_dotenv
from sqlalchemy import create_engine, text
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

load_dotenv()

SQLALCHEMY_DATABASE_URL = "mysql+mysqlconnector://"+env.get('DB_ROOT')+":"+env.get('DB_PASSWORD')+env.get('DB_HOST')+":"+env.get('DB_PORT')+env.get('DB_DATABASE')

engine = create_engine(SQLALCHEMY_DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()