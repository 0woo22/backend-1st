package com.github.supercodinpj1.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDto {
    private String title;
    private String content;
    private String author;
}
