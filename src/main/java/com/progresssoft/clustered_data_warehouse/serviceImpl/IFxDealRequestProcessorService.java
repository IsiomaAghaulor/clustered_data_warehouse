package com.progresssoft.clustered_data_warehouse.serviceImpl;

import com.progresssoft.clustered_data_warehouse.dto.FxDealRequest;
import org.springframework.http.ResponseEntity;

public interface IFxDealRequestProcessorService {
     ResponseEntity<Object> validateFxDealsRequestFields(FxDealRequest request);
}
