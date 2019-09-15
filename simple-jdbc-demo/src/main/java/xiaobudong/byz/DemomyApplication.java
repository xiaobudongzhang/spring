package xiaobudong.byz;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


@SpringBootApplication
public class DemomyApplication implements CommandLineRunner {
	@Autowired
	private FooDao fooDao;
	public static void main(String[] args) {
		SpringApplication.run(DemomyApplication.class, args);
	}

	@Bean
	@Autowired
	public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("FOO")
			.usingGeneratedKeyColumns("ID");
	}

	@Bean
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void run(String... args) throws Exception {
		fooDao.insertData();
	
		fooDao.listData();
	}
}
