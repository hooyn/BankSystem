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
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String social_security_number;
    private String tel_number;

    @OneToMany(mappedBy = "member")
    private List<Account> accounts = new ArrayList<>();

    public Member(String name, String social_security_number, String tel_number) {
        this.name = name;
        this.social_security_number = social_security_number;
        this.tel_number = tel_number;
    }
}
