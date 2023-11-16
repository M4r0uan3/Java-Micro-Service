package ma.emsi.tp2ms.service;

import jakarta.transaction.Transactional;
import ma.emsi.tp2ms.dto.BankAccountRequestDTO;
import ma.emsi.tp2ms.dto.BankAccountResponseDTO;
import ma.emsi.tp2ms.entities.BankAccount;
import ma.emsi.tp2ms.mappers.AccountMapper;
import ma.emsi.tp2ms.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(bankAccountRequestDTO.getBalance())
                .currency(bankAccountRequestDTO.getCurrency())
                .type(bankAccountRequestDTO.getType())
                .createdAt(new Date())
                .build();
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
//        BankAccountResponseDTO responseDTO = BankAccountResponseDTO.builder()
//                .id(savedAccount.getId())
//                .balance(savedAccount.getBalance())
//                .currency(savedAccount.getCurrency())
//                .type(savedAccount.getType())
//                .createdAt(savedAccount.getCreatedAt())
//                .build();
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedAccount);
        return bankAccountResponseDTO;
    }
}
