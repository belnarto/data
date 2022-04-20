package com.belnarto.spring.data.repository;

import com.belnarto.spring.data.entity.AccountEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountRepositoryTests {

    @Autowired
    private AccountRepository repository;

    @BeforeEach
    @AfterEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void save() {
        AccountEntity entity = AccountEntity.builder()
                .balance(BigDecimal.TEN)
                .user("test")
                .build();
        repository.save(entity);
        assertEquals(entity, repository.findById(entity.getId()).orElse(null));
    }

    @Test
    void saveAll() {
        AccountEntity entity1 = AccountEntity.builder()
                .balance(BigDecimal.TEN)
                .user("test1")
                .build();
        AccountEntity entity2 = AccountEntity.builder()
                .balance(BigDecimal.ONE)
                .user("test2")
                .build();
        Set<AccountEntity> entities = Set.of(entity2, entity1);
        repository.saveAll(entities);

        Set<AccountEntity> fetched = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
        assertEquals(entities, fetched);
    }

    @Test
    void counting() {
        AccountEntity entity1 = AccountEntity.builder()
                .balance(BigDecimal.TEN)
                .user("test1")
                .build();
        AccountEntity entity2 = AccountEntity.builder()
                .balance(BigDecimal.ONE)
                .user("test2")
                .build();
        Set<AccountEntity> entities = Set.of(entity2, entity1);
        repository.saveAll(entities);

        assertEquals(2, repository.count());
        assertTrue(repository.existsById(entity2.getId()));
        assertEquals(entity1, repository.findById(entity1.getId()).orElse(null));

        repository.deleteById(entity1.getId());
        assertEquals(1, repository.count());
        assertFalse(repository.existsById(entity1.getId()));
    }
}
