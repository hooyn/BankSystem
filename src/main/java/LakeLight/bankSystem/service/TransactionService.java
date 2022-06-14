package LakeLight.bankSystem.service;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Transaction;
import LakeLight.bankSystem.domain.TransactionStatus;
import LakeLight.bankSystem.dto.TransactionDto;
import LakeLight.bankSystem.repository.AccountRepository;
import LakeLight.bankSystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    /**
     * 입, 출금
     */
    @Transactional
    public void saveTransaction(Long account_id, String info, int amount, TransactionStatus status){
        Account account = accountRepository.findById(account_id);

        Transaction transaction = Transaction.createTransaction(info, amount, account, status);
        transactionRepository.save(transaction);
    }

    /**
     * 거래내역 조회
     */
    @Transactional(readOnly = true)
    public List<TransactionDto> findTransactions(String account_num){
        return transactionRepository.findAll(account_num);
    }
}
