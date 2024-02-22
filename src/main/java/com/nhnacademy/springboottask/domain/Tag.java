package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@NoArgsConstructor
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
}
