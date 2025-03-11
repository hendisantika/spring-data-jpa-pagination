package id.my.hendisantika.jpapagination.seed;

import com.github.javafaker.Faker;
import id.my.hendisantika.jpapagination.entity.User;
import id.my.hendisantika.jpapagination.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-jpa-pagination
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 12/03/25
 * Time: 06.04
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class DbSeeder {

    private static final Faker faker = new Faker();

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            /* generate 100 users using faker */
            for (int i = 0; i < 100; i++) {
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
//                        faker.random().nextLong(),
                        name,
                        email,
                        status,
                        address,
                        phone,
                        imageUrl
                ));
            }
        };
    }
}
