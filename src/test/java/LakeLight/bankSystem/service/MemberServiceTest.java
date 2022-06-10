package LakeLight.bankSystem.service;

import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.MemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    public void duplicate() throws Exception {
        //given
        Member member = new Member("ho", "980311-1", "010-3364");
        Member member1 = new Member("ho1", "980311-1", "010-3364");
        //when
        memberService.join(member);

        //then
        try{
            memberService.join(member1);
        } catch (IllegalStateException e){
            Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage());
        }
    }

    @Test
    public void select() throws Exception {
        //given
        Member member = new Member("ho", "980311-1", "010-3364");
        //when
        memberService.join(member);

        //then
        MemberDto member1 = memberService.findMember("980311-1");
        System.out.println(member1);
    }
}