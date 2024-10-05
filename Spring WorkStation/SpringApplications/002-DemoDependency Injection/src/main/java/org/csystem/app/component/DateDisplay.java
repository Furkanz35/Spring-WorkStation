package org.csystem.app.component;

import com.karandev.util.spring.datetime.BeanName;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@Component
public class DateDisplay {

    private LocalDate localDate;
    private DateTimeFormatter formatter;

    @Autowired
    public void setToday(@Qualifier(BeanName.CURRENT_DATE) LocalDate today) {
        localDate = today;
    }

    @Autowired
    public void setFormatter(@Qualifier(BeanName.DATE_FORMATTER_TR) DateTimeFormatter dateTimeFormatter) {
        formatter = dateTimeFormatter;
    }

    @PostConstruct
    public void display(){
        System.out.printf("Today:%s\n", formatter.format(localDate));
    }

}
