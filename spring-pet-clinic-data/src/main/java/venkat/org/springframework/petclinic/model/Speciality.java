package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {
    @Column(name = "description")
    @NonNull
    private String description;
}
