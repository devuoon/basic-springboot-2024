# basic-springboot-2024

Java 빅데이터 개발자과정 Spring boot 학습 리포지토리

## 1일차

- ## Spring Boot 개요

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

  - 프로젝트 생성 후
    - /build.gradle 확인
    - src/main/resources/application.properties(또는 .yml) 확인
    - src/java/groupid/arifactid/Java 소스파일 위치, 작업
    - src/main/resources/ 프로젝트 설정 파일, 웹 리소스 파일(css, js, html, jsp, etc...)
    - Spring01Application.java Run | Debug 메뉴
