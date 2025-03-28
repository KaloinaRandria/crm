package site.easy.to.build.crm.service.my;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.service.lead.LeadServiceImpl;
import site.easy.to.build.crm.service.ticket.TicketServiceImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExportService {
    @Autowired
    LeadServiceImpl leadService;

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    BudgetService budgetService;

    public String listToString(List<Lead> leads , List<Ticket> tickets , List<Budget> budgets) {
        String valiny = "";
        valiny += Lead.listToString(leads);
        valiny += Ticket.listToString(tickets);
        valiny += Budget.listToString(budgets);

        return valiny;
    }

    
//    public void exportDataCSV(int customerId) {
//        List<Lead> leadList = leadService.getLeadByCustomerId(customerId);
//        List<Ticket> ticketList = ticketService.getByCustomerId(customerId);
//        List<Budget> budgetList = budgetService.getBudgetByCustomerAndDate(customerId , LocalDateTime.now());
//
//        String result = this.listToString(leadList , ticketList , budgetList);
//
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(
//                    new FileWriter("TestingRawData.csv")));
//
//            ResultSetMetaData rsmd = result.getMetaData();
//            int columnCount = rsmd.getColumnCount();
//
//            for (int i = 1; i < columnCount + 1; i++) {
//                String name = rsmd.getColumnName(i);
//                out.println(name);
//                // print the name
//            }
//
//            while (result.next()) {
//                out.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s",
//                        result.getString(1), result.getString(2),
//                        result.getString(3), result.getString(4),
//                        result.getString(5), result.getString(6),
//                        result.getString(7), result.getString(8)));
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
