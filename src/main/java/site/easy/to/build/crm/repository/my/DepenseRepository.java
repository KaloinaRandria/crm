package site.easy.to.build.crm.repository.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.my.Depense;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Integer> {
}
