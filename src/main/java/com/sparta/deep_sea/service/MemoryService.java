package com.sparta.deep_sea.service;

import com.sparta.deep_sea.domain.Memory;
import com.sparta.deep_sea.domain.MemoryRepository;
import com.sparta.deep_sea.domain.MemoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    @Transactional
    public Long update(Long id, MemoryRequestDto requestDto) {
        Memory memory = memoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memory.update(requestDto);
        return id;
    }

}
