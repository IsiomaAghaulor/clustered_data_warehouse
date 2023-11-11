package com.progresssoft.clustered_data_warehouse.services.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.progresssoft.clustered_data_warehouse.dto.CustomResponse;
import com.progresssoft.clustered_data_warehouse.dto.FxDealRequest;
import com.progresssoft.clustered_data_warehouse.entity.FxDeals;
import com.progresssoft.clustered_data_warehouse.exceptions.DuplicateRequestException;
import com.progresssoft.clustered_data_warehouse.repositories.FxDealRequestRepos;
import com.progresssoft.clustered_data_warehouse.serviceImpl.FxDealRequestProcessorService;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

class FxDealRequestProcessorServiceTest {
    private FxDealRequestProcessorService fxDealRequestProcessorService;

    private FxDealRequestRepos fxDealRequestReposMock;

    @BeforeEach
    void setUp() {
        fxDealRequestReposMock = mock(FxDealRequestRepos.class);
        fxDealRequestProcessorService = new FxDealRequestProcessorService(fxDealRequestReposMock);
    }

    @Test
    void testLogRequestFieldsToDb_Success() {
        FxDealRequest request = createFxDealRequest();
        when(fxDealRequestReposMock.save(any(FxDeals.class))).thenReturn(new FxDeals());

        ResponseEntity<Object> responseEntity = fxDealRequestProcessorService.logRequestFieldsToDb(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("00", ((CustomResponse) responseEntity.getBody()).getResponseCode());
    }

    @Test
    void testLogRequestFieldsToDb_DuplicateRequestException() {
        FxDealRequest request = createFxDealRequest();
        when(fxDealRequestReposMock.save(any(FxDeals.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DuplicateRequestException.class,
                () -> fxDealRequestProcessorService.logRequestFieldsToDb(request));
    }

    @Test
    void testLogRequestFieldsToDb_SystemMalfunction() {
        FxDealRequest request = createFxDealRequest();
        when(fxDealRequestReposMock.save(any(FxDeals.class))).thenThrow(PersistenceException.class);

        ResponseEntity<Object> responseEntity = fxDealRequestProcessorService.logRequestFieldsToDb(request);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, responseEntity.getStatusCode());
        assertEquals("55", ((CustomResponse) responseEntity.getBody()).getResponseCode());
    }

    private FxDealRequest createFxDealRequest() {
        FxDealRequest request = new FxDealRequest("123","USD","NGN","", BigDecimal.valueOf(1000.0));
        return request;
    }
}
