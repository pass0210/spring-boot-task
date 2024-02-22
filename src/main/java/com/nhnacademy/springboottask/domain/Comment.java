package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
}
