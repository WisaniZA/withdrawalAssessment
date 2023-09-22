package com.assessment.wisanimabasa.controller.rest;

import com.assessment.wisanimabasa.models.dto.WithdrawalDTO;
import com.assessment.wisanimabasa.service.interfaces.WithdrawalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Withdrawal controller.
 *  @author Wisani Mabasa
 *   @Since 22-sep-2023
 */
@RestController
@RequestMapping("/api/withdrawals")
@CrossOrigin
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    /**
     * Instantiates a new Withdrawal controller.
     *
     * @param withdrawalService the withdrawal service
     */
    @Autowired
    public WithdrawalController(final WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    /**
     * Create withdrawal response entity.
     *
     * @param withdrawalDTO the withdrawal dto
     * @return the response entity
     */
    @PostMapping("/create")
    @Operation(summary = "Create withdrawal", description = "Create a new withdrawal")
    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema =  @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createWithdrawal(final @RequestBody WithdrawalDTO withdrawalDTO) {
        return withdrawalService.createWithdrawal(withdrawalDTO);
    }

}
