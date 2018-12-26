package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Pets")
public class Pet extends BaseEntity {

    @Column(name = "name")
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NonNull
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NonNull
    private Owner owner;

    @Column(name = "birth_date")
    @NonNull
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Visit addVisit(Visit visit) {
        visit.setPet(this);
        visits.add(visit);
        return visit;
    }

}
