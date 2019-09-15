package xiaobudong.byz;

import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class DemomyApplication  implements CommandLineRunner{
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemomyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(dataSource.toString());
		showData();
	}

	public void showData() throws SQLException {
		jdbcTemplate.queryForList("select * from foo")
			.forEach(row ->log.info(row.toString()));
	}
}
