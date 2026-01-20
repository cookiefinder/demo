package com.example.demo.listener;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.event.UserCreatedEvent;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private final UserController userController;
    private final UserService userService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation =  Propagation.REQUIRES_NEW)
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        log.info("handleUserCreatedEvent: {}", event);
        userController.save(User.builder()
                .name("zijie")
                .build());
    }

}
