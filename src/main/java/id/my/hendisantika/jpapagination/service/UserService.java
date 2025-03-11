package id.my.hendisantika.jpapagination.service;

import id.my.hendisantika.jpapagination.entity.User;
import id.my.hendisantika.jpapagination.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-jpa-pagination
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 12/03/25
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<User> getUsers(String name, int page, int size) {
        log.info("Fetching users for page {} of size {}", page, size);
        return userRepository.findByNameContaining(name, PageRequest.of(page, size));
    }
}
