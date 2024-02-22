package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime deadline;

    @JoinColumn(name = "projectId", nullable = false)
    @ManyToOne
    private Project project;

    @JoinColumn(name = "milestoneId")
    @OneToOne
    private Milestone milestone;

    @JoinColumn(name = "taskState", nullable = false)
    @ManyToOne
    private TaskStatus taskStatus;
}
