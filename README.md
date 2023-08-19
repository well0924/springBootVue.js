# Spring boot Vue.js project

## 취지 

Spring boot 와 Vue.js를 사용해서 간단한 게시판 프로젝트를 만드는 것이 목적입니다.

## 제작인원: 1인

## 기술스택

- BackEnd
  - Java
  - Spring Boot
  - Jpa
  - MapStruct
  - Spring Security
  - Query Dsl

- FrontEnd
  - vue.js
  - JQuery
  - BootStrap

## featured 

- [x] 회원 api crud
  - 회원 가입
  - 회원 수정
  - 회원 탈퇴
- [x] 게시글 api crud
  - 게시글 작성
  - 게시글 수정
  - 게시글 삭제
  - 게시글 페이징 및 검색
- [x] rest api로 설계
- [x] jwt를 활용한 로그인 기능(spring security)
- [x] query dsl을 활용한 동적 검색 및 정렬
- [x] vue.js를 활용한 프론트 처리
- [x] swagger를 활용한 api 문서작성.

## ERD

## 개발 일지

- 1일차:
  - [x] 프로젝트 생성 및 mapstruct로 dto<->entity 전환 검증
  - [x] 회원 목록 및 상세조회 api 기능

- 2일차:
  - [x] 회원 api 완료
  - [x] 커스텀 예외처리
  - [x] 게시글 api 완료
  - [x] query dsl 동적 검색 적용(게시글 검색,정렬)
  - [x] vue.js 연동(포트를 선언시 화면이 나오는가??)
  - [x] swagger 적용(모델 어노테이션 및 에러 응답 설정)

- 3일차:
  - [x] post, member 테스트 코드 작성하기.
  - [x] SpringSecurity 적용
  - [x] jwt 로그인 적용하기.
  - [x] swagger security 설정

- 4일차
  - [x] 시큐리티 작동시 예외처리 작동 확인

- 5일차
  - [x] vue.js 화면(로그인,회원 가입 화면)

- 7일차
  - [x] 게시글 화면 연결 및 페이징 처리.