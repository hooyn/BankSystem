package LakeLight.bankSystem.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberAccountDto {
    private Long memberId;
    private String username;

    private Long accountId;
    private String bank_name;
    private String name;
    private String account_number;

    private int balance;

    @QueryProjection
    public MemberAccountDto(Long memberId, String username, Long accountId, String bank_name, String name, String account_number, int balance) {
        this.memberId = memberId;
        this.username = username;
        this.accountId = accountId;
        this.bank_name = bank_name;
        this.name = name;
        this.account_number = account_number;
        this.balance = balance;
    }
}
