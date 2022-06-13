package LakeLight.bankSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    @Id @GeneratedValue
    @Column(name = "transaction_id")
    private Long id;

    private String info;
    private int amount;
    private int balance_before;
    private int balance_after;
    private LocalDateTime localDateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public void setAccount(Account account){
        this.account = account;
        account.getTransactions().add(this);
    }

    public Transaction(String info, int amount, Account account, TransactionStatus status) {
        this.info = info;
        this.amount = amount;
        this.account = account;
        this.status = status;
        this.localDateTime = LocalDateTime.now();
    }
}
