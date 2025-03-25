package site.easy.to.build.crm.repository.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.my.DepenseTemp;

@Repository
public interface DepenseTempRepository extends JpaRepository<DepenseTemp, Integer> {
}
