package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 빈 등록
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로 채는데
    // password 부분처리는 알아서 함.
    // username이 DB에 있는지만 확인해 주면 됨됨
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User principal = userRepository.findByUsername(username)
               .orElseThrow(() -> {
                   return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);
               });
       return new PrincipalDetail(principal); // 시큐리티 세션의 유저 정보가 저장이 됨
    }
}
