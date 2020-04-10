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
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.app.model.Employee;

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
	public StaxEventItemReader<Employee> reader() {

		StaxEventItemReader<Employee> staxEventItemReader = new StaxEventItemReader<>();

		Map<String, Class> aliases = new HashMap<>();
		aliases.put("employee", Employee.class);

		XStreamMarshaller unMarshaller = new XStreamMarshaller();
		unMarshaller.setAliases(aliases);

		staxEventItemReader.setResource(new ClassPathResource("/xml/emps.xml"));
		staxEventItemReader.setFragmentRootElementName("employee");
		staxEventItemReader.setUnmarshaller(unMarshaller);

		return staxEventItemReader;
	}

	@Bean
	public JdbcBatchItemWriter<Employee> employeeItemWriter() {
		JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();

		writer.setDataSource(dataSource);
		writer.setSql("INSERT INTO EMPLOYEE VALUES (:id, :firstName, :lastName)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
		writer.afterPropertiesSet();

		return writer;
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(5).reader(reader())
				.writer(employeeItemWriter()).build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}

