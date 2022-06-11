# AdmWork

# 구현항목(체크)

data access layer 구현

entity 맵핑 - ERD 상에 표현된 컬럼들을 모두 맵핑한다

연관관계 맵핑 - ERD 상에 표현된 relation은 모두 맵핑한다

# Repository

REST API와 웹페이지를 위한 쿼리들을 Repository로 구현한다

## 아래 기능들을 모두 이용해서 Repository를 구현해야 한다

JpaRepository에 정의된 메서드

Spring Data JPA Repository에서 제공하는 메서드 이름 규칙을 이용한 쿼리 생성

 @Query를 이용한 JPQL 수행

Querydsl을 이용한 Custom Repository 구현

너무 한 가지 기능에 의존해서 개발한 경우 감점한다

Dto Project 기능을 최대한 활용한다

Pageable 을 이용해 페이징 기능을 구현해야 한다

# REST API로 구현

주민(등록/수정)

가족관계(등록/수정/삭제)

출생신고(등록/수정/삭제)

사망신고(등록/수정/삭제)

세대(등록/삭제)

세대 전입 주소(등록/수정/삭제)


# 웹

Thymeleaf를 이용해서 HTML로 응답

주민목록

가족곤계증명서

주민등록등본

출생신고서

사망신고서

증명서 발급 목록

주민 삭제
