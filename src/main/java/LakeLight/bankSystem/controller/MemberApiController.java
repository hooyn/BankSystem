package LakeLight.bankSystem.controller;

import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.MemberDto;
import LakeLight.bankSystem.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    /**
     * 전체 멤버 조회
     */
    @GetMapping("/members")
    public Result member(){
        List<MemberDto> members = memberService.findMembers();
        return new Result(true, 200, members, "전체 멤버 조회");
    }

    /**
     * 특정 멤버 조회 (주민번호 기준)
     */
    @PostMapping("/member/secure")
    public Result findOneMember(@RequestBody SelectMemberRequest request){
        MemberDto member = memberService.findMember(request.getSocial_security_number());
        return new Result(true, 200, member, "특정 멤버 조회");
    }

    /**
     * 전체 멤버 생성
     */
    @PostMapping("/member")
    public Result saveMember(@RequestBody CreateMemberRequest request){
        Member member = new Member(request.username, request.social_security_number, request.tel_number);
        Long id = memberService.join(member);
        return new Result(true, 200, id, "멤버 생성");
    }

    @Data
    static class SelectMemberRequest {
        private String social_security_number;
    }

    @Data
    static class CreateMemberRequest {
        private String username;
        private String social_security_number;
        private String tel_number;
    }
}