package venkat.org.springframework.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class Owner extends Person {
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;
}
