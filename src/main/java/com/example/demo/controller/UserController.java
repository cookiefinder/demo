package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.event.UserCreatedEvent;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/users")
    public void save(User user) {
        log.info("save user: {}", user);
        User save = userService.save(user);
        log.info("saved user {}", save.getId());
    }

    @GetMapping("/test")
    @Transactional
    public void test() {
        eventPublisher.publishEvent(new UserCreatedEvent());
    }

    @GetMapping("/test2")
    @Transactional
    public void test2() {
        User save = userService.save(User.builder().name("zijie2").build());
        log.info("saved user {}", save.getId());
    }

}
