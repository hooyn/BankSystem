package LakeLight.bankSystem.dto;

import LakeLight.bankSystem.domain.TransactionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private String info;
    private int balance_before;
    private int amount;
    private int balance_after;
    private TransactionStatus status;

    public TransactionDto(Long id, String info, int balance_before, int amount, int balance_after, TransactionStatus status) {
        this.id = id;
        this.info = info;
        this.balance_before = balance_before;
        this.amount = amount;
        this.balance_after = balance_after;
        this.status = status;
    }
}
