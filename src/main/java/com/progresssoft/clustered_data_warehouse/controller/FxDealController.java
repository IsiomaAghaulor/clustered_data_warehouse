package com.progresssoft.clustered_data_warehouse.controller;

import com.progresssoft.clustered_data_warehouse.dto.FxDealRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.progresssoft.clustered_data_warehouse.serviceImpl.IFxDealRequestProcessorService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fxDealProcessor")
@Slf4j
public class FxDealController {
    final IFxDealRequestProcessorService fxDealRequestProcessorService;

    @PostMapping("/api/v1/process-fxDeal")
    public ResponseEntity<Object> createFxDeal(@RequestBody @Valid FxDealRequest fxDeals){
        log.info("******** FX DEAL REQUEST RECEIVED FOR DEAL UNIQUE ID: " + fxDeals.dealUniqueId() +" ********");
        return fxDealRequestProcessorService.logRequestFieldsToDb(fxDeals);
    }

}
