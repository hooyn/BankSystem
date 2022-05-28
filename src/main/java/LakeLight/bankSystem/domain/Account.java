package LakeLight.bankSystem.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String bank_name;
    private String name;
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
        balance -= money;
    }

    public Account(Member member, String bank_name, String name, String account_number, String password, int balance) {
        this.member = member;
        this.bank_name = bank_name;
        this.name = name;
        this.account_number = account_number;
        this.password = password;
        this.balance = balance;
    }


}
