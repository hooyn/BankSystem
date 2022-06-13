package LakeLight.bankSystem.repository;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.QTransaction;
import LakeLight.bankSystem.domain.Transaction;
import LakeLight.bankSystem.domain.TransactionStatus;
import LakeLight.bankSystem.dto.TransactionDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static LakeLight.bankSystem.domain.QTransaction.transaction;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Transaction transaction){
        Account account = transaction.getAccount();

        if(transaction.getStatus().equals(TransactionStatus.DEPOSIT)){
            transaction.setBalance_before(account.getBalance());
            account.addBalance(transaction.getAmount());
            transaction.setBalance_after(account.getBalance());
            em.persist(transaction);
            em.flush();
            em.clear();
        } else if(transaction.getStatus().equals(TransactionStatus.WITHDRAW)){
            if((account.getBalance() - transaction.getAmount()) >= 0){
                transaction.setBalance_before(account.getBalance());
                account.subBalance(transaction.getAmount());
                transaction.setBalance_after(account.getBalance());
                em.persist(transaction);
                em.flush();
                em.clear();
            } else {
                throw new IllegalStateException("잔액이 부족합니다.");
            }
        }
    }

    //계좌에 따른 거래 내역 조회
    public List<TransactionDto> findAll(String account_num){
        return queryFactory
                .select(Projections.constructor(TransactionDto.class,
                        transaction.id,
                        transaction.info,
                        transaction.balance_before,
                        transaction.amount,
                        transaction.balance_after,
                        transaction.status,
                        transaction.localDateTime))
                .from(transaction)
                .where(transaction.account.account_number.eq(account_num))
                .fetch();
    }
}
