package com.nhnacademy.springboottask.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class CreateProjectRequest {
    String projectState;
    String memberId;
    String projectName;
}
