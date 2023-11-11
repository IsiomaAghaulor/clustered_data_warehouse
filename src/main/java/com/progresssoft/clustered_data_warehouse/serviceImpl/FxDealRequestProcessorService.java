package com.progresssoft.clustered_data_warehouse.serviceImpl;

import com.progresssoft.clustered_data_warehouse.dto.CustomResponse;
import com.progresssoft.clustered_data_warehouse.dto.FxDealRequest;
import com.progresssoft.clustered_data_warehouse.entity.FxDeals;
import com.progresssoft.clustered_data_warehouse.exceptions.DuplicateRequestException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.progresssoft.clustered_data_warehouse.repositories.FxDealRequestRepos;

@Service
@RequiredArgsConstructor
@Slf4j
public class FxDealRequestProcessorService implements IFxDealRequestProcessorService {
    private static final String SYSTEM_ERROR_CODE = "55";
    private static final String SUCCESS_CODE = "00";
    final FxDealRequestRepos fxDealRequestRepos;
    CustomResponse customResponse;

    @Transactional
    public ResponseEntity<Object> logRequestFieldsToDb(FxDealRequest request) {
        log.info("****** PERSISTING REQUEST FIELDS FOR FX DEAL WITH ID: " + request.dealUniqueId() + " TO DB ******");
        FxDeals fxDeals = new FxDeals();

        fxDeals.setDealUniqueId(request.dealUniqueId());
        fxDeals.setOrderingCurrency(request.orderingCurrency());
        fxDeals.setToCurrency(request.toCurrency());
        fxDeals.setDealAmount(request.dealAmount());
        fxDeals.setDealTimeStamp(request.dealTimeStamp());

        try {
            fxDealRequestRepos.save(fxDeals);
            String successMsg = "FX DEAL INFO PERSISTED SUCCESSFULLY";

            log.info(successMsg);
            customResponse.setResponseCode(SUCCESS_CODE);
            customResponse.setResponseMsg(successMsg);
            customResponse.setResponseDetails(request);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            String errMsg = "ERR: DUPLICATE REQUEST";
            throw new DuplicateRequestException(errMsg);

        } catch (PersistenceException e) {
            String errMsg = "ERR: SYSTEM MALFUNCTION PERSISTING FX DEAL INFO.TRY AGAIN LATER";
            log.info(errMsg);

            customResponse.setResponseCode(SYSTEM_ERROR_CODE);
            customResponse.setResponseMsg(errMsg);
            return new ResponseEntity<>(customResponse, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
