CREATE TABLE IF NOT EXISTS MESSAGE (
    message_id bigint NOT NULL AUTO_INCREMENT,
    message varchar(100) NOT NULL,
    PRIMARY KEY (message_id)
);

/*‘message_id’는 MESSAGE 테이블의 Primary key이고 AUTO_INCREMENT를 지정했기 때문에 데이터가 insert 될때 마다 자동으로 증가.
  즉, 이 말의 의미는 애플리케이션 쪽에서 데이터베이스에 데이터를 insert할 때 ‘message_id’ 컬럼에 해당하는 값을 지정해주지 않아야한다는 의미.
  MESSAGE 테이블은 Message 클래스 명과 매핑되고 ‘message_id’ 컬럼은 Message 클래스의 messageId 멤버 변수와 매핑.
  ‘message’ 컬럼은 예상했듯 Message 클래스의 message 멤버 변수와 매핑.*/

//여기부터는 헬로월드 패키지를 위한 sql이 아닌 핵심 어플리케이션을 위한 sql임.

CREATE TABLE IF NOT EXISTS MEMBER(
    MEMBER_ID bigint NOT NULL AUTO_INCREMENT,
    EMAIL varchar(100) NOT NULL UNIQUE,
    NAME varchar(100) NOT NULL,
    PHONE varchar(100) NOT NULL,
    PRIMARY KEY (MEMBER_ID)
);

CREATE TABLE IF NOT EXISTS COFFEE(
    COFFEE_ID bigint NOT NULL AUTO_INCREMENT
    KOR_NAME varchar(100) NOT NULL,
    ENG_NAME varchar(100) NOT NULL,
    PRICE int NOT NULL,
    COFFEE_CODE char(3) NOT NULL,
    PRIMARY KEY (COFFEE_ID)
);

CREATE TABLE IF NOT EXISTS ORDERS (
    ORDER_ID bigint NOT NULL AUTO_INCREMENT,
    MEMBER_ID bigint NOT NULL,
    ORDER_STATUS varchar(20) NOT NULL,
    CREATED_AT datetime NOT NULL,
    PRIMARY KEY (ORDER_ID),
    FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(MEMBER_ID)
);

CREATE TABLE IF NOT EXISTS ORDER_COFFEE (
    ORDER_COFFEE_ID bigint NOT NULL AUTO_INCREMENT,
    ORDER_ID bigint NOT NULL,
    COFFEE_ID bigint NOT NULL,
    QUANTITY int NOT NULL,
    PRIMARY KEY (ORDER_COFFEE_ID),
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID),
    FOREIGN KEY (COFFEE_ID) REFERENCES COFFEE(COFFEE_ID)
);