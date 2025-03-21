package site.easy.to.build.crm.service.DataManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResetData {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResetData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    

}
