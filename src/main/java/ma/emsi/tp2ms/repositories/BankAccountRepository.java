package ma.emsi.tp2ms.repositories;

import ma.emsi.tp2ms.entities.BankAccount;
import ma.emsi.tp2ms.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    @RestResource(path = "/byType")
    List<BankAccount> findByType(@Param("t") AccountType type);
}
