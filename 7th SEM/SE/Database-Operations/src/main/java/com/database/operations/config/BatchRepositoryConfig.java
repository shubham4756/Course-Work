package com.database.operations.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * configures our database for all operations
 */
@Configuration
@EnableBatchProcessing
public class BatchRepositoryConfig extends DefaultBatchConfigurer {
	private DataSource dataSource;

	/**
	 * specification of the data source
	 */
	@Override
	@Autowired
	@Qualifier("jobRepositoryDataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	/**
	 * creates the job repository, specifying the database type, table prefix,
	 * isolation level,data source, transaction manager
	 */
	@Override
	protected JobRepository createJobRepository() throws Exception {
		JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
		factoryBean.setDatabaseType(DatabaseType.H2.getProductName());
		factoryBean.setTablePrefix("BATCH_");
		factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		factoryBean.setDataSource(dataSource);
		factoryBean.setTransactionManager(platformTransactionManager);
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	/**
	 * job explorer
	 */
	@Override
	protected JobExplorer createJobExplorer() throws Exception {
		JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
		factoryBean.setDataSource(this.dataSource);
		factoryBean.setTablePrefix("BATCH_");
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	/**
	 * returning the data source
	 *
	 * @return data source for spring batch processing
	 */
	@Bean(destroyMethod = "shutdown")
	public EmbeddedDatabase dataSourceH2() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
				.addScript("classpath:org/springframework/batch/core/schema-h2.sql").build();
	}
}