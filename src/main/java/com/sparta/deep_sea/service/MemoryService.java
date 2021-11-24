package com.sparta.deep_sea.service;

import com.sparta.deep_sea.Dto.MemoryRequestDto;
import com.sparta.deep_sea.domain.Memory;
import com.sparta.deep_sea.domain.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;


//    public Memory createMemory(MemoryRequestDto requestDto, Long userId) {
//        Memory memory = new Memory(requestDto, userId);
//        memoryRepository.save(memory);
//        return memory;
//    }



    @Transactional
    public Long update(Long id, MemoryRequestDto requestDto) {
        Memory memory = memoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memory.update(requestDto);
        return id;
    }

}
