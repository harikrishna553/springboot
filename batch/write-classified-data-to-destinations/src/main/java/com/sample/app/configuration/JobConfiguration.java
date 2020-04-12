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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.app.classifier.EmployeeClassifier;
import com.sample.app.entity.Employee;
import com.sample.app.lineaggregators.EmployeeJsonLineAggregator;
import com.sample.app.mappers.EmployeeRowMapper;

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
	public StaxEventItemWriter<Employee> xmlFileItemWriter() throws Exception {
		StaxEventItemWriter<Employee> staxEventItemWriter = new StaxEventItemWriter<>();

		Map<String, Class> aliases = new HashMap<>();
		aliases.put("employee", Employee.class);

		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliases);

		staxEventItemWriter.setRootTagName("employees");
		staxEventItemWriter.setMarshaller(marshaller);
		String outFilePath = "/Users/Shared/result.xml";
		staxEventItemWriter.setResource(new FileSystemResource(outFilePath));

		staxEventItemWriter.afterPropertiesSet();

		return staxEventItemWriter;
	}

	@Bean
	public ClassifierCompositeItemWriter<Employee> classifierCompositeItemWriter() throws Exception {
		ClassifierCompositeItemWriter<Employee> writer = new ClassifierCompositeItemWriter<>();

		EmployeeClassifier empClassifier = new EmployeeClassifier(xmlFileItemWriter(), jsonFileItemWriter());

		writer.setClassifier(empClassifier);

		return writer;
	}

	@Bean
	public Step step1() throws Exception {
		return this.stepBuilderFactory.get("step1")
				.<Employee, Employee>chunk(5)
				.reader(jdbcCursorItemReader())
				.writer(classifierCompositeItemWriter())
				.stream(xmlFileItemWriter())
				.stream(jsonFileItemWriter())
				.build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager)
			throws Exception {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}



