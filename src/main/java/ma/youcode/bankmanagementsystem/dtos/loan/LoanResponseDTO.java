package ma.youcode.bankmanagementsystem.dtos.loan;

import ma.youcode.bankmanagementsystem.enums.LoanStatus;
import ma.youcode.bankmanagementsystem.enums.LoanType;

import java.time.LocalDate;

public record LoanResponseDTO(
        Long id,
        String userName,
        LoanType type,
        double amount,
        int termMonths,
        double monthlyPayment,
        double monthlyIncome,
        double debtRatio,
        LoanStatus status,
        LocalDate startDate,
        LocalDate endDate,
        String reviewedBy,
        String rejectionReason,
        Integer creditScore,
        String collateralDescription,
        LocalDate loanStartDate,
        LocalDate loanEndDate
) {
}
