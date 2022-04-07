package uz.pdp.restfullcodingsite.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.restfullcodingsite.entity.Language;

import java.util.Optional;

@Repository
public interface LanguageRepo extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);
    Optional<Language> findByName(String name);
    boolean existsByNameAndIdNot(String name,int id);
}
