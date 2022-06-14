package LakeLight.bankSystem.controller;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Transaction;
import LakeLight.bankSystem.domain.TransactionStatus;
import LakeLight.bankSystem.dto.TransactionDto;
import LakeLight.bankSystem.service.AccountService;
import LakeLight.bankSystem.service.TransactionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionApiController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    /**
     * 거래 생성 [입금]
     */
    @PostMapping("/transaction/deposit")
    public Result deposit(@RequestBody CreateTransactionRequest request){
        transactionService.saveTransaction(request.getAccount_id(), request.getInfo(), request.getAmount(), TransactionStatus.DEPOSIT);

        return new Result(true, 200, null, "입금 완료");
    }

    /**
     * 거래 생성 [출금]
     */
    @PostMapping("/transaction/withdraw")
    public Result withdraw(@RequestBody CreateTransactionRequest request){
        transactionService.saveTransaction(request.getAccount_id(), request.getInfo(), request.getAmount(), TransactionStatus.WITHDRAW);

        return new Result(true, 200, null, "출금 완료");
    }

    /**
     * 거래 조회 ( 계좌 번호에 따른 )
     */
    @PostMapping("/transactions")
    public Result transactions (@RequestBody SelectTransactionRequest request){
        List<TransactionDto> transactions = transactionService.findTransactions(request.getAccount_number());

        return new Result(true, 200, transactions, "거래 내역 조회");
    }

    @Data
    static class CreateTransactionRequest{
        private Long account_id;
        private String info;
        private int amount;
    }

    @Data
    static class SelectTransactionRequest{
        private String account_number;
    }
}
