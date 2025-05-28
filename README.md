# Spring MCP Demo

This is a demo project to connect an MCP client to an MCP Server, both implemented using the Spring MCP library.

The client connects to a running instance of **Ollama**, and by default uses the Llama 3.1 model, but can in theory use
any LLM model compatible with MCP tools.

## Java Version

This is built using Java 24. Lower versions of Java will also probably work, but I have not tested.
This is also built with Spring 3.5.
## The application

This demo application contains a MCP Server which is connected to a database, for a **Hospital**. It contains three
tables,
`doctors`, `patients`, and `appointments`. We would like to perform CRUD operations on these tables. That is, create
patients and doctors and make appointments.
To do this we expose multiple MCP Tools to the MCP Client that allows it to perform the desired database operations via
natural language requests from a user.

## Setup

Setup requires 4 components
1. PostgreSql database
2. Ollama instance
3. MCP Server app
4. MCP Client app

### Ollama

We can start Ollama in docker (doesn't have to be dockerized, but it is simplest.)
```bash
docker run -d  -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
```

### Database

The MCP server contains tools that perform operations on a PostgreSQL database. The
database can be started by running

```bash
docker compose up --build
```

which will use Flyway to build, modify the tables, and sets up the database on default port `5432`.

### MCP Server

MCP Server can be run from the `mcp-server` module directory with the command:

```bash
mvn spring-boot:run
```
By default, this will run on port `8080`.

### MCP Client

MCP Client can be run from the `mcp-client` module directory with the command:

```bash
mvn spring-boot:run
```
The client runs on port `8081`.


## Using the app

We can send POST requests to our MCP app (the client). 
Examples below:

Create a doctor record.
```bash
curl -X POST -H 'Content-Type: application/json' -d \
'{"message":"Create a doctor with first name Gregory, last name House. His specialization is diagnostics."}' \
http://localhost:8081/tool
```

Create a patient record.
```bash
curl -X POST -H 'Content-Type: application/json' -d \
'{"message": "create a patient record with first name Bob and last name Jones. Email is bobjones@email.ccc, and the phone number is 090-123-567-432."}' \
http://localhost:8081/tool

```

Create an appointment.
```bash
curl -X POST -H 'Content-Type: application/json' -d \
'{"message":"Create an appointment for the doctor with id 1, and patient with id 1, where the time of the appointment is 2025/08/25 at 3:15 PM"}' \
http://localhost:8081/tool
```

Delete a doctor (Cascades to appointments)

```bash
curl -X POST -H 'Content-Type: application/json' -d \
'{"message":"Delete the doctor record which has id 1."}' \
http://localhost:8081/tool
```
