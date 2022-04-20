package com.belnarto.spring.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface MyCustomRepository<T, ID> extends Repository<T, ID> {

    List<T> findByAttributeContainsText(String attributeName, String text);
}
