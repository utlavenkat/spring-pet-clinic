package venkat.org.springframework.petclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class Owner extends Person {
    private Set<Pet> pets;
}
