package com.sparta.deep_sea.controller;

import com.sparta.deep_sea.domain.Memory;
import com.sparta.deep_sea.domain.MemoryRepository;
import com.sparta.deep_sea.domain.MemoryRequestDto;
import com.sparta.deep_sea.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;
    private final MemoryRepository memoryRepository;

    // 게시물 작성
    @PostMapping("/memories")
    public Memory createMemory(@RequestBody MemoryRequestDto requestDto) {
        Memory memory = new Memory(requestDto);
        return memoryRepository.save(memory);
    }

    // 전체 게시물 조회(게시)
    @GetMapping("/memories")
    public List<Memory> readMemory() {
        return memoryRepository.findAllByOrderByModifiedAtDesc();
    }

    // 상세 게시물 조회(게시)

    // 게시물 수정
    @PutMapping("/memories/{id}")
    public Long updateMemory(@PathVariable Long id, @RequestBody MemoryRequestDto requestDto) {
        memoryService.update(id, requestDto);
        return id;
    }

    // 게시물 삭제
    @DeleteMapping("/memories/{id}")
    public Long deleteMemory(@PathVariable Long id) {
        memoryRepository.deleteById(id);
        return id;
    }

}
