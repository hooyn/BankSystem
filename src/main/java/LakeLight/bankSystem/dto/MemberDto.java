package LakeLight.bankSystem.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String social_security_number;
    private String tel_number;

    public MemberDto(Long id, String username, String social_security_number, String tel_number) {
        this.id = id;
        this.username = username;
        this.social_security_number = social_security_number;
        this.tel_number = tel_number;
    }
}
