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

# 구현
Spring Security 이용한 아이디/비밀번호 인증 추가

아이디/비밀번호 인증 추가(커스텀 로그인 UI)

데이터베이스 테이블 확장 ( 아이디/비밀번호/이메일 추가)

비밀번호  : 단방향 hash 함수의 digest 기반으로 저장

로그인/로그아웃 기능 추가

인증 쿠키 : expire 3일

세션은 redis에 저장

OAuth2 인증 추가

아이디/비밀번호 인증과 OAuth2 인증은 동시에 기능이 제공되어야 한다

OAuth2 Provider는 github 만 제공하면 된다.

Spring Security 라이브러리를 이용하지 않고 github API를 이용해서 직접 구현한다.

github에서 인증한 결과 중 email 값이 실제 resident 테이블에 있는 값이면 그 계정으로 로그인을 시켜준다.

github에서 인증이 성공했다고 하더라도 email 값이 resident 테이블에 없는 값이면 로그아웃 시킨다
