# spring-data-jpa

jpa 와 mybatis 를 함께 사용할때
JpaTransactionManager로 jpa 안에 mybatis를 합칠 수 있다고 함

1. 패키지
 - MapperScan 에 annotationClass 속성으로 Mapper.class 를 주고, 인터페이스에 @Mapper 를 사용
2. @Transactional
 - name을 명시적으로 해 줘야 한다고 하는데..... 안 그럼 rollback 이 안된다고 하는데... 테스트 해 봐야 함