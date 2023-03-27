package pl.com.great.nysa.it.image.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public GridFsTemplate gridFsTemplate(MongoDatabaseFactory mongoDatabaseFactory, MongoConverter mongoConverter) {
        return new GridFsTemplate(mongoDatabaseFactory, mongoConverter);
    }
}
