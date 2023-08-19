from dotenv import dotenv_values
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base
from sqlalchemy.pool import StaticPool

from ..api.sql.database import SQLALCHEMY_DATABASE_URL

env = dotenv_values(".env")  

SQLALCHEMY_DATABASE_TEST_URL = "mysql+mysqlconnector://"+env.get('MYSQL_USER')+":"+env.get('MYSQL_PASSWORD')+"@"+env.get('MYSQL_HOST')+":"+env.get('MYSQL_PORT')+env.get('MYSQL_DATABASE_TEST')

#Test connection
engine = create_engine(
    SQLALCHEMY_DATABASE_TEST_URL,
    poolclass=StaticPool,
)
TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()
Base.metadata.create_all(bind=engine)