package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean을 틍록을 해줌. IOC를해준다.
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void join(User user) {
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
}
