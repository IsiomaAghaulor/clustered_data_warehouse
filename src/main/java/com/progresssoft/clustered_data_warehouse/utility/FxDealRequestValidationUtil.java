package com.progresssoft.clustered_data_warehouse.utility;

import org.springframework.stereotype.Component;

@Component
public class FxDealRequestValidationUtil {
    public Boolean validateNonNullField(String field){
        return (!field.isEmpty());
    }
}
