package com.chervonnaya.library;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@Transactional
@Sql(scripts = "classpath:sql/dml.sql")
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)

public abstract class BaseTest {

    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:latest");

    @BeforeAll
    static void initContainer(){
        try {
            MY_SQL_CONTAINER.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterAll
    static void tearDown() {
        MY_SQL_CONTAINER.stop();
    }


    @DynamicPropertySource
    static void propertyContainer(DynamicPropertyRegistry propertyRegistry){
        propertyRegistry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username",MY_SQL_CONTAINER::getUsername);
        propertyRegistry.add("spring.datasource.password",MY_SQL_CONTAINER::getPassword);
    }

}
