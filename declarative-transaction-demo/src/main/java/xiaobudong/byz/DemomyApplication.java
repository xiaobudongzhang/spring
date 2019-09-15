package xiaobudong.byz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.AdviceMode;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@Slf4j
public class DemomyApplication implements CommandLineRunner {
	@Autowired
	private FooService fooService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemomyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fooService.insertRecord();
		log.info("AAA {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='AAA'", long.class));
	
		try {
			fooService.insertThenRollback();
		} catch (Exception e) {
			
		}
		log.info("BBB {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='BBB'", long.class));
		try {
			fooService.invokeInsertThenRollback();
		} catch (Exception e) {
			
		}
		log.info("BBB {}", jdbcTemplate.queryForObject("select count(*) from foo where bar= 'BBB'", long.class));
	}
}