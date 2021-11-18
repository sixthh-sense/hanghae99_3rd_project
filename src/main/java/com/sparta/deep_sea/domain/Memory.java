package com.sparta.deep_sea.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Memory extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id; // 기본적으로 주어지는 id값

    @Column(nullable=false)
    private String title; // 제목

    @Column(nullable=false)
    private String name; // 작성자

    @Column(nullable=false)
    private String comment; // 작성 내용
    // 작성 날짜는 TimeStamped에서 긁어올 수 있을 거고
    // 최신날짜 순으로 정렬하는 건 간이Repository(interface)를 통해 jparepository에서 시간순으로 정렬된 자료 끌어오고
    // "보여주는" 날짜니까 Controller의 GET(mapping)에서 어떤 걸 보여줄지 선언한다.

    public Memory(MemoryRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.comment = requestDto.getComment();
    }

    public void update(MemoryRequestDto requestDto) { // 수정할 때 사용할 기능
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.comment = requestDto.getComment();
    }
}


