package com.sparta.deep_sea.controller;

import com.sparta.deep_sea.Dto.SignupRequestDto;
import com.sparta.deep_sea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }


    // 회원가입
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) { // Dto에서 받아서
        userService.registerUser(requestDto); // Service의 method를 거쳐
        return "redirect:/user/login"; // login창으로 간다. 주소라서 String?
    }
}
