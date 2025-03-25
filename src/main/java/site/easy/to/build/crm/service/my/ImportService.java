package site.easy.to.build.crm.service.my;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;

import java.io.FileReader;

@Service
public class ImportService {
    CustomerServiceImpl customerService;

    public void importCSVCustomer(String csvFile) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFile));
            String[] column;

            csvReader.readNext();

            Customer customer;
            while ((column = csvReader.readNext()) != null) {
                customer = new Customer();
                customer.setEmail(column[0]);
                customer.setName(column[1]);
                customer.setPhone("0345623498");
                customer.setCountry("MDG");

                customerService.save(customer);
            }
            csvReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
