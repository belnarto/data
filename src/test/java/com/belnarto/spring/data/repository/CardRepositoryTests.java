package com.belnarto.spring.data.repository;

import com.belnarto.spring.data.entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardRepositoryTests {

    @Autowired
    private CardRepository repository;

    @BeforeEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void sorting() {
        CardEntity entity1 = CardEntity.builder()
                .name("name1")
                .type("type1")
                .build();
        CardEntity entity2 = CardEntity.builder()
                .name("name2")
                .type("type2")
                .build();
        List<CardEntity> entities = List.of(entity1, entity2);
        repository.saveAll(entities);

        Iterable<CardEntity> sortedByName = repository.findAll(Sort.by(Sort.Order.asc("name")));
        assertEquals(entities, sortedByName);
    }

    @Test
    void pagination() {
        CardEntity entity1 = CardEntity.builder()
                .name("name1")
                .type("type1")
                .build();
        CardEntity entity2 = CardEntity.builder()
                .name("name2")
                .type("type2")
                .build();
        CardEntity entity3 = CardEntity.builder()
                .name("name3")
                .type("type3")
                .build();
        CardEntity entity4 = CardEntity.builder()
                .name("name4")
                .type("type4")
                .build();
        Set<CardEntity> entities = Set.of(entity1, entity2, entity3, entity4);
        repository.saveAll(entities);

        Page<CardEntity> paged = repository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Order.asc("type"))));
        assertEquals(Set.of(entity1, entity2), paged.toSet());
    }
}
