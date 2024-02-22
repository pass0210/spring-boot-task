package com.nhnacademy.springboottask.dto.request;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class MemberIdRequest {
    String memberId;
}
