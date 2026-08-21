package com.sprint3.admission_test.application.useCases;

import com.sprint3.admission_test.application.ports.in.CreateMedicationUseCase;
import com.sprint3.admission_test.application.ports.in.GetMedicationByCategoryUseCase;
import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.application.ports.out.ICategoryRepository;
import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.exceptions.NotFoundException;
import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.infrastructure.adapter.in.web.dto.MedicationDto;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicationUseCaseImpl implements IMedicationUseCase, CreateMedicationUseCase, GetMedicationByCategoryUseCase {

    @Autowired
    private IMedicationRepository medicationRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Could not find medication with ID: " + id
        ));
    }


    @Override
    public Medication save(MedicationDto medicationDto) {
        Medication medication = Medication.builder()
                .name(medicationDto.getName())
                .description(medicationDto.getDescription())
                .price(medicationDto.getPrice())
                .expirationDate(medicationDto.getExpiration_date())
                .build();

        medication.setCategory(
                categoryRepository.findByName(medicationDto.getCategory_name())
                        .orElseThrow(() -> new NotFoundException(
                                "Could not find category with Name: " + medicationDto.getCategory_name()
                        ))
        );

        medication.setExpirationDate(dateValidation(medicationDto.getExpiration_date()));

        return medicationRepository.save(medication);
    }

    private LocalDate dateValidation(LocalDate date){
        if(date.isAfter(LocalDate.now())){
            return date;
        }
        try {
            throw new BadRequestException("Expiration Date must be after creation Date");
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medication> findByCategory(String category, LocalDate date) {
        Category cat = categoryRepository.findByName(category)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find category with Name: " + category
                ));
        return date == null
                ? medicationRepository.findByCategory(cat)
                : medicationRepository.findByCategory(cat).stream().filter(m -> m.getExpirationDate().isAfter(date)).toList();
    }
}
