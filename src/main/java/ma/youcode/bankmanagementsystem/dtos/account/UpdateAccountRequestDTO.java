package ma.youcode.bankmanagementsystem.dtos.account;

import jakarta.validation.constraints.NotNull;
import ma.youcode.bankmanagementsystem.enums.AccountStatus;

public record UpdateAccountRequestDTO(
        @NotNull(message = "Amount is required")
        double amount,

        @NotNull(message = "Status is required")
        AccountStatus status
) {
}
