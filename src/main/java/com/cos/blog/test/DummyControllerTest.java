package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입
    private UserRepository userRepository;

    // save 함수는 id를 전달하지 않으면 insert 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert해준다.
    //email, password
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
           return new IllegalArgumentException("수정에 실패횄습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(requestUser);

        // 더티 체킹
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException  e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제 되었습니다. id : " + id;
    }

    // 한페이지당 2건에 데이터 리턴
    @GetMapping("dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return pagingUser;
    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // {id} 주소로 파라메터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4번을 찾으면 DB에서 못찾아오면 null return이 null 문제가됨
        // optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 리턴
        //
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다 id : " + id);
        });

        // user 객체 = 자바 오브젝트
        // 변환 = 웹브라우저 이해할 수 있는 Data <- JASON(Gson 라이브러리)
        // spring boot = MessageConverter 응답시 자동 작동
        //
        return user;
    }

    // http://localhost:8000/blog/dummy/join
    // http의 바디에 username, password, email 데이터 가지고 요청
    @PostMapping("/dummy/join")
    public String Join(User user) {  // key value(약속된 규칙)
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
