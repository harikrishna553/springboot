package com.sample.app.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
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
	public JdbcPagingItemReader<Employee> jdbcPagingItemReader() {
		JdbcPagingItemReader<Employee> pagingItemReader = new JdbcPagingItemReader<>();

		pagingItemReader.setDataSource(dataSource);
		pagingItemReader.setFetchSize(3);
		pagingItemReader.setRowMapper(new EmployeeRowMapper());

		MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
		mySqlPagingQueryProvider.setSelectClause("id, first_name, last_name");
		mySqlPagingQueryProvider.setFromClause("FROM employee");

		Map<String, Order> orderByKeys = new HashMap<>();
		orderByKeys.put("first_name", Order.ASCENDING);

		mySqlPagingQueryProvider.setSortKeys(orderByKeys);

		pagingItemReader.setQueryProvider(mySqlPagingQueryProvider);

		return pagingItemReader;
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
		return this.stepBuilderFactory.get("step1").chunk(3).reader(jdbcPagingItemReader()).writer(itemWriter())
				.build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}