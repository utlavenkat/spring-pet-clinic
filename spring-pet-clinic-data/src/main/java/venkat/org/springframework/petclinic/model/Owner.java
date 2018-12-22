package venkat.org.springframework.petclinic.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class Owner extends Person {
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;
}
