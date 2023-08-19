from dotenv import dotenv_values
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

env = dotenv_values(".env")  

SQLALCHEMY_DATABASE_URL = "mysql+mysqlconnector://"+env.get('MYSQL_USER')+":"+env.get('MYSQL_PASSWORD')+"@"+env.get('MYSQL_HOST')+":"+env.get('MYSQL_PORT')+env.get('MYSQL_DATABASE')

engine = create_engine(SQLALCHEMY_DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()