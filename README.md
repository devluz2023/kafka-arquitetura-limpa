# Authentication API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Kafka](https://img.shields.io/badge/Kafka-%23100000.svg?style=for-the-badge&logo=apache-kafka)

This project is an API built using **Java, Java Spring, Flyway Migrations, PostgresSQL as the database, and kafka.**

The API was developed as practice test

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/devluz2023/kafka-arquitetura-limpa.git
```

2. Install dependencies with Maven

3. Install [PostgresSQL](https://www.postgresql.org/)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints

The API provides the following endpoints:

```markdown
POST /device/contracted
{
"device": "457300-4862-d2535cb9-2cd5-4391-a527-db5c39544964",
"released": true
}
```
## Kafka

```bash 1.1 Create the topic contractions.events
docker exec kafka kafka-topics --create --topic contractions.events --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```
```bash  1.2 Create the topic devices.events
docker exec kafka kafka-topics --create --topic devices.events --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```
```bash  1.3 Create the topic devices.events.retry
docker exec kafka kafka-topics --create --topic devices.events.retry --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```
```bash  1.4 Create the topic devices.events.dlq
docker exec kafka kafka-topics --create --topic devices.events.dlq --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```
```bash  1.5 list topics to confirm the creation
 docker exec kafka kafka-topics --list --bootstrap-server localhost:9092
```

```bash  read data
docker exec kafka-console-consumer --bootstrap-server localhost:9092 --topic contractions.events
--from-beginning --max-messages 100
```
```bash  send data
docker exec kafka kafka-console-producer --bootstrap-server localhost:9092 --topic contractions.events
```

## Database

The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.
