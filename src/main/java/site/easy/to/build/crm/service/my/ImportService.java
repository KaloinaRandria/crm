package site.easy.to.build.crm.service.my;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.entity.my.DepenseTemp;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.lead.LeadServiceImpl;
import site.easy.to.build.crm.service.ticket.TicketServiceImpl;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImportService {
    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    DepenseTempService depenseTempService;

    @Autowired
    DepenseService depenseService;

    @Autowired
    LeadServiceImpl leadService;

    @Autowired
    BudgetService budgetService;


    @Autowired
    TicketServiceImpl ticketService;

    List<String> ticketStatus = List.of("open" , "assigned" ,  "on-hold" , "in-progress"
    , "resolved" , "closed" , "reopened" , "pending-customer-response" , "escalated" , "archived");

    List<String> leadStatus = List.of("meeting-to-schedule" , "assign-to-sales" , "archived" , "success" );

    public boolean checkTicketStatus(String status) {
        if (ticketStatus.contains(status)) {
            return true;
        }
        return false;
    }

    public boolean checkLeadStatus(String status) {
        if (leadStatus.contains(status)) {
            return true;
        }
        return false;
    }


    public void importCSVCustomer(String csvFile , User manager) {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator('~').build())
                .build()) {

            String[] column;
            csvReader.readNext(); // Ignorer l'en-tête

            while ((column = csvReader.readNext()) != null) {

                Customer customer = new Customer();
                customer.setEmail(column[0].trim());
                customer.setName(column[1].trim());
                customer.setPhone("0345623498");
                customer.setCountry("MDG");
                customer.setUser(manager);

                customerService.save(customer);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du fichier CSV", e);
        }
    }

    public void importCSVBudget(String csvFile) throws Exception {
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator('~').build())
                .build()) {
            String[] column;
            csvReader.readNext();

            while ((column = csvReader.readNext()) != null) {

                Budget budget = new Budget();
                budget.setCustomer(customerService.findByEmail(column[0]));
                budget.setMontant(CSVFormatUtil.refactorAmountFormat(column[1]));
                budget.setDate(LocalDateTime.now());

                budgetService.saveBudget(budget);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void importCSVTicketLead(User user)throws Exception {
        List<DepenseTemp> depenseTemps = depenseTempService.getAllDepenseTemp();
        int i = 1;
        for (DepenseTemp depenseTemp : depenseTemps) {
            Lead lead = new Lead();
            Ticket ticket = new Ticket();
            Depense depense = new Depense();
            if (!depenseTemp.getType().toLowerCase().equals("lead") && !depenseTemp.getType().toLowerCase().equals("ticket")) {
                throw new Exception("Error to line "+ i +"Type not valid" + depenseTemp.getType());
            }
            if (depenseTemp.getType().toLowerCase().equals("lead")) {
                lead.setCustomer(customerService.findByEmail(depenseTemp.getCustomerEmail()));
//                if (!this.checkLeadStatus(depenseTemp.getStatus())) {
//                    throw new Exception("Error to line "+ i +" Status not found " + depenseTemp.getStatus());
//                }
//                lead.setStatus(depenseTemp.getStatus());
                lead.setStatus("meeting-to-schedule");


                lead.setName(depenseTemp.getSubjectOrName());
                lead.setMontantDepense(String.valueOf(depenseTemp.getExpense()));
                lead.setGoogleDrive(false);
                lead.setCreatedAt(LocalDateTime.now());
                lead.setManager(user);
                lead.setEmployee(user);

                depense.setMontant(depenseTemp.getExpense());
                depense.setLead(lead);
                depense.setDate(LocalDateTime.now());

                leadService.save(lead);
                depenseService.saveDepense(depense);
            }
            if (depenseTemp.getType().toLowerCase().equals("ticket")) {
                ticket.setCustomer(customerService.findByEmail(depenseTemp.getCustomerEmail()));
//                if (!checkTicketStatus(depenseTemp.getStatus())) {
//                    throw new Exception("Error to line "+ i +" Status not found "+ depenseTemp.getStatus());
//                }
//                ticket.setStatus(depenseTemp.getStatus());

                ticket.setStatus("open");
                ticket.setSubject(depenseTemp.getSubjectOrName());
                ticket.setMontantDepense(String.valueOf(depenseTemp.getExpense()));
                ticket.setPriority("low");
                ticket.setCreatedAt(LocalDateTime.now());
                ticket.setManager(user);
                ticket.setEmployee(user);

                depense.setMontant(depenseTemp.getExpense());
                depense.setTicket(ticket);
                depense.setDate(LocalDateTime.now());

                ticketService.save(ticket);
                depenseService.saveDepense(depense);
            }

            depenseTempService.deleteDepenseTemp(depenseTemp);
            i++;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void importAllCSV(String csvFile1 , String csvFile2 , String csvFile3 , User manager)throws  Exception {

        this.importCSVCustomer(csvFile1 , manager);
        this.importCSVBudget(csvFile2);
        depenseTempService.importCsvTicketLeadTemporary(csvFile3);
        this.importCSVTicketLead(manager);
    }


}
