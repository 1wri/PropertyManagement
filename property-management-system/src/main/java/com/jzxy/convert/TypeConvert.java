package com.jzxy.convert;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

/**
 * @author 龙殇
 * @version 1.0
 * @description 类型转换器
 * @date 2023/3/7 23:36
 */

@Configuration
public class TypeConvert {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder(){

        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                LocalDateSerializer localDateSerializer = new LocalDateSerializer(
                        DateTimeFormatter.ofPattern("yyyy年MM月dd日")
                );

                LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
                        DateTimeFormatter.ofPattern("yyyy年MM月dd日")
                );

                jacksonObjectMapperBuilder.serializers(localDateSerializer);
                jacksonObjectMapperBuilder.serializers(localDateTimeSerializer);



            }
        };
    }

}
