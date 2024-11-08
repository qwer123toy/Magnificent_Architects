
<div align="center">
<img src="https://github.com/user-attachments/assets/600efd58-25bd-4fad-943a-8eac9b6316c2">
</div>



<h1 align="center">
  가상 주식 투자 프로그램
</h1>
<p align="center">가상 주식 투자 프로그램</p>
<p align="center"> 실제 주식과 동일한 시뮬레이션을 구성하여 <br>
  주식을 경험하지 않은 사람도 리스크 관리와 투자 전략 수립의 중요성을 체험해주는 프로그램 </p>

---

## Contents

<p align="left">목차</p>
<p align="left">
  <a href="#what-is">What is?</a>  <br>
  <a href="#key-features">Key Features</a> <br>
  <a href="#development-setup--database-design">Development Setup</a> <br>
  <a href="#repository-structure">Repository Structure</a> <br>
  <a href="#authors">Authors</a>
</p>
<br>

---

## 기능 설명
[DAO](/240902_final_team1MagnifientArchitecs/src/DAO)
 - 데이터베이스에서 테이블들을 다루기 위한 DAO 클래스
 - 회사 정보를 검색, 추가, 수정하거나 뉴스, 유저 정보 등을 다루기 위해 선언
   
[MainSystem](/240902_final_team1MagnifientArchitecs/src/MainSystem)
 - 테스트 가능한 범위의 작은 기능 모듈을 구현
 - 이를 객체지향적으로 역할에 따라 클래스 별로 캡슐화하여 주요 기능들을 테스트

[dbUtil](/240902_final_team1MagnifientArchitecs/src/dbUtil)
 - DB 연결을 위해 properties를 만들고 MySQL에 연결하기 위한 메서드 선언

[loginUI](/240902_final_team1MagnifientArchitecs/src/loginUI)
 - 로그인, 회원가입 부분의 GUI를 생성

[mapper](/240902_final_team1MagnifientArchitecs/src/priceGUI)
 - DB의 데이터와 매핑하여 주는 클래스
   
[tables](/240902_final_team1MagnifientArchitecs/src/tables)
 - DB와 동일한 정보를 가질 수 있도록 필드값들을 선언한 클래스

[ui](/240902_final_team1MagnifientArchitecs/src/ui)
 - 프로그램이 돌아갈 수 있도록 만들어둔 패널들

[priceGUI](/240902_final_team1MagnifientArchitecs/src/priceGUI)
 - 주식 매수, 매도 부분 GUI 패널

## What is

<h3>1. 개요 및 목적</h3>

  • 개요 
   - 가상 주식 거래 프로그램은 실제 주식 거래 환경과 유사한 시스템을 구축

  • 목적
   - 주식을 경험하지 않은 사람도 리스크 관리와 투자 전략 수립의 중요성을 체험해주는 프로그램
   - 최대 30일간의 가상 투자 기간 동안 시장 변화에 따른 주식을 사용자가 실제와 유사한 거래를 경험할 수 있도록 하는 시뮬레이션 개발

<h3>2. 프로젝트 시나리오 및 기능/성과 </h3>

  • 프로젝트 시나리오
   1. 사용자 로그인 : 계정을 통해 각각의 투자 상황을 관리
   2. 검색 및 정보 열람 : 주식 종목의 검색과 정보 열람
   3. 주식 구매 결정 : 매수 또는 매도 결정을 내릴 수 있는 인터페이스
   4. 구매 완료 및 주가 변동 : 거래 완료 후 시뮬레이션된 시장 정보에 따라 주가 변동
   5. 시뮬레이션 진행 : 주기적인 시장 상황 업데이트로, 위 과정을 최대 30일까지 진행
   6. 시뮬레이션 종료 및 결과 확인 : 투자 성과를 시각적으로 제공
     
  • 주요 기능 및 성과
   - 주식 거래 시뮬레이션 : 시장 정보에 따라 주식의 가격이 변동되도록 랜덤 이벤트와 경제 이슈를 반영
   - 사용자별 데이터 관리 : 사용자마다 독립된 데이터와 다양한 시나리오를 사용

  • Git 브랜치를 활용해 역할 분담을 명확히 하고, 병합 시 충돌 문제를 최소화
  
## Key Features

