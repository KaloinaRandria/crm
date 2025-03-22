package site.easy.to.build.crm.service.DataManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;

@Service
public class ResetData {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResetData(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void initBase() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

        jdbcTemplate.execute("TRUNCATE TABLE contract_settings");
        jdbcTemplate.execute("TRUNCATE TABLE email_template");
        jdbcTemplate.execute("TRUNCATE TABLE file");
        jdbcTemplate.execute("TRUNCATE TABLE google_drive_file");
        jdbcTemplate.execute("TRUNCATE TABLE lead_action");
        jdbcTemplate.execute("TRUNCATE TABLE lead_settings");
        jdbcTemplate.execute("TRUNCATE TABLE ticket_settings");
        jdbcTemplate.execute("TRUNCATE TABLE trigger_contract");
        jdbcTemplate.execute("TRUNCATE TABLE trigger_lead");
        jdbcTemplate.execute("TRUNCATE TABLE trigger_ticket");

        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }
}
