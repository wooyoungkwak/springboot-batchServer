# Getting Started

### Dependency Package

의존성 패키지 :

* [Batch]()
* [QueryDSL]()
* [RDBMS - MSSQL]()

###  Guides

적용된 패키지 와 간략한 설명:

* [Batch 적용]()
  1. step 의 처리는 reader / processor / writer 방식 적용 하였음.
  2. reader 는 JdbcCursorItemReader 를 사용하였음. ( reader 종류는 cursor type 방식 과 paging 방식 이 있음.)
  3. @StepScope 를 사용하여 1개의 step 처리 과정이 다른 step 처리 과정에 영향을 주지 않도록 영역 처리 하였음.
  4. JobParameter 사용
  ```
     > @JopScope 인 경우와 @StepScope 에서 사용 가능
   
     > @JobScope 표현식 
       @Bean
       @JobScope
       public step myStep(@Value("#{jobParameters[keyName]}") Object keyName) { ... }
  
     > @StepScope 표현식 
       @Bean
       @SobScope
       public MyItemReader<> myItemReader(@Value("#{jobParameters[keyName]}") Object keyName) { ... }
  
     > 예> 
       ~ (@Value("#{jobParameters[times]}") long times) { ... }
  ```

  * [Job running]()
    1. application.yml 에서 "debug" 모드에서는 자동으로 실행 되지 않도록 "spring.batch.job.enable=false" 처리 되어 있음.
    2. 시작하기 위해서는 다음과 같은 url 을 전달 해야한다. 
        >   (post) "http://localhost:8080/start" + { "key" : "temp"} 
    3. 시작하기 위해서는 다음과 같은 url 을 전달 해야한다.
        >   (post) "http://localhost:8080/stop" + {"id" : 1}
    4job 실행 상태 정보를 확인 하기 위해 다음과 같은 url 을 전달 해야한다.
        >   (post) "http://localhost:8080/jobStatus"
    
### Reference 

참고 내용 :

* []()

