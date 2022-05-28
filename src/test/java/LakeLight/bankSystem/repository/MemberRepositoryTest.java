package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void findByName() throws Exception {
        //given
        Member member0 = new Member("hoyun0", "980311-1", "010-3364-2602");
        Member member1 = new Member("hoyun1", "980311-2", "010-3384-2602");
        memberRepository.save(member0);
        memberRepository.save(member1);
        
        //when
        List<MemberDto> findAll = memberRepository.findAll();
        MemberDto hoyun0 = memberRepository.findBySecurityNum("980311-1");
        //then

        for (MemberDto memberDto : findAll) {
            System.out.println("memberDto = " + memberDto.getUsername());
        }
        System.out.println(hoyun0.getUsername());

        Assertions.assertThat(hoyun0.getUsername()).isEqualTo("hoyun0");
    }

}