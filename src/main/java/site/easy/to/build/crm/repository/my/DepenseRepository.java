package site.easy.to.build.crm.repository.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.my.Depense;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Integer> {
    @Query("select d from Depense d where d.ticket.customer.customerId = :customerId AND d.date <= :dateTime")
    List<Depense> findDepenseTicketByCustomerAndDate(@Param("customerId") int customerId ,@Param("dateTime") LocalDateTime dateTime);

    @Query("select d from Depense d where d.lead.customer.customerId = :customerId AND d.date <= :dateTime")
    List<Depense> findDepenseLeadByCustomerAndDate(@Param("customerId") int customerId ,@Param("dateTime") LocalDateTime dateTime);
}
