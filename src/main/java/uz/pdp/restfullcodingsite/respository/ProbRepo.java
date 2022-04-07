package uz.pdp.restfullcodingsite.respository;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restfullcodingsite.entity.Prob;

import java.util.Optional;

public interface ProbRepo extends JpaRepository<Prob, Integer> {
    @Query("select (count(p) > 0) from Prob p where p.code = ?1")
    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, int id);
    @Query("select p from Prob p where p.theme.name = ?1")
    Object findAllByTheme_Name(String name);

    Optional<Prob> findByCode(String code);
}
