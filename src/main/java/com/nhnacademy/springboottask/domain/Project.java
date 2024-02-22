package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "project")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String projectName;

    @JoinColumn(name = "projectState")
    @ManyToOne
    private ProjectStatus projectStatus;
}
