package com.example.demo.listener;

import com.example.demo.entity.User;
import com.example.demo.event.UserCreatedEvent;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private final UserService userService;

    @TransactionalEventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        log.info("handleUserCreatedEvent: {}", event);
        // 检查事务状态
        log.info("Transaction active: {}",
                TransactionSynchronizationManager.isActualTransactionActive());
        log.info("Transaction read-only: {}",
                TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        log.info("Transaction name: {}",
                TransactionSynchronizationManager.getCurrentTransactionName());
        User save = userService.save(User.builder()
                .name("zijie")
                .build());

    }

}
