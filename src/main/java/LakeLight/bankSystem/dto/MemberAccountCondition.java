package LakeLight.bankSystem.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberAccountCondition {
    private String username;
    private String account_number;

    @QueryProjection
    public MemberAccountCondition(String username, String account_number) {
        this.username = username;
        this.account_number = account_number;
    }
}
