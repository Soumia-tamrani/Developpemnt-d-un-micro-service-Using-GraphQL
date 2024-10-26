package org.sid.bank_account_service.service;

import org.sid.bank_account_service.Repositories.BankAccountRepository;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        //Mapping :
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();

        // Couche metier :
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);
        //

        return bankAccountResponseDTO;
    }
}
