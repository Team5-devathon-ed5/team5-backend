FROM python:3.8.10

#Creation and activation of working directory.
COPY . /app/
WORKDIR /app

#Implementation of necessary dependencies.
RUN python3 -m venv /venv 
ENV PATH="/venv/bin:$PATH"
COPY ./requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /app/requirements.txt

# Copy the .env file into the /api/sql directory
COPY .env.template /app/.env

#Service launcher.
ENTRYPOINT uvicorn --host 0.0.0.0 main:app --reload