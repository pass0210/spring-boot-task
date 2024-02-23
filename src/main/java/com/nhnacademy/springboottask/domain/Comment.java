package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String userId;

    private String content;

    @JoinColumn(name = "taskId")
    @ManyToOne
    private Task task;
}
