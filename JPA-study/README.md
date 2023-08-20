# Spring Data Jpa Module

## Jpa 쿼리 지원하는 종류

1. JPQL : entity를 대상으로 쿼리작성
    - "SELECT b FROM Board b WHERE b.title = ?"
    - 대소문자 구분해야됨
  

2. Query Method : 메서드 이름으로 JPQL을 자동생성
    - boardRepository.findByTitleAndWrtier....
    - 너무 길어짐
    - [쿼리메서드 예시] (https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)


3. JPA Criteria : JPQL 메서드 조합으로 작성
    - QueryDSL을 더 많이 사용


4. Querydsl : 오픈소스 메서드 조합으로 작성


5. NativeSQL : Jpql 대신 Sql 직접 작성
    - @Query
