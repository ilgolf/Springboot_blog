package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// 자동으로 Bean 등록이됨
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
