package com.nhnacademy.springboottask.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class CommentRequest {
    String userId;
    String content;
}
