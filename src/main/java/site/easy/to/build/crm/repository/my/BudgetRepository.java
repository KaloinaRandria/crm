package site.easy.to.build.crm.repository.my;

import com.google.api.client.util.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.my.Budget;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    @Query("select b from Budget b where b.customer.customerId = :customerId AND b.date <= :dateTime")
    List<Budget> findBudgetByCustomerAndDate(@Param("customerId") int customerId , @Param("dateTime") LocalDateTime dateTime);
}
