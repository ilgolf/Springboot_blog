package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;
}
