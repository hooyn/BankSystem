package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.dto.AccountDto;
import com.querydsl.core.types.Projections;
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

    public List<AccountDto> findAll(String number){
        return queryFactory
                .select(Projections.constructor(AccountDto.class,
                        account.id,
                        account.bank_name,
                        account.name,
                        account.account_number,
                        account.member.username,
                        account.balance))
                .from(account)
                .where(account.member.social_security_number.eq(number))
                .fetch();
    }

    public AccountDto findByAccountNum(String account_num){
        return queryFactory
                .select(Projections.constructor(AccountDto.class,
                        account.id,
                        account.bank_name,
                        account.name,
                        account.account_number,
                        account.member.username,
                        account.balance))
                .from(account)
                .where(account.account_number.eq(account_num))
                .fetchOne();
    }

    public Account findById(Long id){
        return queryFactory
                .selectFrom(account)
                .where(account.id.eq(id))
                .fetchOne();
    }

    //은행명, 은행이름에 따른 조회
}
