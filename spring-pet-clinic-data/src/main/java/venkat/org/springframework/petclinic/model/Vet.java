package venkat.org.springframework.petclinic.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class Vet extends Person {

    private Set<Speciality> specialities = new HashSet<>();

}
