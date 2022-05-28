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

    private String username;
    private String social_security_number;
    private String tel_number;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Member(String username, String social_security_number, String tel_number) {
        this.username = username;
        this.social_security_number = social_security_number;
        this.tel_number = tel_number;
    }
}
