# 1차 미니 프로젝트
1. 주제 - 간단한 수강 신청 프로그램
2. 요구사항
    - 개설 과목 확인시 신청 인원을 보여줄것
    - 수강 신청시 과목명 말고 과목코드를 통해 입력 받도록 할 것
3. 동작화면
    - 개설 과목 조회
    - 수강 신청
    - 수강 내역 조회
    - 프로그램 종료

## 1차 피드백
- [x] RegisterFunction의 addSubject()의 중복 코드들을 메서드로 만들어서 간결하게 할 것
    - findUpdate() : 일치하는 정보가 존재하는 경우 기존 객체를 변경
    - notFindUpdate() : 일치하는 정보가 없는 경우 새로운 객체 생성

## 2차 피드백
- [x] RegisterFunction의 addSubject() 불필요한 switch-case문 제거 할 것

## 3차 피드백
- [x] RegisterFunction의 불필요한 static 제거 할 것
