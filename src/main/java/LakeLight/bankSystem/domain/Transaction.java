package LakeLight.bankSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Transaction {

    @Id @GeneratedValue
    @Column(name = "transaction_id")
    private Long id;

    private String info;
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public void setAccount(Account account){
        this.account = account;
        account.getTransactions().add(this);
    }
}
