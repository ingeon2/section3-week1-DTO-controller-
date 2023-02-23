DTO 클래스는 API 계층에서 요청 데이터를 전달 받고, 응답 데이터를 전송하는것(in controller)이 주 목적인 반면에   
Entity 클래스는 서비스 계층에서 데이터 액세스 계층과 연동하여 비즈니스 로직의 결과로 생성된 데이터를 다루는 것(in Member, Coffee)이 주 목적입니다.  


DTO 클래스와 엔티티 클래스의 역할 분리가 필요한 이유  
이처럼 DTO 클래스와 엔티티(Entity) 클래스를 매핑해서 변환하는 이유는 무엇인지 한번 생각해봅시다.  
  
DTO와 엔티티 클래스를 매핑해서 사용하는 여러가지 이유가 있지만 그 중에서 대표적인 이유는 아래와 같습니다.  
  
  
✔ 계층별 관심사의 분리  
  
우선 서로 사용되는 계층이 다릅니다. 따라서 기능에 대한 관심사가 다릅니다.  
  
DTO 클래스는 API 계층에서 요청 데이터를 전달 받고, 응답 데이터를 전송하는것이 주 목적인 반면에   
Entity 클래스는 서비스 계층에서 데이터 액세스 계층과 연동하여 비즈니스 로직의 결과로 생성된 데이터를 다루는 것이 주 목적입니다.  
  
  
굳이 Java의 Object Mapping 관점으로 생각하지 않아도 하나의 클래스나 메서드 내에서 여러 개의 기능들을 구현하고 있는 것은 객체 지향 코드 관점에서도 리팩토링 대상이 된다는 사실을 기억하면 좋을 것 같습니다.  
  
  
✔ 코드 구성의 단순화  
  
우리가 아직 JPA 같은 데이터 액세스 기술을 배우지 않았기 때문에 현실감 있게 와닿지 않을 수 있지만 DTO 클래스에서 사용하는 유효성 검사 애너테이션이 Entity 클래스에서 사용이 된다면 JPA에서 사용하는 애너테이션과 뒤섞인 상태가 되어 유지보수하기 상당히 어려운 코드가 됩니다.  
  
이 부분은 JPA 같은 데이터 액세스 기술을 배우게되면 조금 더 현실감있게 와 닿을거라 생각합니다.  
  
  
✔ REST API 스펙의 독립성 확보  
  
데이터 액세스 계층에서 전달 받은 데이터로 채워진 Entity 클래스를 클라이언트의 응답으로 그대로 전달하게되면 원치 않는 데이터까지 클라이언트에게 전송될 수 있습니다.  
  
대표적인 예가 바로 회원의 로그인 패스워드입니다.  
  
DTO 클래스를 사용하면 회원의 로그인 패스워드 같은 정보를 클라이언트에게 노출하지 않고, 원하는 정보만 제공할 수 있습니다.

response, exception, advice 패키지 추가(예외처리 생성)



핵심 포인트  
Controller 클래스 레벨에서 @ExceptionHandler 애너테이션을 사용하면 해당 Controller에서 발생하는 예외를 처리할 수 있다.  
필요한 Error 정보만 담을 수 있는 Error 전용 Response 객체를 사용하면 클라이언트에게 조금 더 친절한 에러 정보를 제공할 수 있다.  
@ExceptionHandler 애너테이션 방식은 Controller마다 동일하게 발생하는 예외 처리에 대한 중복 코드가 발생할 수 있다.  
@ExceptionHandler 애너테이션 방식은 다양한 유형의 예외를 처리하기에는 적절하지 않은 방식이다.  
(그래서 컨트롤러에 @ExceptionHandler 매서드 하나하나 추가하는 대신 GlobalExceptionAdvice 클래스에서 매핑하도록 해줌. By @RestControllerAdvice)    
  
@RestControllerAdvice 애너테이션을 추가한 클래스를 이용하면 예외 처리를 공통화  
  
