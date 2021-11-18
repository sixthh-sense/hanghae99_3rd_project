package com.sparta.deep_sea.controller;

import com.sparta.deep_sea.domain.Memory;
import com.sparta.deep_sea.domain.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MemoryDetailController {
    private final MemoryRepository memoryRepository;

    @RequestMapping(value="/details/{id}", method= RequestMethod.GET)
    public String moreMemory(@PathVariable Long id, Model model) {
        Optional<Memory> memory = memoryRepository.findById(id);
        if (!memory.isPresent()) {
            return "redirect:/";
        }
        model.addAttribute("title", memory.get().getTitle());
        model.addAttribute("name", memory.get().getName());
        model.addAttribute("comment", memory.get().getComment());
        return "details";

    }

}
