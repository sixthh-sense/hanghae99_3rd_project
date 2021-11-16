package com.sparta.week03.controller;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoRepository;
import com.sparta.week03.domain.MemoRequestDto;
import com.sparta.week03.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository; // 필수라서 final 공개적으로 내보일 게 아니기에 private
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) { // requestbody: arc로 post 보낼 때 body 부분으로 보내겠다?
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo); // 저장할 곳에 저장하라, memo를.
    }

    @GetMapping("/api/memos")
    public List<Memo> readMemo() { // readMemo 부분은 임의?
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.update(id, requestDto);
        return id;
    }


    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) { // 경로에 있는 변수를 받는다, pathvariable
        memoRepository.deleteById(id);
        return id;
    }


}
