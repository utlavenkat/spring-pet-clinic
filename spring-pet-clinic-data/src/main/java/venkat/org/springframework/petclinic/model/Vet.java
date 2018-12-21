package venkat.org.springframework.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class Vet extends Person {

    private Set<Speciality> specialities;

}
