package start;

import bbt.BbtGirl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(BbtGirl.class)
@EnableConfigurationProperties(ItarProperties.class)
public class ItarConfiguration {

    @Bean
    public BbtGirl bbtGirl(ItarProperties properties){
        BbtGirl bbtGirl = new BbtGirl();
        bbtGirl.setName(properties.getName());
        bbtGirl.setAge(properties.getAge());
        bbtGirl.setLength(properties.getLength());
        return bbtGirl;
    }
}
