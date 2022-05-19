package com.database.operations;

import static java.lang.Long.MAX_VALUE;

import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Starting point of spring batch processing
 */
@SpringBootApplication
@EnableScheduling
public class DatabaseUpdateBatchApplication {

	@Autowired
	private ConfigurableApplicationContext context;
	/**
	 * This JobLauncher Launches the job
	 */
	@Autowired
	private JobLauncher jobLauncher;
	/**
	 * This Job has all further steps
	 */
	@Autowired
	private Job job;

	/**
	 * Main Method
	 * 
	 * @param args argument of cmd
	 */
	public static void main(String[] args) {
		SpringApplication.run(DatabaseUpdateBatchApplication.class, args);
	}

	/**
	 * Schedule Job with initial Delay 1s after completing the job once it stop the
	 * application
	 * 
	 * @throws JobInstanceAlreadyCompleteException
	 * @throws JobExecutionAlreadyRunningException
	 * @throws JobParametersInvalidException
	 * @throws JobRestartException 
	 */
	@Scheduled(fixedDelay = MAX_VALUE, initialDelay = 1000)
	public void perform() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
			JobParametersInvalidException, JobRestartException,
			org.springframework.batch.core.repository.JobRestartException {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
//		context.close();
	}

}
