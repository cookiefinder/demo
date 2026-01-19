package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        // 检查事务状态
        log.info("Transaction active: {}",
                TransactionSynchronizationManager.isActualTransactionActive());
        log.info("Transaction read-only: {}",
                TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        log.info("Transaction name: {}",
                TransactionSynchronizationManager.getCurrentTransactionName());
        return userRepository.save(user);
    }

}
