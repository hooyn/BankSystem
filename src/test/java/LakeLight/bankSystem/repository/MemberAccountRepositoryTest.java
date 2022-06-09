package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.AccountDto;
import LakeLight.bankSystem.dto.MemberAccountCondition;
import LakeLight.bankSystem.dto.MemberAccountDto;
import LakeLight.bankSystem.service.AccountService;
import LakeLight.bankSystem.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberAccountRepositoryTest {
    @Autowired
    MemberService memberService;

    @Autowired
    AccountService accountService;

    @Autowired
    MemberAccountRepository memberAccountRepository;

    @Test
    public void test() throws Exception {
        //given
        Member member1 = new Member("man1", "980311-1", "010-3364");
        Member member2 = new Member("man2", "980311-2", "010-3365");
        Member member3 = new Member("man3", "980311-3", "010-3366");

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        Account account1 = new Account(member1,"KB", "KB행복1", "2149-02", "0123", 10_000_000);
        Account account2 = new Account(member2,"KB", "KB행복2", "2149-03", "0123", 20_000_000);
        Account account3 = new Account(member3,"KB", "KB행복3", "2149-04", "0123", 30_000_000);
        Account account4 = new Account(member1,"KB", "KB행복4", "2149-05", "0123", 30_200_000);
        Account account5 = new Account(member1,"KB", "KB행복5", "2149-06", "0123", 30_100_000);
        accountService.createAccount(account1);
        accountService.createAccount(account2);
        accountService.createAccount(account3);
        accountService.createAccount(account4);
        accountService.createAccount(account5);
        //when

        MemberAccountCondition man1 = new MemberAccountCondition("man1", "2149-06");
        List<MemberAccountDto> search = memberAccountRepository.search(man1);

        for (MemberAccountDto memberAccountDto : search) {
            System.out.println(memberAccountDto);
        }
    }
}