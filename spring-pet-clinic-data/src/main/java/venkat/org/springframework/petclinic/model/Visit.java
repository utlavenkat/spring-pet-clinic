package venkat.org.springframework.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@Data
public class Visit extends BaseEntity {
    private LocalTime date;
    private String description;
    private Pet pet;

}
