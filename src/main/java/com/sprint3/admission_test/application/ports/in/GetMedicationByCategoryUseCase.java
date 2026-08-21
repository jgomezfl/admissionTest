package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.model.Medication;

import java.time.LocalDate;
import java.util.List;

public interface GetMedicationByCategoryUseCase {
    List<Medication> findByCategory(String category, LocalDate date);
}
