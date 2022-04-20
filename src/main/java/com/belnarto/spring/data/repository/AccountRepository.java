package com.belnarto.spring.data.repository;

import com.belnarto.spring.data.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {

    long countByUser(String user);

    long deleteByBalance(BigDecimal balance);

    List<AccountEntity> removeByBalance(BigDecimal balance);

    List<AccountEntity> findByCard_Name(String name);

    List<AccountEntity> findByCardType(String type);
}
