package site.easy.to.build.crm.repository.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.my.Taux;

import java.time.LocalDateTime;

@Repository
public interface TauxRepository extends JpaRepository<Taux,Integer> {

    @Query("select t from Taux t where t.date <= :dateTime order by t.date desc ")
    double findLastValueTaux(@Param("dateTime") LocalDateTime dateTime);
}