핵심 포인트  
@RestControllerAdvice 애너테이션을 추가한 클래스를 이용하면 예외 처리를 공통화 할 수 있다.  
@RestControllerAdvice 애너테이션을 사용하면 JSON 형식의 데이터를 Response Body로 전송하기 위해 ResponseEntity로 래핑할 필요가 없다.  
@ResponseStatus 애너테이션으로 HTTP Status를 대신 표현할 수 있다.  
  
핵심 포인트  
체크 예외(Checked Exception)는 예외를 잡아서(catch) 체크한 후에 해당 예외를 복구 하든가 아니면 회피를 하든가 등의 어떤 구체적인 처리를 해야하는 예외이다.  
언체크 예외(Unchecked Exception)는 예외를 잡아서(catch) 해당 예외에 대한 어떤 처리를 할 필요가 없는 예외를 의미한다.  
RuntimeException을 상속한 예외는 모두 언체크 예외(Unchked Exception)이다.  
RuntimeException을 상속해서 개발자가 직접 사용자 정의 예외(Custom Exception)를 만들 수 있다.  
사용자 정의 예외(Custom Exception)를 정의해서 서비스 계층의 비즈니스 로직에서 발생하는 다양한 예외를 던질 수 있고, 던져진 예외는 Exception Advice에서 처리할 수 있다.  
@ResponseStatus 애너테이션은 고정된 예외를 처리할 경우에 사용할 수 있다.  
HttpStatus가 동적으로 변경되는 경우에는 ResponseEntity 를 사용한다.  
  
  
  
순전히 db실험을 위해 hello_world 패키지 추가해줬다.  
추가 이후 잘 작동되는것 확인하고, db를 이용하기 위해 수정사항 생겼다.
  
  
여기부터는 jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc jdbc  
SQL 쿼리문이 직접적으로 포함이 되는 방식은 과거부터 많이 사용하던 방식이고, 현재도 사용이 되고 있긴하지만 (마이바틱스, just SQL문으로만.)  
Java 진영에서는 SQL 중심의 기술에서 객체(Object) 중심의 기술로 지속적으로 이전을 하고 있는 추세 (jdbc, jpa)  
  
  
주문(Order) 엔티티 클래스  
Order 클래스와 Coffee 클래스는 N 대 N의 관계를 가지기 때문에 N 대 N의 관계를 1 대 N의 관계로 만들어주는 List<OrderCoffee>를 멤버 변수로 추가했습니다.  
  
커피(Coffee) 엔티티 클래스  
Coffee클래스와 Order 클래스는 N 대 N의 관계를 가지기 때문에 N 대 N의 관계를 1 대 N의 관계로 만들어주는 List<OrderCoffee>를 멤버 변수로 추가했습니다.  
  
주문_커피(OrderCoffee) 테이블  
Order 클래스와 Coffee 클래스가 N 대 N 관계이므로 두 클래스의 관계를 각각 1 대 N의 관계로 만들어주기 위한 OrderCoffee 클래스가 추가 되었습니다.  
주문하는 커피가 한 잔 이상일 수 있기때문에 quantity(주문 수량) 멤버 변수를 추가했습니다.  


기억해야 될 내용은 다음과 같습니다. (엔티티, 자바 코드)  
클래스들은 객체 간에 참조가 가능하기때문에 이 객체 참조를 사용해서 외래키의 기능을 대신함.  
  
Member 클래스와 Order 클래스는 1 대 N의 관계이다.  
1에 해당되는 Member 클래스는 N에 해당되는 Order 클래스의 객체를 참조할 수 있도록 List를 멤버 변수로 가진다.  
Order 클래스와 Coffee 클래스는 N 대 N의 관계이므로, 1 대 N과 N 대 1의 관계로 변환되었다.  
1에 해당되는 Order 클래스는 N에 해당되는 OrderCoffee 클래스의 객체를 참조할 수 있도록 List를 멤버 변수로 가진다.  
1에 해당되는 Coffee 클래스는 N에 해당되는 OrderCoffee 클래스의 객체를 참조할 수 있도록 List를 멤버 변수로 가진다.  
  
