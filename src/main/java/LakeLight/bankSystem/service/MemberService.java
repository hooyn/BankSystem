package LakeLight.bankSystem.service;

import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.dto.MemberDto;
import LakeLight.bankSystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        MemberDto findMember = memberRepository.findBySecurityNum(member.getSocial_security_number());
        if(findMember!=null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    @Transactional(readOnly = true)
    public List<MemberDto> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회 (주민번호로)
     */
    @Transactional(readOnly = true)
    public MemberDto findMember(String num){
        return memberRepository.findBySecurityNum(num);
    }

    /**
     * 회원 한명 조회 (id)
     */
    @Transactional(readOnly = true)
    public Member findOne(Long id){
        return memberRepository.findById(id);
    }

    /**
     * 회원 삭제
     */
    public void deleteMember(String number){
        memberRepository.delete(number);
    }
}
