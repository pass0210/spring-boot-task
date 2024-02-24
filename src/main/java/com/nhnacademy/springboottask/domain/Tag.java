package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false)
    private String tagName;

    @JoinColumn(name = "projectId")
    @ManyToOne
    private Project project;
}