구현 코드와 애그리거트 객체 매핑 규칙  
(1) 모든 엔티티 객체의 상태는 애그리거트 루트를 통해서만 변경할 수 있다.    
(회원의 애그리거트 루트는 회원정보, 주문의 애그리거트 루트는 주문정보를 통해 다른 엔티티에 접근... 이런식)     
이유는..?  
(‘주문 정보’라는 애그리거트 루트를 먼저 거쳐서 ‘음식이 이미 다 만들어졌는지 아직 조리 중인지’ 등의 규칙을 잘 검증한 후에  
검증에 통과하면 ‘배달 주소 정보’ 엔티티의 상태를 업데이트 하도록 해서 도메인 규칙의 일관성을 유지하도록 하는 것.)  
  
(2) 하나의 동일한 애그리거트 내에서의 엔티티 객체 참조  
동일한 하나의 애그리거트 내에서는 엔티티 간에 객체로 참조한다.  
  
(3) 애그리거트 루트 대 애그리거트 루트 간의 엔티티 객체 참조  
애그리거트 루트 간의 참조는 객체 참조 대신에 ID로 참조한다.  
1대1과 1대N 관계일 때는 테이블 간의 외래키 방식과 동일하다.  
N대N 관계일 때는 외래키 방식인 ID 참조와 객체 참조 방식이 함께 사용된다.  
  
당연히 어렵고, 코드를 보면서 구체적으로 이해하기.  
  
  
기억해야 될 내용은 다음과 같습니다. (테이블, sql)  
  
MEMBER 테이블과 ORDERS 테이블은 1 대 N의 관계이다.  
1에 해당되는 MEMBER 테이블과 N에 해당되는 ORDERS 테이블은 ORDERS에 추가된 member_id 외래키(Foreign key)로 조인할 수 있다.  
ORDERS 테이블과 COFFEE 테이블은 N 대 N의 관계이므로, 1 대 N과 N 대 1의 관계로 변환되었다.  
1에 해당되는 ORDERS 테이블과 N에 해당되는 ORDER_COFFEE 테이블은 ORDER_COFFEE 테이블에 추가된 order_id 외래키(Foreign key)로 조인할 수 있다.  
1에 해당되는 COFFEE 테이블과 N에 해당되는 ORDER_COFFEE 테이블은 ORDER_COFFEE 테이블에 추가된 coffee_id 외래키(Foreign key)로 조인할 수 있다.  
  
핵심 포인트  
테이블 간의 관계는 외래키를 통해서 이루어지며, 클래스들 간의 관계는 해당 클래스의 객체 참조를 통해서 이루어진다.  
AggregateReference 클래스는 다른 애그리거트 루트 간의 참조 역할을 한다.  
AggregateReference 클래스는 테이블의 외래키처럼 다른 객체의 ID 값을 참조할 수 있도록 해준다.  
애그리거트 객체 매핑 규칙  
모든 엔티티 객체의 상태는 애그리거트 루트를 통해서만 변경할 수 있다.  
하나의 동일한 애그리거트 내에서의 엔티티 객체 참조  
동일한 하나의 애그리거트 내에서는 엔티티 간에 객체로 참조한다.  
애그리거트 루트 대 애그리거트 루트 간의 엔티티 객체 참조  
애그리거트 루트 간의 참조는 객체 참조 대신에 ID로 참조한다.  
1대N 관계일 때는 AggregateReference 를 사용할 수 있다.  
즉, 테이블 간의 외래키 방식과 동일하다.  
N대N 관계일 때는 참조할 테이블에 해당되는 클래스의 @Id 필드를 멤버 변수로 가지는 별도의 참조 클래스를 사용한다.  
  
  
oreder 패키지의 entitiy 패키지에 Order 클래스 구현을 통해 커피와 멤버의 관계를 설정해놓았고,(주석 존재)  
그에 따라 리소스 패키지 안의 schema.sql에 sql문도 작성해 놓았다.  
Repositoty 인터페이스(커피, 멤버 패키지 안에 존재) 에서 주석에 쿼리매서드 설명.  
그다음 할 일은 db 생성했으니,  
대충 짜뒀던 service,컨트롤러,dto 클래스들 갈아엎었음.(양이 방대하니 주석 달아놓았음.)  
여기서 이거 잘 이해하기. 인터페이스로만 구현한 repository가 어떻게 service에선 구체 클래스로 떡하니 사용되는걸까?  
Spring Data JDBC에서 내부적으로 Java의 리플렉션 기술과 Proxy 기술을 이용해서 MemberRepository 인터페이스의 구현 클래스 객체를 생성.  
주석은 멤버서비스에 달아놨고, 나머지는 깨끗하게 유지하려고 노력했음.(있긴 있음)  
  
