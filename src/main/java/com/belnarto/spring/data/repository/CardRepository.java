package com.belnarto.spring.data.repository;

import com.belnarto.spring.data.entity.CardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CardRepository extends PagingAndSortingRepository<CardEntity, UUID> {
}
