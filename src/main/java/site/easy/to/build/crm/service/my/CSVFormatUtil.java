package site.easy.to.build.crm.service.my;

import org.springframework.stereotype.Service;

@Service
public class CSVFormatUtil {
    public static String refactorAmountFormat(String column) {
        column = column.replace("\'" , "");
        column = column.replace(".", "");
        column = column.replace(",", ".");
        column = column.replace(" ", "");

        return column;
    }
}
