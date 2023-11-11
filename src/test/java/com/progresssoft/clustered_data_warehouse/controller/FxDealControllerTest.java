package com.progresssoft.clustered_data_warehouse.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.progresssoft.clustered_data_warehouse.dto.FxDealRequest;
import com.progresssoft.clustered_data_warehouse.serviceImpl.FxDealRequestProcessorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

class FxDealControllerTest {

    @Mock
    private FxDealRequestProcessorService fxDealRequestProcessorService;

    @InjectMocks
    private FxDealController fxDealController;

    @Test
    void testCreateFxDeal() {
        // Mock FxDealRequest
        FxDealRequest fxDealRequest = new FxDealRequest("123","USD","NGN","", BigDecimal.valueOf(1000.0));
        //fxDealRequest.dealUniqueId("123");

        // Mock FxDealRequestProcessorService's behavior
        when(fxDealRequestProcessorService.logRequestFieldsToDb(any(FxDealRequest.class)))
                .thenReturn(new ResponseEntity<>("Request processed successfully", HttpStatus.OK));

        // Perform the test
        ResponseEntity<Object> responseEntity = fxDealController.createFxDeal(fxDealRequest);

        // Verify interactions and assert the expected result
        verify(fxDealRequestProcessorService, times(1)).logRequestFieldsToDb(eq(fxDealRequest));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Request processed successfully", responseEntity.getBody());
    }
}