package com.sample.app.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.app.entity.Employee;
import com.smaple.app.mappers.EmployeeRowMapper;

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
	public ItemWriter<? super Object> itemWriter() {
		return emps -> {
			System.out.println("\nWriting chunk to console");
			for (Object emp : emps) {
				System.out.println(emp);
			}
		};
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").chunk(3).reader(jdbcCursorItemReader()).writer(itemWriter())
				.build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}
