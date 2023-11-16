package ma.emsi.tp2ms.web;

import ma.emsi.tp2ms.dto.BankAccountRequestDTO;
import ma.emsi.tp2ms.dto.BankAccountResponseDTO;
import ma.emsi.tp2ms.entities.BankAccount;
import ma.emsi.tp2ms.mappers.AccountMapper;
import ma.emsi.tp2ms.repositories.BankAccountRepository;
import ma.emsi.tp2ms.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account %s Not Found!", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account %s Not Found!", id)));
        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getType() != null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping ("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
