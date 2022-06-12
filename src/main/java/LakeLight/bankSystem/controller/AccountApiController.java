package LakeLight.bankSystem.controller;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.AccountDto;
import LakeLight.bankSystem.service.AccountService;
import LakeLight.bankSystem.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;
    private final MemberService memberService;

    /**
     * 계좌 생성
     */
    @PostMapping("/account")
    public Result saveAccount(@RequestBody CreateAccountRequest request){
        Member member = memberService.findOne(request.getMember_id());
        Account account = new Account(member,
                request.getBank_name(),
                request.getName(),
                request.getAccount_number(),
                request.getPassword(),
                0);
        accountService.createAccount(account);

        return new Result(true, 200, null, "계좌 개설");
    }
    //-> 무조건 Dto로 받는건 아니구나
    //-> Repository에서는 엔티티를 반환하고
    //-> API를 만들 때 Dto로 바꾸는게 좋다.

    /**
     * 계좌 조회 (주민번호)
     */
    @PostMapping("/accounts")
    public Result accounts(@RequestBody SelectAccountsRequest request){
        List<AccountDto> accounts = accountService.findAccounts(request.getSocial_security_number());
        return new Result(true, 200, accounts, "계좌 조회");
    }

    /**
     * 계좌 조회 (계좌번호)
     */
    @PostMapping("/account/account_number")
    public Result accounts(@RequestBody SelectAccountRequest request){
        AccountDto account = accountService.findAccount(request.getAccount_number());
        return new Result(true, 200, account, "계좌 조회");
    }

    @Data
    static class CreateAccountRequest{
        private Long member_id;
        private String bank_name;
        private String name;
        private String account_number;
        private String password;
    }

    @Data
    static class SelectAccountsRequest{
        private String social_security_number;
    }

    @Data
    static class SelectAccountRequest{
        private String account_number;
    }
}
