package id.my.hendisantika.jpapagination;

import id.my.hendisantika.jpapagination.entity.User;
import id.my.hendisantika.jpapagination.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringDataJpaPaginationApplicationTests {

    static MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:9.2.0"
    );

    @Autowired
    private UserRepository userRepository;

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
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test MySQL Container Is Running")
    void testMySQLContainerIsRunning() {
        assertThat(mysql.isRunning()).isTrue();
    }

    @Test
    @DisplayName("Test Save User")
    void testSaveUser() {
        userRepository.saveAll(List.of(
                new User("Itadori Yuji", "yuji@yopmail.com", "ACTIVE", "TOKYO", "08123456789", "https://randomuser.me/api/portraits/men/85.jpg"),
                new User("Fushiguro Megumi", "megumi@yopmail.com", "ACTIVE", "TOKYO", "08123456700", "https://randomuser.me/api/portraits/men/85.jpg"),
                new User("Satoru Gojo", "gojo@yopmail.com", "ACTIVE", "TOKYO", "08123456701", "https://randomuser.me/api/portraits/men/85.jpg"),
                new User("Suguru Geto", "geto@yopmail.com", "ACTIVE", "TOKYO", "08123456702", "https://randomuser.me/api/portraits/men/85.jpg"),
                new User("Ryoumen Sukuna", "sukuna@yopmail.com", "INACTIVE", "TOKYO", "08123456703", "https://randomuser.me/api/portraits/men/85.jpg")
        ));

//        User users = userRepository.findUsersByEmail("yuji@yopmail.com");
//        List<User> users = userRepository.findUsersByEmail("yuji@yopmail.com");
        List<User> users = userRepository.findAll();

        assertNotNull(users);
        assertEquals(5, users.size());
        assertEquals("Itadori Yuji", users.get(0).getName());
        assertEquals("Fushiguro Megumi", users.get(1).getName());
        assertEquals("Satoru Gojo", users.get(2).getName());
        assertEquals("Suguru Geto", users.get(3).getName());
        assertEquals("Ryoumen Sukuna", users.get(4).getName());
    }

}
