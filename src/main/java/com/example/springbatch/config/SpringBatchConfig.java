package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import com.example.springbatch.repository.CustomerRespository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
    private JobBuilder jobBuilder;
    private StepBuilder stepBuilder;
    private CustomerRespository respository;

    @Bean
    public FlatFileItemReader<Customers> reader() {
        FlatFileItemReader fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setResource(new FileSystemResource("src/main/resources/customers-1000.csv"));
        fileItemReader.setName("csvReader");
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setLineMapper(lineMapper());
        return fileItemReader;
    }

    private LineMapper<Customers> lineMapper(){
        DefaultLineMapper<Customers> lineMapper = new DefaultLineMapper<>();

        //This will extract the contenet from CSV file.
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("index","customerId","firstName","lastName","company","city","country","phoneNumber1",
                "phoneNumber2","email","subscriptionDate","website");

        //This will map the content to Java class.

        BeanWrapperFieldSetMapper<Customers> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customers.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

}

