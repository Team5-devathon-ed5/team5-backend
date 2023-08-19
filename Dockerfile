# Dockerfile

#Image
FROM tiangolo/uvicorn-gunicorn-fastapi:python3.8

#Creation and activation of working directory.
WORKDIR /app
COPY app/ .

#Implementation of necessary dependencies and Python env.
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

COPY ./requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /app/requirements.txt

# Copy the .env file into the /api/sql directory
COPY .env.template /app/.env