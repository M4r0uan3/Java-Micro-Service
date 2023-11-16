package ma.emsi.tp2ms;

import ma.emsi.tp2ms.entities.BankAccount;
import ma.emsi.tp2ms.enums.AccountType;
import ma.emsi.tp2ms.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class Tp2MsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp2MsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			for (int i = 1; i < 10; i++) {
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
						.balance(10000+Math.random()*90000)
						.createdAt(new Date())
						.currency("MAD")
						.build();
				bankAccountRepository.save(bankAccount);
			}
		};

	}

}
