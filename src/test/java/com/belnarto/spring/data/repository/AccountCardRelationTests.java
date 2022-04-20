package com.belnarto.spring.data.repository;

import com.belnarto.spring.data.entity.AccountEntity;
import com.belnarto.spring.data.entity.CardEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountCardRelationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @BeforeEach
    @AfterEach
    void clearTables() {
        accountRepository.deleteAll();
        cardRepository.deleteAll();
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> {
            CardEntity cardEntity = CardEntity.builder()
                    .name("name1")
                    .type("type1")
                    .build();
            cardRepository.save(cardEntity);

            AccountEntity accountEntity = AccountEntity.builder()
                    .balance(BigDecimal.TEN)
                    .user("test")
                    .card(cardEntity)
                    .build();
            accountRepository.save(accountEntity);
        });
    }

    @Test
    void complexSelect() {
        CardEntity cardEntity = CardEntity.builder()
                .name("name1")
                .type("type1")
                .build();
        cardRepository.save(cardEntity);

        AccountEntity accountEntity = AccountEntity.builder()
                .balance(BigDecimal.TEN)
                .user("test")
                .card(cardEntity)
                .build();
        accountRepository.save(accountEntity);

        assertEquals(List.of(accountEntity), accountRepository.findByCard_Name("name1"));
        assertEquals(List.of(accountEntity), accountRepository.findByCardType("type1"));

    }
}
