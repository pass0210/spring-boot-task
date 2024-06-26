package com.nhnacademy.springboottask.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "project_status")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProjectStatus {
    @Id
    private String projectState;
}
