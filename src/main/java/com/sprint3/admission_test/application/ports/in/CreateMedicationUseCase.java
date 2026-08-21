package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.infrastructure.adapter.in.web.dto.MedicationDto;

public interface CreateMedicationUseCase {
    Medication save(MedicationDto medicationDto);
}
