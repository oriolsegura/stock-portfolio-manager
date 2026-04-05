# Opulentia — Stock Portfolio Management

Backend service for managing stock portfolios, accounts, and transactions, built with Spring Boot.

This project focuses on data modeling, transactional integrity, and API design in a financial domain.

## Purpose

This project is part of a deliberate effort to
1. deepen backend and system design skills,
2. explore financial domain modeling, and
3. build production-oriented architectures.

## Core Concepts

- Users and JWT-based authentication
- Accounts with currency support
- Stock catalog
- Transactions as the single source of truth
- Portfolio derived via aggregation

## Design Decisions

- Event-based portfolio model: positions are not stored explicitly; instead, holdings are derived from transactions.
- Database-first consistency: referential integrity and constraints enforced at the database level.
- Stateless authentication: JWT-based security with Spring Security filters.
- Clear separation of concerns: Controller → Service → Repository.

## Tech Stack

- Java 25 + Spring Boot 4
- Spring Security (JWT)
- PostgreSQL 18
- Flyway (migrations)
- JPA / Hibernate
