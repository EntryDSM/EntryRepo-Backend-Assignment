package com.practice.board.domain.persistence;

import lombok.*;
import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User user;

    private String title;

    private String content;

    public void modifyTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}