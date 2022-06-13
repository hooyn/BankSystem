package LakeLight.bankSystem.dto;

import LakeLight.bankSystem.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String bank_name;
    private String name;
    private String account_number;
    private String username;
    private int balance;

    public AccountDto(Long id, String bank_name, String name, String account_number, String username, int balance) {
        this.id = id;
        this.bank_name = bank_name;
        this.name = name;
        this.account_number = account_number;
        this.username = username;
        this.balance = balance;
    }
}
