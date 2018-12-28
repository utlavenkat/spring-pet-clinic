package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "telephone")
    @NonNull
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Pet addPet(Pet pet) {
        pet.setOwner(this);
        pets.add(pet);
        return pet;
    }
}
