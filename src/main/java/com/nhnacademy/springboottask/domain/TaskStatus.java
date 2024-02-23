package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_status")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TaskStatus {
    @Id
    private String taskState;
}
