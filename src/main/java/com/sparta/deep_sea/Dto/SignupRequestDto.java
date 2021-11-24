package com.sparta.deep_sea.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto { // 회원가입란에서 user가 입력한 걸 받아오는 정보
    private String username;
    private String password;
    private String email;
    private boolean admin = false; // 얘는 정확히 뭔지 모르겠음
    private String adminToken = ""; // 이쪽도
}
