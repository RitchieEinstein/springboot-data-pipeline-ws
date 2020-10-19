# Data Pipeline Processor (Persist & Websock Broadcast)

## Overview
This Application has been designed as an aggregation of 3 microservices (whose responsibilities
explained in later parts). For the Pub/Sub processing, RabbitMQ has been chosen as the Messaging system.

## Installation

The Project has been containerized and can be started with the docker compose. Since this is a microservice environment,
to avoid multiple redundant commands to execute, a shell script has been attached to the project. Please execute
the following command from the base folder of the project to start the application.

`./initialize-and-start.sh`

The command will take a while to build all the docker images of the microservices and initiate the docker compose.

To skip to the Usage part - [click here](#usage-instructions)

## System Design
The Entire System is designed in Spring Environment and many spring projects are utilized to achieve a robust microservice env.

**Spring Cloud Stream** is the backbone for the entire messaging infrastructure. It is cohesive and can plug and play many leading messaging systems.  

The System Design Architecture diagram is as follows.

![ArchitectureDiagram](architecture-diagram.png)

#### Message-Ingester Service
This service acts as the API Endpoints host. The main task of this service are as follows.

- API to consume incoming messages and perform basic sanity and push them into the queue.
- API to pull all messages / paginated message list from the database.
- Util Method to calculate the palindrome length as per the requirement.

## Usage Instructions

The main mode of data ingress for the application is through the REST API. The data egress points are REST API and browser 
client listening to the Websockets broadcast.

### API Endpoints
Here is the list of exposed API endpoints.
#### Post new Message Payload
```http
POST /api/message
```
##### RequestBody
```json
{"content":"string_to_be_sent","timestamp":"yyyy-MM-dd HH:mm:ssZ"}
```
##### Response - STATUS CODE 202
```json
{
    "message": {
        "content": "string_to_be_sent",
        "timestamp": "2007-02-28 19:30:21+0530"
    },
    "status": "ACCEPTED"
}
```

#### Get All Messages
##### Request
```http
GET /api/message/all
```

##### Response - STATUS CODE 200
```json
[
  {
      "message": {
          "content": "string_to_be_sent",
          "timestamp": "2007-02-28 19:30:21+0530"
      },
      "status": "ACCEPTED"
  },
  {
      "message": {
          "content": "string_to_be_sent",
          "timestamp": "2007-02-28 19:30:21+0530"
      },
      "status": "ACCEPTED"
  },
  ...
]
```

#### Get All Messages with Pagination
##### Request
```http
GET /api/message?page=0&size=25
```
##### Parameters
|Param  |Data Type  |Description|
|-------|-----------|-----------|
|page   |int        |page number should be > 0|
|size   |int        |page size should be > 0 < 25|
##### Response - STATUS CODE 200
```json
[
  {
      "message": {
          "content": "string_to_be_sent",
          "timestamp": "2007-02-28 19:30:21+0530"
      },
      "status": "ACCEPTED"
  },
  {
      "message": {
          "content": "string_to_be_sent",
          "timestamp": "2008-02-28 19:30:21+0530"
      },
      "status": "ACCEPTED"
  },
  ...
]
```