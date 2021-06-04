package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성
// @DynamicInsert insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 120) // 123456 => 비밀번호 암호화
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("user")
    // DB는 RoleType이 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다.

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
