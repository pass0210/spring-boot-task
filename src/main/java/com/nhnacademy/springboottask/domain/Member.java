package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "member")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Member {
    @EmbeddedId
    private Pk pk;

    @MapsId(value = "projectId")
    @JoinColumn(name = "projectId")
    @ManyToOne
    private Project project;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "userId", nullable = false)
        private String userId;
        @Column(name = "projectId", nullable = false)
        private Long projectId;
    }
}
