package com.cos.blog.test;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyControllerTest {

    private final BoardRepository boardRepository;

    private final ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get();  // jackson 라이브러리(object를 json으로 리턴) => 모델의 getter를 호출
    }

    @GetMapping("test/reply")
    public List<Reply> GetReply() {
        return replyRepository.findAll();
    }
}
