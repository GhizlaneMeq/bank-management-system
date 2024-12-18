package ma.youcode.bankmanagementsystem.dtos.transaction;

import lombok.Builder;
import ma.youcode.bankmanagementsystem.enums.TransactionStatus;
import ma.youcode.bankmanagementsystem.enums.TransactionType;

import java.time.LocalDateTime;

@Builder
public record TransactionSearchCriteria(
        Long accountId,
        TransactionType type,
        TransactionStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double minAmount,
        Double maxAmount,
        Boolean requiresApproval,
        Boolean recurring
) {
}
