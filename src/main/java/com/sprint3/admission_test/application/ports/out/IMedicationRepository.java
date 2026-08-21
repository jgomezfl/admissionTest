package com.sprint3.admission_test.application.ports.out;

import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.model.Medication;

import java.util.List;
import java.util.Optional;

public interface IMedicationRepository {

    Medication save(Medication medication);
    Optional<Medication> findById(Long id);
    List<Medication> findByCategory(Category category);

}
