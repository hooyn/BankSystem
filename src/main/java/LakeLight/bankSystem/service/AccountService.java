package LakeLight.bankSystem.service;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.dto.AccountDto;
import LakeLight.bankSystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    /**
     * 계좌 개설
     */
    @Transactional
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    /**
     * 회원에 따른 계좌 조회 (간단한 목록)
     */
    @Transactional(readOnly = true)
    public List<AccountDto> findAccounts(String number){
        return accountRepository.findAll(number);
    }

    /**
     * 계좌 번호로 계좌 조회
     */
    @Transactional(readOnly = true)
    public AccountDto findAccount(String account_num){
        return accountRepository.findByAccountNum(account_num);
    }

    /**
     * Account_Id에 따른 계좌 조회
     */
    @Transactional(readOnly = true)
    public Account findOne(Long id){
        return accountRepository.findById(id);
    }

    /**
     * 계좌 삭제
     */
}
