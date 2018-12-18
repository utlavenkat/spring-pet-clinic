package venkat.org.springframework.petclinic.services.map;


import venkat.org.springframework.petclinic.model.BaseEntity;
import venkat.org.springframework.petclinic.services.CRUDService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> implements CRUDService<T, ID> {

    protected Map<Long, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getMaxId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    @Override
    public void delete(T object) {
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);

    }

    private Long getMaxId() {
        if (map.isEmpty()) {
            return 1L;
        }
        return Collections.max(map.keySet()) + 1;
    }
}
