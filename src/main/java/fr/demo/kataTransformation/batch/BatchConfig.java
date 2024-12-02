package fr.demo.kataTransformation.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import fr.demo.kataTransformation.exception.InvalidationNombreException;
import fr.demo.kataTransformation.service.NombreTransformer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private NombreTransformer transformer;

    @Value("${batch.input-file}")
    private String inputFile;

    @Value("${batch.output-file}")
    private String outputFile;

    @Bean
    public Job transformJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("transformJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<String> reader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("numberItemReader")
                .resource(new FileSystemResource(inputFile))
                .lineMapper(new PassThroughLineMapper())
                .build();
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return item -> {
            try {
                int number = Integer.parseInt(item.trim());
                String result = transformer.transforme(number);
                return number + " \"" + result + "\"";
            } catch (InvalidationNombreException e) {
                return item.trim() + " \"Nombre invalide\"";
            } catch (TransactionException e) {
                return item.trim() + " \"Erreur lors de la transformation\"";
            }
        };
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .name("numberItemWriter")
                .resource(new FileSystemResource(outputFile))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }
}
