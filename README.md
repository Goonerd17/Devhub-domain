# DevHub
---

* DevHub는 **사이드 프로젝트 구인/매칭 플랫폼**입니다.
* 사용자의 기술 스택과 관심 분야를 기반으로 프로젝트를 추천하고, 팀 구성과 지원 과정을 관리합니다.
* 본 Reposiotry는 실제 프로젝트가 진행되기 전에 필요한 공통코드 작성 및 헥사고날 아키텍처가 적용된 예제 소스 Repository 입니다.
* 도메인 중심 설계, 확장성을 보장하도록 구성되어 있으며 추후 테스트 용이성을 실제로 확인 및 검토해보고자 합니다.

---

## 헥사고날 아키텍처
![img.png](img.png)

### 헥사고날 아키텍처 적용 이유

* **도메인 중심 설계**: 핵심 비즈니스 로직은 도메인 계층에 집중
* **유연한 확장성**: 외부 시스템 변경 시 Adapter만 수정, 도메인/서비스는 영향 없음
* **비즈니스와 인프라 관심사 분리**: 도메인은 POJO를 통해 Spring Framework에 종속적이지 않고,서비스는 Repository Interface에 의존하기 때문에 실제 DB Access 정책과는 무관합니다.
* **테스트 용이성**: 외부 의존성(DB, Web, 외부 API 등)을 Adapter로 격리

---

## 프로젝트 구조

```
devhub
├─ adapters
│   ├─ in           # Controller, 외부요청 Request/ResponseDTO -> 시스템 입력 Command
│   └─ out          # RepositoryAdapter 등 외부 시스템 연결
├─ common           # 프로젝트 전반에 걸친 공통 기능
├─ domain           # 핵심 도메인 (POJO 패턴)
│   ├─ project
│   ├─ user
│   └─ application
├─ ports
│   ├─ in           # UseCase 인터페이스
│   └─ out          # Repository 인터페이스
├─ service
│   ├─ application  
│   ├─ facade       # 여러 서비스 호출 후 응답 조립
│   ├─ project
│   └─ user
└─ DevHubApplication (Spring Boot Application)
```

---

## 흐름

### 1. [Adapter In] Controller(Class)

* 외부 요청 수신
* Request DTO → Command 변환
* 공통 응답(`ApiResponseVo`) 및 ResponseEntity로 Wrapping
* 단순 전달자, 비즈니스 로직 없음(단, 기본적인 입력값 또는 입력양식은 RequestDto에서 수행 예정)

### 2. [Port In] UseCase(Interface)

* Controller와 Service 간 계약
* 테스트 용이성을 위해 인터페이스 제공

### 3. [Service] Facade(Class)

* 여러 서비스 호출 후 응답 조합
* 기존 레이어드 아키텍처에서 하나의 서비스가 다른 서비스 또는 리포지토리를 다수 참조하게 되는 책임을 해당 Facade 계층에 부여
* 외부 시스템 호출이나 복잡한 비즈니스 흐름 조립 예정

### 4. [Service] Domain-Service(Class)

* 도메인을 이용한 핵심 비즈니스 처리
* 도메인 상태,행위는 캡슐화된 내부 도메인 메서드를 호출하는 방식으로 도메인 객체 스스로 자신의 상태를 변경하고 책임질 수 있도록 구성 
* Repository 인터페이스 호출
* Repository로 넘겨주는 파라미터와 Repository의 반환값 모두 Domain 객체를 이용하여 실제 Domain-Service 계층이 DB 스키마와 동일한 Entity 클래스를 알 수 없도록 적용

### 5. [Domain] Domain(Class)

* 핵심 비즈니스 로직, POJO 기반
* 필요하다면, 불변 객체 사용
* 프레임워크와 완전히 분리

### 6. [Port Out] Repository(Interface)

* DB 접근 계약 정의

### 7. [Adapter Out] RepositoryAdapter(Class)

* 실제 DB 접근 구현
* DomainMapper 클래스를 이용한 도메인 ↔ 엔티티 변환 담당
* DB 변경 시 도메인 영향 없음

### 8. [DB Access] RepositoryJPA

* 현재는 JPA Repository 사용
* Adapter Out에서 생성자를 이용한 의존성 주입으로 실제 호출

---

## 전체 데이터/요청 흐름

```
[External Call] <-> [Adapter In] Controller <-> [Port In] UseCase <-> [Service] Facade/Service <-> [Domain]
                                                                                 ^
                                                                                 |
                                                                                 v 
                                                                        [Port Out] Repository
                                                                                 ^
                                                                                 |
                                                                                 v
                                                                  [Adapter Out] Repository Adapter <-> [JPA] <-> H2
```

---

## 에시: 프로젝트 생성

### 요청 JSON

```json
{
  "username": "Goonerd",
  "title": "AI 기반 프로젝트 매칭 플랫폼 개발",
  "content": "사용자의 기술 스택을 기반으로 프로젝트를 자동 추천하는 플랫폼을 개발합니다.",
  "recruitmentType": "EXTRA",
  "projectProgressType": "ONLINE",
  "recruitCount": 5,
  "positions": [
    {"position": "BACKEND", "proficiency": "JUNIOR", "capacity": 2},
    {"position": "FRONTEND", "proficiency": "JUNIOR", "capacity": 2},
    {"position": "DESIGNER", "proficiency": "JUNIOR", "capacity": 1}
  ],
  "skills": ["Java", "Spring Boot", "React", "Figma"],
  "startDate": "2025-12-15",
  "endDate": "2025-12-20"
}
```

---

## 주의 사항

1. **도메인 불변 유지:** Service와 Facade에서 도메인을 직접 변경하지 않음
2. **Mapper 책임 분리:** Domain ↔ Entity 변환 전담
3. **AuditInfo 처리:** BaseEntity 상속된 Entity + 도메인에 해당 정보 필드 추가

---