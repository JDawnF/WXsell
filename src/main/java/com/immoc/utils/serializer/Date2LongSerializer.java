package com.immoc.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @program: sell
 * @description: 时间序列化,把date类型转为long类型
 * @author: baichen
 * @create: 2018-08-21 14:15
 **/
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException,JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
