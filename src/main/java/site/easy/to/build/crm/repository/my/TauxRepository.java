package site.easy.to.build.crm.repository.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.my.Taux;

@Repository
public interface TauxRepository extends JpaRepository<Taux,Integer> {
}
