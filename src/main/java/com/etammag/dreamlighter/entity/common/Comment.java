package com.etammag.dreamlighter.entity.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {

    private Long hotId;

    private String content;

    private String name;

    private String photo;

    private LocalDateTime time;

}
