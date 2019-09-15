package xiaobudong.byz;

import java.util.Properties;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionLogFilter  extends FilterEventAdapter {

	@Override
	public void connection_connectBefore(FilterChain chain, Properties info) {
		log.info("BEFORE CONNECTION!");
	}

	@Override
	public void connection_connectAfter(ConnectionProxy connection) {
		log.info("AFTER CONNECTION!");
	}

}
