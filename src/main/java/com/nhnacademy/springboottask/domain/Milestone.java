package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "milestone")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    @Column(nullable = false)
    private String stepName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JoinColumn(name = "projectId")
    @ManyToOne
    private Project project;
}
