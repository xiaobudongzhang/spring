package xiaobudong.byz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemomyApplication implements CommandLineRunner {
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemomyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("count before trans:{}", getCount());

		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus){
				jdbcTemplate.execute("insert into foo(id,bar) values (3, 'aaaaa')");
				log.info("count:{}", getCount());
				transactionStatus.setRollbackOnly();
			}
		});
		log.info("count after trans:{}", getCount());
	}

	private long getCount() {
		return (long) jdbcTemplate.queryForList("select count(*) as cnt from foo")
			.get(0).get("cnt");
	}
}