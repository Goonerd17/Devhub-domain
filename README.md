# DevHub-backend

## 프로젝트 개요

**DevHub-backend**는 DevOps 프로젝트를 위한 **간단한 방명록 API** 프로젝트입니다.
현재는 방명록 목록 조회와 작성 기능만 제공하며, 추후 기능 확장을 계획 중입니다.

* **기술 스택:** Spring Boot 3.5, Java 17, H2 Database, JPA, Lombok
* **주요 기능:**

    * 방명록 목록 조회
    * 방명록 작성

---

## 주요 프로젝트 구조

```
src/main/java
├── goonerd.devhub
│   ├── controller
│   │   └── GuestBookController.java
│   ├── service
│   │   └── GuestBookService.java
│   ├── entity
│   │   └── GuestBook.java
│   ├── dto
│   │   ├── GuestBookRequestDto.java
│   │   └── GuestBookResponseDto.java
│   └── common
│       └── ApiResponseBuilder.java
```

---

## 환경 설정

### `application.yml`

```yaml
spring:
  application:
    name: DevHub

  datasource:
    url: jdbc:h2:mem:devhubdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ""

  h2:
    console:
      enabled: true
      path: /h2-console

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        use_sql_comments: true
```

* **H2 콘솔:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* **DB URL:** `jdbc:h2:mem:devhubdb`
* **User:** `sa` / **Password:** 없음

---

## 빌드 및 실행

### Gradle 빌드

```bash
./gradlew build
```

### 실행

```bash
./gradlew bootRun
```

---

## API 문서 (Swagger/OpenAPI 스타일)

### 공통 응답 구조

```json
{
  "isSuccess": true,
  "code": "SUCCESS_CODE",
  "message": "상세 메시지",
  "param": {},
  "data": {}
}
```

* **isSuccess:** 요청 성공 여부 (`true`/`false`)
* **code:** 성공/실패 코드
* **message:** 상세 메시지
* **param:** 요청에 사용된 데이터
* **data:** 실제 응답 데이터

---

### 1. 방명록 목록 조회

* **URL:** `/guestbook`

* **Method:** `GET`

* **Request:** 없음

* **Response Example**

```json
{
  "isSuccess": true,
  "code": "S001",
  "message": "방명록 목록 조회 성공",
  "param": {},
  "data": [
    {
      "id": 1,
      "username": "홍길동",
      "description": "안녕하세요!"
    },
    {
      "id": 2,
      "username": "김철수",
      "description": "좋은 하루 되세요."
    }
  ]
}
```

---

### 2. 방명록 작성

* **URL:** `/guestbook/create`
* **Method:** `POST`
* **Request Example**

```json
{
  "username": "홍길동",
  "description": "안녕하세요!"
}
```

* **Response Example**

```json
{
  "isSuccess": true,
  "code": "S002",
  "message": "방명록 작성 성공",
  "param": {
    "username": "홍길동",
    "description": "안녕하세요!"
  },
  "data": {
    "id": 1,
    "username": "홍길동",
    "description": "안녕하세요!"
  }
}
```

* **Error Example**

```json
{
  "isSuccess": false,
  "code": "F002",
  "message": "방명록 작성 실패",
  "param": {
    "username": "홍길동",
    "description": "안녕하세요!"
  },
  "data": "에러 상세 메시지"
}
```

---

## 사용 기술

* **Spring Boot 3.5**
* **Java 17**
* **Spring Data JPA**
* **H2 Database**
* **Lombok**

---

## 향후 계획

* 추후 논의 예정

---