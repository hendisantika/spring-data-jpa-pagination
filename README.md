# spring-data-jpa-pagination

[![Java CI with Maven](https://github.com/hendisantika/spring-data-jpa-pagination/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-data-jpa-pagination/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-9.2-4479A1?logo=mysql&logoColor=white)

A sample REST API showing **pagination with Spring Data JPA** (`Pageable` / `PageRequest`) backed by MySQL.
100 fake users are seeded on startup with [JavaFaker](https://github.com/DiUS/java-faker).

## Tech Stack

| Component        | Version                                  |
|------------------|------------------------------------------|
| Java (JDK)       | **25**                                   |
| Spring Boot      | 4.1.1 (Spring Data JPA, Web, DevTools)   |
| Database         | MySQL 9.2 (via Docker Compose)           |
| Build            | Maven Wrapper (`./mvnw`)                 |
| Testing          | JUnit 5, Testcontainers 2.x, REST Assured |
| Container image  | Jib / Dockerfile (JDK 25 base images)    |

## Prerequisites

- JDK 25 (e.g. [Eclipse Temurin 25](https://adoptium.net/))
- Docker (used by Spring Boot Docker Compose support and by Testcontainers in tests)

## Getting Started

1. Clone this repository:
   ```shell
   git clone https://github.com/hendisantika/spring-data-jpa-pagination.git
   cd spring-data-jpa-pagination
   ```
2. (Optional) Change DB credentials in `src/main/resources/application.yml` and `compose.yaml`.
3. Run the application (MySQL is started automatically from `compose.yaml` on port `3307`):
   ```shell
   ./mvnw spring-boot:run
   ```
4. Open http://localhost:8080/api/v1/users

## API

`GET /api/v1/users`

| Query param | Default | Description                         |
|-------------|---------|-------------------------------------|
| `name`      | `""`    | Filter users whose name contains it |
| `page`      | `0`     | Zero-based page index               |
| `size`      | `10`    | Page size                           |

Examples:

```shell
curl "http://localhost:8080/api/v1/users"
curl "http://localhost:8080/api/v1/users?page=2&size=5"
curl "http://localhost:8080/api/v1/users?name=john&page=0&size=20"
```

> Note: the endpoint deliberately sleeps 3 seconds to simulate latency (handy for front-end loading states).

Sample response:

```json
// 20250312062205
// http://localhost:8080/api/v1/users

{
  "timeStamp": "06:22:05.609977",
  "statusCode": 200,
  "status": "OK",
  "message": "Users Retrieved",
  "data": {
    "page": {
      "content": [
        {
          "id": 1,
          "name": "Judson Hermann",
          "email": "judsonhermann@contact.com",
          "status": "ACTIVE",
          "address": "6690 Janella Forge, Randallshire, ND 82623",
          "phone": "(568) 966-0800 x0118",
          "imageUrl": "https://randomuser.me/api/portraits/men/85.jpg"
        },
        {
          "id": 2,
          "name": "Jerome Hane",
          "email": "jeromehane@contact.com",
          "status": "PENDING",
          "address": "285 Ka Road, Lake Madelaine, WI 55277",
          "phone": "(575) 128-9382",
          "imageUrl": "https://randomuser.me/api/portraits/men/21.jpg"
        },
        {
          "id": 3,
          "name": "Virgie Pfannerstill",
          "email": "virgiepfannerstill@contact.com",
          "status": "INACTIVE",
          "address": "67041 Goldner Pass, New Dixiebury, NH 67643-1762",
          "phone": "552-777-3404",
          "imageUrl": "https://randomuser.me/api/portraits/men/51.jpg"
        },
        {
          "id": 4,
          "name": "Genny Champlin",
          "email": "gennychamplin@contact.com",
          "status": "INACTIVE",
          "address": "095 Steuber Glen, New Benjaminland, ND 85553",
          "phone": "(919) 456-6914 x5818",
          "imageUrl": "https://randomuser.me/api/portraits/men/24.jpg"
        },
        {
          "id": 5,
          "name": "Normand Bruen",
          "email": "normandbruen@contact.com",
          "status": "INACTIVE",
          "address": "Suite 981 4906 Pacocha Trail, Jeremiahfurt, FL 55851",
          "phone": "485-021-4645",
          "imageUrl": "https://randomuser.me/api/portraits/men/6.jpg"
        },
        {
          "id": 6,
          "name": "Donte Casper",
          "email": "dontecasper@contact.com",
          "status": "BANNED",
          "address": "896 Roman Glens, East Phillip, NV 38622-6234",
          "phone": "1-095-387-4125 x6390",
          "imageUrl": "https://randomuser.me/api/portraits/men/9.jpg"
        },
        {
          "id": 7,
          "name": "Adelia Olson",
          "email": "adeliaolson@contact.com",
          "status": "INACTIVE",
          "address": "Suite 902 49765 Darron Locks, New Daltonfurt, HI 27623",
          "phone": "1-075-658-8386 x3011",
          "imageUrl": "https://randomuser.me/api/portraits/men/40.jpg"
        },
        {
          "id": 8,
          "name": "Rudy Brakus",
          "email": "rudybrakus@contact.com",
          "status": "BANNED",
          "address": "9056 Mikaela Path, East Eliseo, VA 26159-2394",
          "phone": "408.009.5378",
          "imageUrl": "https://randomuser.me/api/portraits/men/29.jpg"
        },
        {
          "id": 9,
          "name": "Erich Kiehn",
          "email": "erichkiehn@contact.com",
          "status": "ACTIVE",
          "address": "142 Deckow Union, Lake Philomenachester, CT 93500-7834",
          "phone": "(918) 663-3750",
          "imageUrl": "https://randomuser.me/api/portraits/men/25.jpg"
        },
        {
          "id": 10,
          "name": "Everette Hilll",
          "email": "everettehilll@contact.com",
          "status": "PENDING",
          "address": "7207 Kunze Oval, New Gavin, KY 65032-3339",
          "phone": "882-533-4308 x70459",
          "imageUrl": "https://randomuser.me/api/portraits/men/21.jpg"
        }
      ],
      "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
          "empty": true,
          "unsorted": true,
          "sorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
      },
      "last": false,
      "totalPages": 10,
      "totalElements": 100,
      "first": true,
      "size": 10,
      "number": 0,
      "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
      },
      "numberOfElements": 10,
      "empty": false
    }
  }
}
```

## Running Tests

Tests spin up a real MySQL instance with Testcontainers, so Docker must be running:

```shell
./mvnw verify
```

## Docker

Build a container image with Jib (base image `amazoncorretto:25`):

```shell
./mvnw compile jib:dockerBuild
```

Or build with the `Dockerfile` (base image `bellsoft/liberica-openjdk-debian:25`):

```shell
./mvnw package -DskipTests
docker build -t spring-data-jpa-pagination .
```

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests on JDK 25 (Temurin) for every push/PR to `main`,
  uploads surefire reports on failure and submits the Maven dependency graph on `main`.
- **Dependabot** (`.github/dependabot.yml`): weekly (Monday 05:00 Asia/Jakarta) updates for Maven, GitHub Actions,
  Dockerfile and Docker Compose, with grouped PRs (Spring Boot, testing, Maven plugins, minor/patch), labels and
  Conventional Commit prefixes.
