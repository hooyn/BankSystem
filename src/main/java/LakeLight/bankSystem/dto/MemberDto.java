package LakeLight.bankSystem.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {
    private String name;
    private String social_security_number;
    private String tel_number;

    public MemberDto(String name, String social_security_number, String tel_number) {
        this.name = name;
        this.social_security_number = social_security_number;
        this.tel_number = tel_number;
    }
}
