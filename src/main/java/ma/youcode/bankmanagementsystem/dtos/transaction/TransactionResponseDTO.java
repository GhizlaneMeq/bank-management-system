package ma.youcode.bankmanagementsystem.dtos.transaction;

import ma.youcode.bankmanagementsystem.enums.TransactionStatus;
import ma.youcode.bankmanagementsystem.enums.TransactionType;

import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        TransactionType type,
        double amount,
        double feeAmount,
        double totalAmount,
        String sourceAccountOwnerName,
        String destinationAccountOwnerName,
        TransactionStatus status,
        String description,
        String reference,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
