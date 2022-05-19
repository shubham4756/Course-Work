package com.database.operations.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.database.operations.batch.TableUpdateWriter;
import com.database.operations.batch.TableUpdateReader;
import com.database.operations.batch.TableUpdateProcessor;
import com.database.operations.model.RowDetails;

/**
 * configures our Batch Processing and step
 */
@Configuration
public class DatabaseUpdateJobConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	/**
	 * ConfigurationTableUpdateReader specified as the item reader
	 * 
	 * @return table reader
	 */
	@Bean
	@Qualifier("dbConfigUpdateJobReader")
	public ItemReader<RowDetails> getItemReader() {
		return new TableUpdateReader();
	}

	/**
	 * ConfigurationTableUpdateProcessor specified as the item processor
	 * 
	 * @return table processor
	 */
	@Bean
	@Qualifier("dbConfigUpdateJobProcessor")
	public ItemProcessor<RowDetails, RowDetails> getItemProcessor() {
		return new TableUpdateProcessor();
	}

	/**
	 * ConfigurationTableUpdateWriter specified as item writer
	 * 
	 * @return table writer
	 */
	@Bean
	@Qualifier("dbConfigUpdateJobWriter")
	public ItemWriter<RowDetails> getItemWriter() {
		return new TableUpdateWriter();
	}

	@Bean
	@Qualifier("dbConfigUpdateJob")
	public Job job() {
		Step step = stepBuilderFactory.get("Static-DB-Update-Step").<RowDetails, RowDetails>chunk(100)
				.reader(getItemReader()).processor(getItemProcessor()).writer(getItemWriter()).build();

		Job job = jobBuilderFactory.get("Static-DB-Update-Job").incrementer(new RunIdIncrementer()).start(step).build();
		return job;
	}
}
