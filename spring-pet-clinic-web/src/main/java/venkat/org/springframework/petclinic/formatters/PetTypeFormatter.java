package venkat.org.springframework.petclinic.formatters;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@RequiredArgsConstructor
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> petTypes = petTypeService.findAll();
        for (PetType type : petTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
