package com.sprint3.admission_test.infrastructure.adapter.in.web;

import com.sprint3.admission_test.application.ports.in.CreateMedicationUseCase;
import com.sprint3.admission_test.application.ports.in.GetMedicationByCategoryUseCase;
import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.infrastructure.adapter.in.web.dto.MedicationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    @Autowired
    private IMedicationUseCase medicationUseCase;
    @Autowired
    private CreateMedicationUseCase createMedicationUseCase;
    @Autowired
    private GetMedicationByCategoryUseCase getMedicationByCategoryUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(medicationUseCase.getMedicationById(id));
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Medication>> getMedicationsByCategory(@PathVariable String category, @RequestParam(value = "expiration-after", required = false) LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(getMedicationByCategoryUseCase.findByCategory(category, date));
    }

    @PostMapping
    public ResponseEntity<Medication> createMedication(@Valid @RequestBody MedicationDto medicationDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(createMedicationUseCase.save(medicationDto));
    }

}
