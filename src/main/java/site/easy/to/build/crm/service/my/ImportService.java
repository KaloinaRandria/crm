package site.easy.to.build.crm.service.my;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;

import java.io.FileReader;

@Service
public class ImportService {
    @Autowired
    CustomerServiceImpl customerService;

    public void importCSVCustomer(String csvFile) {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build()) // Définir ';' comme séparateur
                .build()) {

            String[] column;
            csvReader.readNext(); // Ignorer l'en-tête

            while ((column = csvReader.readNext()) != null) {
                if (column.length < 2) {
                    System.err.println("Ligne invalide détectée : " + String.join(";", column));
                    continue; // Ignorer les lignes incorrectes
                }

                Customer customer = new Customer();
                customer.setEmail(column[0].trim());
                customer.setName(column[1].trim());
                customer.setPhone("0345623498");
                customer.setCountry("MDG");

                customerService.save(customer);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du fichier CSV", e);
        }
    }
}
