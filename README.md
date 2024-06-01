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

## Database

The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.

├── app
│   ├── dto
│   │   ├── PaymentRequest.java
│   │   └── PaymentResponse.java
│   ├── rest
│   │   ├── PaymentResource.java
│   │   └── ResourceInterceptor.java
│   └── service
│       ├── PaymentServiceFacade.java
│       └── PaymentService.java
├── cross
│   └── ZonedDateFactory.java
├── domain
│   ├── entity
│   │   └── EPayment.java
│   ├── gateway
│   │   └── IPaymentGateway.java
│   └── usecase
│       ├── IDeletePaymentUseCase.java
│       ├── IFindPaymentUseCase.java
│       ├── impl
│       │   ├── DeletePaymentUseCase.java
│       │   ├── FindPaymentUseCase.java
│       │   ├── SavePaymentUseCase.java
│       │   └── UpdatePaymentUseCase.java
│       ├── ISavePaymentUseCase.java
│       └── IUpdatePaymentUseCase.java
└── infra
    ├── dataprovider
    │   └── PaymentDataProvider.java
    └── db
        ├── model
        │   └── Payment.java
        └── repository
            ├── IPaymentRepository.java
            └── PaymentRepository.java could you create and script to create all this folder

#!/bin/bash

# Base directory

BASE_DIR="C:\Users\binho\Documents\kafka-arquitetura-limpa\service-principal\src\main\java\br\com\faluz"

# Create directories

mkdir -p $BASE_DIR/app/dto
mkdir -p $BASE_DIR/app/rest
mkdir -p $BASE_DIR/app/service
mkdir -p $BASE_DIR/cross
mkdir -p $BASE_DIR/domain/entity
mkdir -p $BASE_DIR/domain/gateway
mkdir -p $BASE_DIR/domain/usecase/impl
mkdir -p $BASE_DIR/infra/dataprovider
mkdir -p $BASE_DIR/infra/db/model
mkdir -p $BASE_DIR/infra/db/repository

# Create empty files

touch $BASE_DIR/app/dto/PaymentRequest.java
touch $BASE_DIR/app/dto/PaymentResponse.java
touch $BASE_DIR/app/rest/PaymentResource.java
touch $BASE_DIR/app/rest/ResourceInterceptor.java
touch $BASE_DIR/app/service/PaymentServiceFacade.java
touch $BASE_DIR/app/service/PaymentService.java
touch $BASE_DIR/cross/ZonedDateFactory.java
touch $BASE_DIR/domain/entity/EPayment.java
touch $BASE_DIR/domain/gateway/IPaymentGateway.java
touch $BASE_DIR/domain/usecase/IDeletePaymentUseCase.java
touch $BASE_DIR/domain/usecase/IFindPaymentUseCase.java
touch $BASE_DIR/domain/usecase/impl/DeletePaymentUseCase.java
touch $BASE_DIR/domain/usecase/impl/FindPaymentUseCase.java
touch $BASE_DIR/domain/usecase/impl/SavePaymentUseCase.java
touch $BASE_DIR/domain/usecase/impl/UpdatePaymentUseCase.java
touch $BASE_DIR/domain/usecase/ISavePaymentUseCase.java
touch $BASE_DIR/domain/usecase/IUpdatePaymentUseCase.java
touch $BASE_DIR/infra/dataprovider/PaymentDataProvider.java
touch $BASE_DIR/infra/db/model/Payment.java
touch $BASE_DIR/infra/db/repository/IPaymentRepository.java
touch $BASE_DIR/infra/db/repository/PaymentRepository.java

echo "Folder structure and files created successfully."
