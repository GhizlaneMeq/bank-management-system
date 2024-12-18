package ma.youcode.bankmanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.bankmanagementsystem.dtos.PageResponse;
import ma.youcode.bankmanagementsystem.dtos.account.AccountResponseDTO;
import ma.youcode.bankmanagementsystem.dtos.account.CreateAccountRequestDTO;
import ma.youcode.bankmanagementsystem.dtos.account.UpdateAccountRequestDTO;
import ma.youcode.bankmanagementsystem.entities.Account;
import ma.youcode.bankmanagementsystem.exceptions.ResourceNotFoundException;
import ma.youcode.bankmanagementsystem.mappers.AccountMapper;
import ma.youcode.bankmanagementsystem.repositories.AccountRepository;
import ma.youcode.bankmanagementsystem.services.AccountService;
import ma.youcode.bankmanagementsystem.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<AccountResponseDTO> getAllAccounts(Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAll(pageable);
        return PageResponse.of(
                accountPage.getContent().stream()
                        .map(accountMapper::toResponse)
                        .toList(),
                accountPage
        );
    }

    @Override
    public AccountResponseDTO createAccount(CreateAccountRequestDTO request) {
        Account account = accountMapper.toEntity(request);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toResponse(savedAccount);
    }

    @Override
    public AccountResponseDTO updateAccountStatus(Long id, UpdateAccountRequestDTO request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        accountMapper.updateAccountStatus(account, request);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toResponse(updatedAccount);
    }

    @Override
    public AccountResponseDTO updateAccountBalance(Long id, UpdateAccountRequestDTO request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        accountMapper.updateAccountBalance(account, request);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toResponse(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountResponseDTO> getAccountsByUserId(Long userId) {
        List<Account> accounts = accountRepository.findAllByUserId(userId);
        return accounts.stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public List<AccountResponseDTO> getMyAccounts() {
        Long currentUserId = userService.getCurrentUserProfile().id();
        return getAccountsByUserId(currentUserId);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }
}
