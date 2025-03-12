package id.my.hendisantika.jpapagination.controller;

import com.github.javafaker.Faker;
import id.my.hendisantika.jpapagination.entity.User;
import id.my.hendisantika.jpapagination.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.StringUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-jpa-pagination
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 12/03/25
 * Time: 06.25
 * To change this template use File | Settings | File Templates.
 */
@AutoConfigureWebTestClient
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    static MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:9.2.0"
    );

    @Autowired
    private UserRepository userRepository;

    private static final Faker faker = new Faker();
    @LocalServerPort
    private Integer port;

    @BeforeAll
    static void beforeAll() {
        mysql.start();
    }

    @AfterAll
    static void afterAll() {
        mysql.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        userRepository.deleteAll();
    }

    private List<User> initData() {
        List<User> userList = new ArrayList<>();
        /* generate 100 users using faker */
        for (int i = 0; i < 10; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            String name = String.format("%s %s",
                    firstName,
                    lastName
            );

            String email = String.format("%s@contact.com",
                    StringUtils.trimAllWhitespace(name.trim().toLowerCase()));

            String status = faker.options().option("ACTIVE", "INACTIVE", "PENDING", "BANNED");

            String address = faker.address().fullAddress();
            String phone = faker.phoneNumber().phoneNumber();
            String imageUrl = String.format(
                    "https://randomuser.me/api/portraits/%s/%s.jpg",
                    "men",
                    faker.random().nextInt(1, 100)
            );

            userRepository.save(new User(
                    name,
                    email,
                    status,
                    address,
                    phone,
                    imageUrl
            ));
        }

        return userList = userRepository.findAll();
    }

    @Test
    @DisplayName("Should Get All Users")
    void shouldGetAllUsers() {
        List<User> users = initData();

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(200)
//                .body(".", hasSize(100));
                .body("message", equalTo("Users Retrieved")
//                        "totalElements", equalTo("100")
                );
//                .body("totalElements", equalTo("100"));
    }

    @Test
    @DisplayName("Should Contains 10 Users")
    void shouldContain10lUsers() {
        List<User> users = initData();

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(200)
                .body("data.page.totalElements", equalTo(users.size()))
                .body("data.page.numberOfElements", equalTo(10));
    }
}
