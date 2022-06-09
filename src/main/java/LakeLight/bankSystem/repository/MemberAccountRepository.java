package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.QAccount;
import LakeLight.bankSystem.domain.QMember;
import LakeLight.bankSystem.dto.MemberAccountCondition;
import LakeLight.bankSystem.dto.MemberAccountDto;
import LakeLight.bankSystem.dto.QMemberAccountCondition;
import LakeLight.bankSystem.dto.QMemberAccountDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static LakeLight.bankSystem.domain.QAccount.account;
import static LakeLight.bankSystem.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberAccountRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public List<MemberAccountDto> search(MemberAccountCondition condition){
        return queryFactory
                .select(new QMemberAccountDto(
                        member.id.as("memberId"),
                        member.username,
                        account.id.as("accountId"),
                        account.bank_name,
                        account.name,
                        account.account_number,
                        account.balance
                ))
                .from(account)
                .leftJoin(account.member, member)
                .where(
                        usernameEq(condition.getUsername()),
                        accountNumberEq(condition.getAccount_number())
                )
                .fetch();
    }

    private BooleanExpression usernameEq(String username) {
        return StringUtils.hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression accountNumberEq(String accountNumber) {
        return StringUtils.hasText(accountNumber) ? account.account_number.eq(accountNumber) : null;
    }
}
