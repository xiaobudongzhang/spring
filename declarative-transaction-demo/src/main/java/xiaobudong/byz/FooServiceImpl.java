package xiaobudong.byz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FooServiceImpl implements FooService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void insertRecord() {
		jdbcTemplate.execute("insert into foo (bar) values ('AAAA')");
	}

	@Override
	@Transactional(rollbackFor = RollbackException.class)
	public void insertThenRollback() throws RollbackException {
		jdbcTemplate.execute("insert into foo (bar) values ('BBB')");
	}

	@Override
	public void invokeInsertThenRollback() throws RollbackException {
		insertThenRollback();
	}
}