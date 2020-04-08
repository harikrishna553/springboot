package com.sample.app.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.app.mappers.EmployeeFieldSetMapper;
import com.sample.app.model.Employee;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<Employee> reader() {
		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();

		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setResource(new ClassPathResource("/csv/emps.csv"));

		DefaultLineMapper<Employee> empDefaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "id", "firstName", "lastName" });

		empDefaultLineMapper.setLineTokenizer(lineTokenizer);
		empDefaultLineMapper.setFieldSetMapper(new EmployeeFieldSetMapper());
		empDefaultLineMapper.afterPropertiesSet();

		flatFileItemReader.setLineMapper(empDefaultLineMapper);

		return flatFileItemReader;
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").chunk(5).reader(reader()).writer(emps -> {
			for (Object emp : emps) {
				System.out.println(emp);
			}
		}).build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}

