package ma.youcode.bankmanagementsystem.dtos.transaction;

import lombok.Builder;
import ma.youcode.bankmanagementsystem.enums.TransactionFrequency;

import java.time.LocalDateTime;

@Builder
public record RecurringTransactionDTO(
    Long id,
    TransactionFrequency frequency,
    LocalDateTime nextExecutionDate,
    LocalDateTime endDate,
    Integer totalExecutions,
    Integer executedCount
) {}