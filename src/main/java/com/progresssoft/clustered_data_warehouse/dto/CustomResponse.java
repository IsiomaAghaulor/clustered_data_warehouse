package com.progresssoft.clustered_data_warehouse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {
    private String responseCode;
    private String responseMsg;
    private Object responseDetails;
}
