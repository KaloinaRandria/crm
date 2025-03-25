package site.easy.to.build.crm.repository.my;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.my.Depense;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Integer> {
    @Query("select d from Depense d where d.ticket.customer.customerId = :customerId AND d.date <= :dateTime")
    List<Depense> findDepenseTicketByCustomerAndDate(@Param("customerId") int customerId ,@Param("dateTime") LocalDateTime dateTime);

    @Query("select d from Depense d where d.lead.customer.customerId = :customerId AND d.date <= :dateTime")
    List<Depense> findDepenseLeadByCustomerAndDate(@Param("customerId") int customerId ,@Param("dateTime") LocalDateTime dateTime);

    @Modifying
    @Query("DELETE FROM Depense d WHERE d.ticket = :ticket")
    void deleteByTicket(@Param("ticket") Ticket ticket);

    @Modifying
    @Query("DELETE FROM Depense d WHERE d.lead = :lead")
    void deleteByLead(@Param("lead")Lead lead);

    @Query("select d from Depense d where d.ticket.ticketId = :idTicket AND d.date <= :dateTime order by d.date desc ")
    List<Depense> findLastValueTicket(@Param("idTicket") int idTicket ,@Param("dateTime")  LocalDateTime dateTime);

    @Query("select d from Depense d where d.lead.leadId = :idLead AND d.date <= :dateTime order by d.date desc ")
    List<Depense> findLastValueLead(@Param("idLead") int idLead ,@Param("dateTime")  LocalDateTime dateTime);

    @Query("select d from Depense d where d.ticket.ticketId is not null and d.date <= :dateTime")
    List<Depense> findDepenseTicketByDate(@Param("dateTime") LocalDateTime dateTime);

    @Query("select d from Depense d where d.lead.leadId is not null and d.date <= :dateTime")
    List<Depense> findDepenseLeadByDate(@Param("dateTime") LocalDateTime dateTime);

    @Query("select d from Depense d where d.ticket.ticketId = :idTicket")
    Depense findDepenseByIdTicket(@Param("idTicket") int idTicket);

    @Query("select d from Depense d where d.lead.leadId = :idLead")
    Depense findDepenseByIdLead(int idLead);
}
