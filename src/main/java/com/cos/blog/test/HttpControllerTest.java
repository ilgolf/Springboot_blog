package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML파일)
// @Controller

// 사용자가 요청 -> 응답(Data)

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    // http://localhost:8000/blog/http/lombok
    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG + "getter" + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "setter" + m.getUsername());

        return "lombokTest 완료";
    }

    // http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(Member m) {

        return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost8080:/http/post
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {

        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost8080:/http/put
    @PutMapping("http/put")
    public String putTest(@RequestBody Member m) {

        return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost8080:/http/delete
    @DeleteMapping("http/delete")
    public String deleteTest() {

        return "delete 요청";
    }
}
