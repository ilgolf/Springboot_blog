package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증안된 사용자들이 출입할 수 있는 경로를 auth/** 로 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/css/image

@Controller
public class UserController {

    // 뷰의 요청 경로 지정 GET 방식(가져오는 방식)
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
