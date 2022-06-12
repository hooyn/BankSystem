package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Member;
import LakeLight.bankSystem.domain.QMember;
import LakeLight.bankSystem.dto.MemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static LakeLight.bankSystem.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Member member){
        em.persist(member);
    }

    public List<MemberDto> findAll(){
        return queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.id,
                        member.username,
                        member.social_security_number,
                        member.tel_number))
                .from(member)
                .fetch();
    }

    public MemberDto findBySecurityNum(String number){
        return queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.id,
                        member.username,
                        member.social_security_number,
                        member.tel_number))
                .from(member)
                .where(member.social_security_number.eq(number))
                .fetchOne();
    }

    public void delete(String number){
        MemberDto member = findBySecurityNum(number);
        if(member!=null){
            em.remove(member);
        }
    }

    public Member findById(Long id){
        return queryFactory
                .selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne();
    }
}
