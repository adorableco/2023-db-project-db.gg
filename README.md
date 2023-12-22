# DB.gg

### Dissect Battle - 전투를 해부하다.
</br>
<img width="280" alt="스크린샷 2023-12-22 오후 10 47 19" src="https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/7ad471e3-e065-4b30-ac30-24b34387d78b">

</br>
</br>

> 2023 경북대학교 데이터베이스 팀 프로젝트</br>
> 개발기간 : 2023.11.27 ~ 2023.12.06
</br>


## 프로젝트 소개

이제 온라인 게임은 단순히 오락 요소를 넘어서, 승패보다는 어떻게 이기는지, 그 과정과 전략이 중요한 분야가 되었습니다. 이러한 변화를 반영하고자, 저희 팀은 과거 게임의 전적 정보를 보여주는 것을 넘어서 플레이어들이 자신의 전적을 깊게 분석하고 개선할 수 있도록 도움을 주는 웹 서비스를 구상하였습니다. 사용자 친화적인 인터페이스와 함께 각 경기 별 세부 정보와 통계 데이터 등을 제공하여 사용자들의 전략 수립과 능력 향상에 도움을 주는 웹 서비스입니다.
</br>

## 시연영상 🎞️
Link : [Youtube](https://youtu.be/6ddl6ElogIg?si=I6WQ2d7a_68TofFx)
</br>

## 팀원 소개
|이상희|박세연|
|---|---|
|<a href="https://github.com/sanghee0820"><img src="https://avatars.githubusercontent.com/u/102018082?v=4" width="130" height="130"/></a>|<a href="https://github.com/adorableco"><img src="https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/6c8918a0-5bad-45fc-b2b5-d4906c1d2198" width="130" height="130"/></a>|
|백엔드|프론트엔드|
</br>

## 시작 가이드 
### 요구 사항
[Java - openjdk 17.0.9 2023-10-17](https://jdk.java.net/17/)</br>
Oracle (com.oracle.database.jdbc:ojdbc8)</br>
[Npm 10.2.4](https://www.npmjs.com/package/npm/v/10.2.4)

### 실행 방법 
```bash
$ git clone https://github.com/sanghee0820/db-project-lol-op.gg.git
$ cd db-project-lol-op.gg
```

### Backend
`src/main/java/resource/application-db.properties` 수정
```java
spring.datasource.url=
spring.data.source.usename=
spring.datasource.password=
```
이 세줄을 환경에 맞게 수정합니다.
✅ 실행 포트는 8080번으로 설정돼 있습니다.
</br>
### Frontend
```bash
$ cd frontend
$ npm install
$ npm start
```
✅ 실행 포트는 3002번으로 설정돼 있습니다.

</br>

## 기술 스택
### Environments
<img src="https://img.shields.io/badge/Vscode-4285F4?style=for-the-badge&logo=visualstudiocode&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"/>
</br>

### Config
  <img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white"/>
</br>

### Development
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 

### Communication
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/Google Meet-00897B?style=for-the-badge&logo=googlemeet&logoColor=white"> 
</br>
</br>

## 기능 소개

**1. 메인페이지 (‘/’)**</br>
- 조회하고 싶은 소환사의 이름 검색
- 소환사 이름 일부 입력 시, 포함하는 모든 소환사 리스트 확인 가능
- 또한, 게임 계정(사용자), 아이템, 챔피언 데이터 추가/수정/삭제 가능 페이지로 이동하는 버튼 존재

![메인페이지 이미지](https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/f5f2f623-c7ce-48a5-92de-b67033d5c0a1)

**2. 소환사 검색 결과 페이지 (‘/summoner_list/{검색 키워드})**</br>
검색 조건에 해당하는 소환사 리스트 확인 가능
- 각 소환사의 게임이름, 게임 레벨 표시

![검색 결과 이미지](https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/1efbff72-6226-4862-977f-6013e6ae3f67)

**3. 특정 소환사의 매치 리스트 페이지 (‘/match_list/{소환사 게임 네임}’)**</br>
- 특정 소환사의 상세 정보와 소환사가 참가한 매치 리스트 확인 가능
- 소환사의 게임 네임, 티어, 레벨 표시. 소환사의 전체 매치 평균 KILL 횟수 표시
- 연두색 버튼 클릭 시, 이 평균 KILL 횟수보다 많은 KILL을 달성한 회원의 아이템 구매 횟수 확인 가능 페이지로 이동
- 매치 리스트에는 각 매치의 매치 ID, 소환사의 KILL, DEATH, ASSIST 횟수, 소환사가 선택한 챔피언 확인 가능

![매치 리스트 이미지](https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/e86d44f9-50e8-497e-9eff-fd679bb5f009)

**4. 매치 상세 페이지 (‘/match_detail/{매치 아이디}’)**</br>
- 해당 매치에 참가한 소환사의 참가자 번호, 게임 네임, 선택한 챔피언 확인 가능
- 연두색 버튼 클릭 시, 같은 챔피언을 사용한 소환사의 전적 분석 확인 가능
- 참가자 정보 하단에는 해당 매치에서 일어난 이벤트들 표시. 이벤트 별로 이벤트를 일으킨 참가자 번호, 이벤트 타입, 이벤트가 일어난 시간, 그리고 이벤트 타입이 ‘아이템 구매’인 경우, 구매한 아이템 명 확인 가능

![매치 상세 이미지](https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/3419ccd0-6f2e-42b3-8262-52f510e60f98)

(좌측) `비슷한 시간대의 진행된 경기들 어떨까?` 창: 해당 매치와 비슷한 시간대(시기)에 진행된 매치들의 매치 ID와 진행시간 확인 가능. 매치 ID 클릭 시, 매치 상세 페이지로 이동 </br>
(우측) `진행 시간이 비슷한 경기들의 결과는?` 창: 해당 매치와 진행 시간이 비슷한 매치들의 결과를 승리한 팀 ID, 패배한 팀 ID, 진행시간과 함께 확인 가능. 승리한 팀은 연두색 하이라이트로 강조

**5. 챔피언을 사용한 소환사 전적 분석 페이지 (‘/champ/{챔피언 이름}’)**</br>
해당 챔피언을 사용한 소환사가 한 매치에서 달성한 KILL, DEATH, ASSIST 횟수를 리스트로 확인 가능. 또한, 전체 KILL 평균 횟수, 전체 DEATH 평균 횟수, 전체 ASSIST 평균 횟수로 보기 쉽게 확인 가능.

**6. 특정 이벤트과 비슷한 시간대에 일어난 이벤트 리스트 페이지 (‘/event/{이벤트가 일어난 시간}’)**</br>
- 특정 이벤트과 비슷한 시간대에 일어난 이벤트를 이벤트 타입과 이벤트가 일어난 시간으로 확인 가능

(좌측 사용자 추가 토글, 우측 아이템 수정 토글)

![이벤트 리스트 이미지](https://github.com/sanghee0820/db-project-lol-op.gg/assets/90397541/dd4d7d35-a6f3-40db-bd00-9ee92a64dbef)
