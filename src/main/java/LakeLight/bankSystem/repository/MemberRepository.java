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
                        member.name,
                        member.social_security_number,
                        member.tel_number))
                .from(member)
                .fetch();
    }

    public MemberDto findByName(String name){
        return queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.name,
                        member.social_security_number,
                        member.tel_number))
                .from(member)
                .where(member.name.eq(name))
                .fetchOne();
    }
}
