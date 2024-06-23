package cn.example.order;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (Exception e) {
        }
        return null;
    }
}
