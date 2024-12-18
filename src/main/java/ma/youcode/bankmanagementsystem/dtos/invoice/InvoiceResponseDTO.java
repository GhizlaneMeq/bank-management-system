package ma.youcode.bankmanagementsystem.dtos.invoice;

import ma.youcode.bankmanagementsystem.enums.InvoiceStatus;

import java.time.LocalDate;

public record InvoiceResponseDTO(
        Long id,
        double amount,
        LocalDate dueDate,
        LocalDate paymentDate,
        InvoiceStatus status,
        String description,
        Long accountId
) {
}
