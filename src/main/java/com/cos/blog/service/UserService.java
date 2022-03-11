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

    @Transactional(readOnly = true)
    public User find_user(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("없는 회원입니다."));
    }

    @Transactional
    public void join(User user) {
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);

        userRepository.save(user);
    }

    @Transactional
    public void userModify(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        // select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!!
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
        User persistance = userRepository.findById(user.getId()).orElseThrow
                (() -> new IllegalArgumentException("회원찾기 실패"));

        // Validate 체크 => ouath필드에 값이 없으면 수정 가능
        if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }
        // 회원수정 함수 종료시 = 서비스 종료 = 트랜스 종료 = commit 자동으로됨
        // 영속화된 persistance객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
    }
}
