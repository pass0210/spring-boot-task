package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task_tag")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TaskTag {
    @EmbeddedId
    private Pk pk;

    @MapsId(value = "taskId")
    @JoinColumn(name = "taskId")
    @ManyToOne
    private Task task;

    @MapsId(value = "tagId")
    @JoinColumn(name = "tagId")
    @ManyToOne
    private Tag tag;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "taskId", nullable = false)
        private Long taskId;

        @Column(name = "tagId", nullable = false)
        private Long tagId;
    }
}
