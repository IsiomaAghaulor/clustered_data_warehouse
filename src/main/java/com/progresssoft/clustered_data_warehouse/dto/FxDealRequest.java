package com.progresssoft.clustered_data_warehouse.dto;

import com.progresssoft.clustered_data_warehouse.exceptions.InvalidRequestFieldException;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public record FxDealRequest(
        @NotNull(message = "DEAL UNIQUE ID IS REQUIRED") String dealUniqueId,
        @NotNull(message = "ORDERING CURRENCY IS REQUIRED") String orderingCurrency,
        @NotNull(message = "TO CURRENCY IS REQUIRED") String toCurrency,
        @NotNull(message = "DEAL TIME STAMP IS REQUIRED") String dealTimeStamp,
        @NotNull(message = "DEAL AMOUNT IS REQUIRED") BigDecimal dealAmount) {

    public FxDealRequest {
        if (dealUniqueId == null || dealUniqueId.isEmpty()) {
            throw new InvalidRequestFieldException("ENTER DEAL UNIQUE ID");
        }

        if (orderingCurrency == null || orderingCurrency.isEmpty()) {
            throw new InvalidRequestFieldException("ENTER ORDERING CURRENCY");

        }

        if (toCurrency == null || toCurrency.isEmpty()) {
            throw new InvalidRequestFieldException("ENTER TO CURRENCY");
        }

        if (dealTimeStamp == null || dealTimeStamp.isEmpty()) {
            throw new InvalidRequestFieldException("ENTER TIME STAMP");

        }

        if (dealAmount == null || dealAmount.compareTo(BigDecimal.ZERO) < 1) {
            throw new InvalidRequestFieldException("INVALID AMOUNT INPUTTED");

        }

    }
}
