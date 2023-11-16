package ma.emsi.tp2ms.service;

import ma.emsi.tp2ms.dto.BankAccountRequestDTO;
import ma.emsi.tp2ms.dto.BankAccountResponseDTO;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
}