OrderController 변화 내용  
우리가 이 전 유닛까지는 데이터베이스를 연동하지 않았기 때문에 주문 정보 등록 시,  
OrderController에서 OrderService의 createOrder(order)를 호출해 등록할 주문을 전달하고,  
이렇게 전달한 Order 객체를 createOrder(order)의 리턴 값으로 그대로 리턴하도록 했습니다.  
그런데 이제는 상황이 다릅니다.  
등록할 주문 정보는 데이터베이스에 저장이 되고, ORDER 테이블에 하나의 row로 저장이 됩니다.  
즉, ORDER_ID라는 고유한 식별자(기본키)를 가지는 진정한 주문 정보로써의 역할을 합니다.  


핵심 포인트  
Spring Data JDBC의 CrudRepository 인터페이스를 상속하면 CrudRepository에서 제공하는 CRUD 메서드를 사용할 수 있다.  
Spring Data JDBC에서는 SQL 쿼리를 대신하는 다양한 쿼리 메서드(Query Method) 작성을 지원한다.  
Spring Data JDBC에서 지원하는 쿼리 메서드의 정의가 어렵다면 @Query 애너테이션을 이용해서 SQL 쿼리를 직접 작성할 수 있다.  
회원 정보, 커피 정보 등의 리소스를 데이터베이스에 Insert할 경우 이미 Insert된 리소스인지 여부를 검증하는 로직이 필요하다.  
Optional을 이용하면 데이터 검증에 대한 로직을 간결하게 작성할 수 있다.  
복잡한 DTO 클래스와 엔티티 클래스의 매핑은 Mapper 인터페이스에 default 메서드를 직접 구현해서 개발자가 직접 매핑 로직을 작성해줄 수 있다.  
  
  
레퍼런스 코드와 비교해서 찾아보니,  
MemberMapper, CoffeeMapper 인터페이스에서 MapStruct(스프링 부트) 가 구체 클래스를 만들 때 문제가 생긴다는 것 캐치됨.  
generate 패키지에서 해당 인터페이스들의 구현클래스를 지우고 다시 컴파일시 구현 클래스가 자동으로 만들어짐.  
근데 또 CoffeeMapperImpl 클래스는 잘 만들어지는데 MemberMapperImpl 클래스는 잘 안만들어지더라고.  
responsedto의 애너테이션은 맞았는데, build.gradle에서 문제가 있었던 모양.  
build.gradle 에서 Lombok이 MapStruct보다 윗줄에 있어야 함.  
이렇게 문제 해결 장장 5시간..  (https://velog.io/@gwichanlee/MapStruct-%EB%AF%B8%EC%9E%91%EB%8F%99%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)

에러 로그에서는 sql문제인것처럼 나왔지만, responsedto 클래스를 잘못 구현해(제어자) get set매서드를 사용해야 하는데 new 키워드를 사용하여 문제가 이렇게 됨.  
제어자 똑바로 사용할것.  
