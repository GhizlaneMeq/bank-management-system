package ma.youcode.bankmanagementsystem.services;

import ma.youcode.bankmanagementsystem.dtos.transaction.CreateTransactionRequestDTO;
import ma.youcode.bankmanagementsystem.dtos.transaction.TransactionApprovalRequestDTO;
import ma.youcode.bankmanagementsystem.dtos.transaction.TransactionResponseDTO;
import ma.youcode.bankmanagementsystem.dtos.transaction.TransactionSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransactionResponseDTO createTransaction(CreateTransactionRequestDTO request);

    TransactionResponseDTO getTransactionById(Long id);

    TransactionResponseDTO getTransactionByReference(String reference);

    Page<TransactionResponseDTO> getAccountTransactions(Long accountId, Pageable pageable);

    Page<TransactionResponseDTO> searchTransactions(TransactionSearchCriteria criteria, Pageable pageable);

    TransactionResponseDTO approveTransaction(Long id, TransactionApprovalRequestDTO request);

    void processRecurringTransactions();
}