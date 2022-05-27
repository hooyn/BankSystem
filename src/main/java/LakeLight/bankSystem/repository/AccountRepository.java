package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static LakeLight.bankSystem.domain.QAccount.account;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Account account){
        em.persist(account);
    }

    public List<Account> findAll(String name){
        return queryFactory
                .selectFrom(account)
                .where(account.member.name.eq(name))
                .fetch();
    }

    public Account findById(Long id){
        return queryFactory
                .selectFrom(account)
                .where(account.id.eq(id))
                .fetchOne();
    }

    //은행명, 은행이름에 따른 조회
}
