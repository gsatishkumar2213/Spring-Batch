package com.sample;

import com.sample.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * Created by gsati on 1/31/2018.
 */
@Configuration
@EnableBatchProcessing
public class CustomerConfig {

    @Autowired
    public JobBuilderFactory jobBuildFactory;


    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Autowired
    public DataSource dataSource;
    @Bean
    @Scope("prototype")
    public FlatFileItemReader<Customer> reader(){
        FlatFileItemReader<Customer> input = new FlatFileItemReader<>();
        //  input.setStrict(false);
        input.setResource(new ClassPathResource("Sample.csv"));
        input.setLineMapper(new DefaultLineMapper<Customer>(){{
            setLineTokenizer(productLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>(){{
                setTargetType(Customer.class);
            }});
        }
        });
        return input;

    }
    @Bean
    public CustomerProcessor processor(){
        return new CustomerProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Customer> writer(){
        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<Customer>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
        writer.setSql("INSERT INTO customer (cust_name, cust_email,cust_dob) VALUES ( :custName, :email, :dob)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public LineTokenizer productLineTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range[] { new Range(1, 6), new Range(7, 20), new Range(21, 28) });
        tokenizer.setNames(new String[] {  "custName", "email", "dob" });
        return tokenizer;
    }

    @Bean
    @Scope("prototype")
    public Job importUserJob() {
        return jobBuildFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    @Scope("prototype")
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
