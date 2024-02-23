package com.nhnacademy.springboottask.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
public class UpdateTaskRequest {
    List<Long> tagIds;
    Long projectId;
    Long milestoneId;
    String taskState;
    String userId;
    String title;
    String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime deadline;
}
