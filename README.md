# spring-data-jpa

jpa 와 mybatis 를 함께 사용할때
JpaTransactionManager로 jpa 안에 mybatis를 합칠 수 있다고 함

1. 패키지
 - MapperScan 에 annotationClass 속성으로 Mapper.class 를 주고, 인터페이스에 @Mapper 를 사용
2. @Transactional
 - spring data jpa 에서는 안되는 경우가 있네, 어떻게 보면 당연한거 같기도 하고..... 복잡하네...