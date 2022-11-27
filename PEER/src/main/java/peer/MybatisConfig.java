package peer;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class MybatisConfig {
	
	@MapperScan /* Mapper 어노테이션 인식을 위해서 반드시 기입 - 에러발생 이유 */
	@Configuration /* 환경설정 어노테이션 */
	@PropertySource("classpath:/application.properties")          
	public class DataAccessConfig {
		
		@ConfigurationProperties(prefix = "spring.datasource")    
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
		}
		
		@Bean
		public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
			SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
			
			factoryBean.setDataSource(dataSource);
			factoryBean.setMapperLocations(
					new PathMatchingResourcePatternResolver().getResources("static/mapper/*.xml")
					);
			
			factoryBean.setTypeAliasesPackage("peer.model"); /* Alias 설정 */
			return factoryBean.getObject();
		}
		
		@Bean
		public SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory) {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}

}