| 핵심 기능                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **MainProgram**                  | ● 가상 주식 거래 시뮬레이션을 위해 회원가입, 로그인, 저장된 데이터 선택 기능을 제공 <br> - 시뮬레이션을 모듈을 통해 사전 테스트하는 부분                              |
| **AllCompanyDAO**                 | ●  테이블에서 회사 정보를 CRUD 방식으로 관리 <br> - 회사 주식 정보, 날짜 등의 데이터를 입력 및 업데이트하고, 특정 사용자 ID와 저장 데이터로 조회 기능을 제공                                    |
| **AllCompanyMapper**                 | ●  AllCompany 테이블의 데이터를 AllCompany 객체로 매핑하는 역할을 하며, 단일 행을 선택해 반환하는 selectRow 메서드를 제공                               |
| **SaveData**     | ● 사용자 저장 데이터를 관리하며 선택된 데이터를 불러와 표시하는 UI를 구성 <br> - 사용자 저장 데이터를 표시하고, 저장 상태에 따라 새 데이터를 추가 또는 기존 데이터를 불러오는 기능을 제공 <br> - 각 버튼은 저장 데이터를 불러와 BaseMainFrame으로 이동시키며, 필요시 데이터를 새로 생성                           |
| **CompanyInfoPnlforgraph**           | ● CompanyInfo 객체의 세부 정보를 JLabel을 통해 표시하는 GUI 패널을 제공                                                  |
| **SeeMyTradingHistoryPnl**      | ● 사용자의 투자 내역과 거래 기록을 표시 <br> - 투자 내역, 잔고 및 수익률을 업데이트하는 기능을 제공 <br> -  각 거래 내역을 동적으로 표시하며, 뒤로가기 버튼을 통해 이전 화면으로 돌아갈 수 있도록 함       |
| **BuyPriceGUI**        | ● 사용자가 회사의 주식을 매수하고 해당 거래를 데이터베이스에 반영하는 기능을 제공 <br> - 사용자가 주식 매수 수량과 가격을 입력하고, 이를 기반으로 매수 금액을 계산하여 데이터베이스에 반영하는 GUI를 제공 <br> - 사용자 정보와 회사 주식 데이터를 활용하여 매수 후의 금액과 주식 수량을 업데이트                     |
| **BaseMainFrame**    | ● 주식 거래 시뮬레이션 프로그램의 메인 화면을 구성 <br> - 사용자 정보, 거래 내역, 회사 주식 정보 등을 표시하고, 장 마감, 매수/매도, 내 정보 등의 기능을 지원하는 UI를 제공 <br> - CardLayout을 사용하여 여러 패널을 전환하며, 버튼 클릭에 따른 이벤트 처리 구현                |


## Development setup / Database Design

* 본 프로젝트는 주식을 잘 모르는 사람도 투자 시 리스크 관리와 전략 수립의 중요성을 체감할 수 있도록 돕는 프로그램
* 최대 30일의 가상 투자 기간 동안 시장 변화에 맞춰 실제와 유사한 방식으로 주식 거래를 시뮬레이션하여 현실감 있는 투자 경험을 제공

* **프로젝트 구조**
  - 이 프로그램은 Java 기반으로 구성되었으며, JFrame을 활용하여 동적인 프로그램을 구현
  - 다양한 라이브러리를 통해 기능을 확장하고 데이터 처리를 최적화. 특히 주가 변동 그래프를 그리기 위해 xchart 라이브러리를 활용하여 차트 작성
  - 본 프로젝트는 팀 프로젝트로 진행되었으며, 각 팀원들은 맡은 역할에 따라 분담하여 효율적인 작업 분배와 전문성 확보

* **개발 과정**
  - 자바 기능과 DB 연동을 주로 담당하였으며, 초기에는 테스트 가능한 범위의 작은 기능 모듈을 구현하고, 이를 객체지향적으로 역할에 따라 클래스 별로 캡슐화하여 구현
  - 다른 팀원은 SQL 명령문 작성과 GUI를 구현했으며, 위에서 구현한 모듈을 연계하여, 통합된 모든 기능을 Live DB와 연결한 뒤, 최종 검토 수행
    
* **내 역할**
  - 주요 기능들을 먼저 개발한 후 캡슐화하고, 테스트를 위해 콘솔을 활용한 기능 모듈을 구현
  - 테스트를 마친 기능을 팀원이 만든 GUI와 연결하여 기능을 다시 확인하였고 최종적으로 DB와 연결
  - 연결 과정에서 발생한 문제점이나 오류 부분 수정 및 전체적인 코드 조율

* 본 프로젝트는 실제 주식 프로그램과 유사한 시뮬레이션을 개발하는 것을 목표로 하며, 사용자가 주식 시뮬레이션을 통해 다양한 경험을 쌓고, 실제 주식을 경험한 듯한 느낌을 제공하는 데 중점을 둠.

<br>

> **DB 설계**
>
> ![image](https://github.com/user-attachments/assets/f3442b8b-753c-4529-aab7-12b91d03871f)

<br>

## Repository Structure

```sh
└──EnjoyFood_Project/
  ├─ README.md
  ├─ .gitignore
  └─ src/
     ├─ default/
     ├─ DAO/
     ├─ loginUI/
     ├─ MainSystem/
     ├─ mapper/
     ├─ otherPnl/
     ├─ priceGUI/
     ├─ resource/
     ├─ tables/
     └─ ui/

```

<p align="center">
  <h2>Built With</h2>
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">


## Authors
> 프로필 
>
> 이재민 [@깃허브 프로필 페이지](https://github.com/qwer123toy)
> 

