package xiaobudong.byz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Test(expected = CustomDuplicatedKeyException.class)
	public void contextLoads() {
		jdbcTemplate.execute("insert into foo(id,bar)values(1, 'a')");
		jdbcTemplate.execute("insert into foo(id,bar)values(1, 'a')");
	}

}
