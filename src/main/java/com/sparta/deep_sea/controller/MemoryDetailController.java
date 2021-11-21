package com.sparta.deep_sea.controller;

import com.sparta.deep_sea.domain.Memory;
import com.sparta.deep_sea.domain.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MemoryDetailController {
    private final MemoryRepository memoryRepository;

    @GetMapping("/detail/{id}")
    public String moreMemory(@PathVariable Long id, Model model) {
        Memory memory = memoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 존재하지 않습니다."));
        System.out.println(model);
        model.addAttribute("title", memory.getTitle());
        model.addAttribute("name", memory.getName());
        model.addAttribute("comment", memory.getComment());
        System.out.println(model);
        return "detail";
    }
}
