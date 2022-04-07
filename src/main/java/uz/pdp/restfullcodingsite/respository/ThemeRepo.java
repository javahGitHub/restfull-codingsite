package uz.pdp.restfullcodingsite.respository;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.restfullcodingsite.entity.Theme;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepo extends JpaRepository<Theme,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name,int id);
    @Query("select t from Theme t where t.language.name = ?1")
    List findByLanguage_Name(String name);

    Optional<Theme> findByName(String name);
}
