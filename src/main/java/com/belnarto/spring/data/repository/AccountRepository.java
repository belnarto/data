package com.belnarto.spring.data.repository;

import com.belnarto.spring.data.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
}
