package gargoyle.heartsong.dao.base;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    List<T> findAll();

    long countAll();

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Optional<T> findByUid(String uid);

    default void beforeSave(T model) {
    }

    default void afterSave(T model) {
    }

    @Transactional
    void save(T model);
}
