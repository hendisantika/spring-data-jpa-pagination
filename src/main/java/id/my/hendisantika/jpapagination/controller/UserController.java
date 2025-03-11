package id.my.hendisantika.jpapagination.controller;

import id.my.hendisantika.jpapagination.entity.HttpResponse;
import id.my.hendisantika.jpapagination.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-jpa-pagination
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 12/03/25
 * Time: 06.06
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<HttpResponse> getUsers(@RequestParam Optional<String> name,
                                                 @RequestParam Optional<Integer> page,
                                                 @RequestParam Optional<Integer> size) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        //throw new RuntimeException("Forced exception for testing");
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalTime.now().toString())
                        .data(
                                Map.of("page",
                                        userService.getUsers(
                                                name.orElse(""),
                                                page.orElse(0), /* if page is not specified, default to 0 */
                                                size.orElse(10) /* if size is not specified, default to 10 */
                                        )
                                ))
                        .message("Users Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());

    }
}
