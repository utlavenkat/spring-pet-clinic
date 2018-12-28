package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {
    @Column(name = "description")
    @NonNull
    private String description;
}
