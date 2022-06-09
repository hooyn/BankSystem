package LakeLight.bankSystem.service;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired MemberService memberService;
    @Autowired AccountService accountService;

    @Test
    public void test() throws Exception {
        //given
        Member member1 = new Member("man1", "980311-1", "010-3364");
        Member member2 = new Member("man2", "980311-2", "010-3365");
        Member member3 = new Member("man3", "980311-3", "010-3366");

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        Account account1 = new Account(member1,"KB", "KB행복", "2149-02", "0123", 10_000_000);
        Account account2 = new Account(member2,"KB", "KB행복", "2149-03", "0123", 20_000_000);
        Account account3 = new Account(member3,"KB", "KB행복", "2149-04", "0123", 30_000_000);
        accountService.createAccount(account1);
        accountService.createAccount(account2);
        accountService.createAccount(account3);
        //when

        AccountDto account = accountService.findAccount("2149-02");
        //then
        System.out.println(account);
    }
}