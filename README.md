# Hibernate 예제

[하이버네이트](http://hibernate.org/)는 예전부터 해봐야지 하다가 이번에 한번 직접 사용해봄.

## 준비작업

[H2DB](http://www.h2database.com/)를 다운로드 받아서 서버 모드로 기동한다.

	java -cp h2-1.4.197.jar org.h2.tools.Server

H2 웹 콘솔( localhost:8082 )에 접근해서, 아래 쿼리를 실행해서 테이블을 생성한다.

	CREATE TABLE user_table (
		user_id int(20) NOT NULL,
		user_name varchar(255) NULL,
		created_by VARCHAR (255) NOT NULL,
		created_date DATE NOT NULL,
		PRIMARY KEY (user_id)
	);

끗 (...)

이후는 그냥 코드 실행해보면 됨.

전부터 하이버네이트를 써봐야지 하다가, 하이버네이트만 사용하는 코드를 발견해서 해봄.

## reference

  * [Hibernate Maven Example](https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-maven-example/)

인터넷 검색해서 나오는 하이버네이트 예제들은 열이면 열 모두 Spring + 하이버네이트의 구성이었는데,
이건 마침 하이버네이트만 사용하는 구성이어서 직접 해 보았다.

  * [Hibernate 5 :- org.hibernate.MappingException: Unknown entity](https://stackoverflow.com/questions/32405031/hibernate-5-org-hibernate-mappingexception-unknown-entity)

그런데 하이버네이트 5 기준으로 위 코드를 그대로 진행해보면 ```MappingException``` 예외가
```Unknown entity```라는 메시지를 뿜으면서 죽어버린다. 이것땜에 좀 오랫동안 검색했는데,
위 스택오버플로 답변을 대충 요약하자면,

하이버네이트 5에서는 Configuration 객체를 만들어서 ServiceRegistry 객체를 만든 뒤,
미리 만들어준 Configuration 객체에 ServiceRegistry 객체를 인자로 전달하면,
미리 Configuration 객체를 통해서 읽어들인 매핑 정보를 모두 잃어버린다고 한다.

	※ 매핑 정보가 사라지는 코드의 예
	
	Configuration configuration = new Configuration();
	configuration.configure("hibernate.cfg.xml");
	
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
	
	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

때문에, 아예 ServiceRegistry 객체를 생성할때 환경파일을 전달해주고, Configuration 객체는
새로 생성해서 매핑정보를 잃어버리지 않고 진행할 수 있다고 한다.

	※ 이렇게 코딩하면 매핑 정보가 유지됨
	
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		.configure().loadProperties("hibernate.cfg.xml").build();
	SessionFactory sessionFactory = new Configuration().buildSessionFactory(serviceRegistry);

커밋된 코드에는 이 코드로 적용되어 있다.