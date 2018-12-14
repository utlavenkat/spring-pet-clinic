package venkat.org.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pet {
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;

}