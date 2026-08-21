package com.sprint3.admission_test.infrastructure.adapter.out.persistence.repositoryImpl;

import com.sprint3.admission_test.application.ports.out.ICategoryRepository;
import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.infrastructure.adapter.out.persistence.jpaRepository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findByName(String name) {
        return categoryJpaRepository.findByName(name);
    }
}
