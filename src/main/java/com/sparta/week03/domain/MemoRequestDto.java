package com.sparta.week03.domain;

import lombok.Getter;

@Getter
public class MemoRequestDto { // 여기엔 final 넣는 거 아님(?)
    private String username;
    private String contents;
}
