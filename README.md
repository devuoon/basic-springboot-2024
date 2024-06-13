# basic-springboot-2024

Java 빅데이터 개발자과정 Spring boot 학습 리포지토리

## 1일차

- Spring Boot 개요

  - 개발환경, 개발 난이도를 낮추는 작업
  - Servlet > EJB > JSP > Spring(부흥기) > Spring Boot (끝판왕)
  - 장점

    - Spring의 기술을 그대로 사용가능(마이그레이션 간단)
    - JPA를 사용하면 ERD나 DB설계를 하지 않고도 손쉽게 DB 생성
    - Tomcat Webserver가 내장(따로 설치 필요 X)
    - 서포트 기능 다수 존재(개발을 쉽게 도와줌)
    - JUnit 테스트, Log4J2 로그도 모두 포함
    - JSP, Thymeleaf, Mustache 등.. 편하게 사용
    - DB 연동이 쉽다.

  - MVC

    <img src="https://github.com/devuoon/basic-springboot-2024/assets/104570636/89a0d7a6-638e-4957-ae83-ac37ca97a659" style="width:700px;">

- Spring Boot 개발환경 설정

  - Java JDK 확인 > 17버전 이상

    - https://jdk.java.net/archive/
    - 시스템 속성 > 고급 > 환경변수 중 JAVA_HOME 설정

  - Visual Studio Code
    - Extensions > korean 검색,
    - Extensions > Java 검색, Extension Pack for Java
      - Debugger for Java 등
    - Extensions > Spring 검색, Spring Extension Pack 설치
      - Spring Initializration Java Support 등 3개 확장팩 같이 설치
    - Extensions > Gradle for Java 검색, 설치
  - Gradle build tool 설치 고려
    - https://gradle.org/releases/
  - Oracle latest version Docker - 보류

- Spring Boot 프로젝트 생성

  - 메뉴 보기 > 명령 팔레트(ctrl + shift + p)

    - Spring Initializr : Create a Gradle Project...
    - Specify Spring Boot version : 3.2.6
    - specify project language : Java
    - Input Group Id : com.devuoon
    - Input Artifact Id : spring01 (대문자 불가!)
    - specify packagine type : Jar
    - specify Java version : 17
    - Choose dependencies : Selected 0 dependencies
    - 폴더 선택 Dialog 팝업 : 원하는 폴더 선택 Generate .... 버튼 클릭
    - 오른쪽 하단 팝업 open 버튼 클릭
    - Git 설정 옵션, Language Support for Java by Red Hat 설정 항상버튼 클릭

  - TroubleShooting

    1. 프로젝트 생성이 진행되다 Gradle Connection 에러가 뜨면,

    - Extensions > Gradle for Java를 제거
    - VS code 재시작 한 뒤 프로젝트 재생성

    2. Gradle 빌드시 버전 에어로 빌드가 실패하면

    - Gradle build tool 사이트에서 에러에 표시된 버전의 Gradle bt 다운로드
    - 개발 컴퓨터에 설치

    3. ':compileJava' execution failed...

    - JDK 17... error 메세지
    - Java JDK 잘못된 설치 x86(32bit) x64비트 혼용 설치
    - eclipse adoptium jdk 17 새로 설치, 시스템 환경 설정
    - biuld.gradle SpringBoot Framework 버전을 다운 3.3.0 -> 3.1.5
    - VS Code 재시작

  - 프로젝트 생성 후

    - /build.gradle 확인
    - src/main/resources/application.properties(또는 .yml) 확인
    - src/java/groupid/arifactid/Java 소스파일 위치, 작업
    - src/main/resources/ 프로젝트 설정 파일, 웹 리소스 파일(css, js, html, jsp, etc...)
    - Spring01Application.java Run | Debug 메뉴
    - Gradle 빌드
      - 터미널에서 .\gradlew.bat 실행
      - Gradle for java(코끼리 아이콘) > Tasks > Build > Build play icon(Run task) 실행
    - Sprign Boot Dashboard

      - Apps > spring01 Run | Debug 중에서 하나 아이콘 클릭 서버 실행
      - 디버그로 실행해야 Hot code replace 가 동작

        <img src="https://github.com/devuoon/basic-springboot-2024/assets/104570636/c1f31a6b-3fcb-4449-92f7-ff3274ccacea" style="width:300px;">

    - 브라우저 변경 설정
      - 설정 (ctrl + ,) > browser > Spring Dashboard Open with 'Internal' -> 'external'로 변경
      - chrome 을 기본브라우저 사용 추천

## 2일차

- Oracle 도커로 설치

  - Docker는 Virtual Machine을 업그레이드한 시스템
  - 윈도우 서비스 내(services.msc) oracle 관련 서비스 삭제
  - Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
  - Docker 설치시 오류 Docker Dsktop - WSL Update failed
    - Docker Desktop 실행종료 후
    - Windows 업데이트 실행 최신판 재부팅
    - https://github.com/microsoft/WSL/releases, wsl.2.x.x.x64.msi 다운로드 설치한 뒤
    - Docker Desktop 재실행
  - Oracle 최신판 설치

  ```shell
  > docker --version
  > docker pull container-registry.oracle.com/database/free:latest
  latest: ...
  ```

- Database 설정

  - H2 DB : Spring Boot에서 손쉽게 사용한 Inmemory DB, oracle, Mysql, SQLServer과 쉽게 호환
  - Oracle : 운영시 사용할 DB
  - MySQL : Optional 설명할 DB
  - Oracle PKNUSB / pknu_p@ss 로 생성
    - 콘솔(도커/일반 Oracle)
      ```shell
      > sqlplus system/password
      SQL> select name from v$database;
      // 서비스명 확인
      // 최신 버전에서 사용자 생성 시 C## prefix 방지 쿼리
      SQL> ALTER SESSION SET "_ORACLE_SCRIPT"=true;
      // 사용자 생성
      SQL> create user pknusb identified by "pknu_p@ss";
      // 사용자 권한
      SQL> grant CONNECT, RESOURCE, CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW to pknusb;
      // 사용자 계정 테이블 공간 설정, 공간 쿼터 할당
      SQL> alter user pknusb default tablespace users;
      SQL> alter user pknusb quota unlimited on users;
      ```

- Spring Boot + MyBatis

  - application name : apring02
  - Spring Boot 3.3.x 에는 MyBatis 없음
  - Dependency 중 DB(H2, Oracle, MySQL)가 선택되어 있으면 웹서버 실행이 안됨
  - build.gradle 확인

  ```properties
  ## 포트변경
  server.port=8091

  ## 로그색상
  spring.output.ansi.enabled=always

  ## 수정사항이 있으면 서버 자동 재빌드 설정
  spring.devtools.livereload.enabled = true
  spring.devtools.restart.enabled = true

  ## 로그레벨 설정
  logging.level.org.springframework=info
  logging.level.org.zerock=debug

  ## Oracle 설정
  spring.datasource.driver-class-name=oracle.jdbc.OracleDr
  spring.datasource.url=jdbc:oracle:thin:@localhost:1521:X
  spring.datasource.username=pknusb
  spring.datasource.password=pknu_p@ss

  ## MyBatis 설정
  # mapper 폴더 밑에 여러가지 폴더가 내재, 확장자는 .xml이
  mybatis.mapper-locations=classpath:mapper/**/*.xml
  mybatis.type-aliases-package=com.devuoon.spring02.domain
  ```
