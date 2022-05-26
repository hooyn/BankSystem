package LakeLight.bankSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String bank_name;
    private String account_number;
    private String password;
    private int balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getAccounts().add(this);
    }

    public void addBalance(int money){
        balance += money;
    }

    public void subBalance(int money){
        if((balance - money) >= 0){
            balance -= money;
        } else {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
    }
}
