package com.sample.app.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.app.entity.Employee;
import com.sample.app.lineaggregators.EmployeeJsonLineAggregator;
import com.sample.app.mappers.EmployeeRowMapper;
import com.sample.app.validators.EmployeeValidator;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcCursorItemReader<Employee> jdbcCursorItemReader() {
		JdbcCursorItemReader<Employee> cursorItemReader = new JdbcCursorItemReader<>();

		cursorItemReader.setSql("SELECT id, first_name, last_name FROM employee ORDER BY first_name");
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setRowMapper(new EmployeeRowMapper());
		return cursorItemReader;
	}

	@Bean
	public FlatFileItemWriter<Employee> jsonFileItemWriter() throws Exception {
		FlatFileItemWriter<Employee> flatFileItemWriter = new FlatFileItemWriter<>();

		flatFileItemWriter.setLineAggregator(new EmployeeJsonLineAggregator());
		String outFilePath = "/Users/Shared/result.json";

		flatFileItemWriter.setResource(new FileSystemResource(outFilePath));

		flatFileItemWriter.afterPropertiesSet();

		return flatFileItemWriter;
	}

	@Bean
	public ValidatingItemProcessor<Employee> validatingItemProcessor() {
		ValidatingItemProcessor<Employee> itemProcessor = new ValidatingItemProcessor<>(new EmployeeValidator());
		
		itemProcessor.setFilter(true);

		return itemProcessor;
	}

	@Bean
	public Step step1() throws Exception {
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(3).reader(jdbcCursorItemReader())
				.processor(validatingItemProcessor()).writer(jsonFileItemWriter()).build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager)
			throws Exception {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}


