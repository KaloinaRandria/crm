package site.easy.to.build.crm.service.my;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import site.easy.to.build.crm.entity.my.DepenseTemp;
import site.easy.to.build.crm.repository.my.DepenseTempRepository;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@Service
public class DepenseTempService {
    @Autowired
    DepenseTempRepository depenseTempRepository;

    @Autowired
    CustomerServiceImpl customerService;

    @PersistenceContext
    EntityManager entityManager;

    public void saveDepenseTemp(DepenseTemp depenseTemp) {
        depenseTempRepository.save(depenseTemp);
    }

    public List<DepenseTemp> getAllDepenseTemp() {
        return depenseTempRepository.findAll();
    }

    public void importCSVDepenseTemp(String csvFile) {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()){
            String [] column;

            csvReader.readNext();

            while ((column = csvReader.readNext()) != null) {
                DepenseTemp depenseTemp = new DepenseTemp();

                if (customerService.findByEmail(column[0]) == null) {
                    throw new Exception("Customer doesn't exists");
                }

                depenseTemp.setCustomerEmail(column[0]);
                depenseTemp.setSubjectOrName(column[1]);
                depenseTemp.setType(column[2]);
                depenseTemp.setStatus(column[3]);
                depenseTemp.setExpense(CSVFormatUtil.refactorAmountFormat(column[4]));

                this.saveDepenseTemp(depenseTemp);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du fichier CSV", e);
        }
    }

    @Transactional
    public String importCsvTicketLeadTemporary(String csvFile) throws SQLException {
        String errorMessage ="";
        try {
            // Définir un parser avec le séparateur ';'
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = reader.readAll(); // Lire toutes les lignes
            reader.close();

            // Ignorer la première ligne (en-têtes)
            allData.remove(0);

            int i = 1;
            boolean isError = false;
            for (String[] column : allData) {
                try {

                    if (customerService.findByEmail(column[0]) == null) {
                        throw new Exception("Customer doesn't exists");
                    }

                    DepenseTemp depenseTemp = new DepenseTemp();

                    depenseTemp.setCustomerEmail(column[0]);
                    depenseTemp.setSubjectOrName(column[1]);
                    depenseTemp.setType(column[2]);
                    depenseTemp.setStatus(column[3]);
                    depenseTemp.setExpense(CSVFormatUtil.refactorAmountFormat(column[4]));


                    entityManager.persist(depenseTemp);

                } catch (Exception e) {
                    isError = true;
                    errorMessage = "Ligne "+i +" :"+ Arrays.toString(column) +" MESSAGE : "+e.getMessage();
                    System.out.println(errorMessage);
                    break;
                }
                i++;
            }
            if (isError == true)
            {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return errorMessage;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
