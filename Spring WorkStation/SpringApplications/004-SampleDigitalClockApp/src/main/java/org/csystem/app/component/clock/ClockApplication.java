package org.csystem.app.component.clock;

import com.karandev.util.console.Console;
import com.karandev.util.spring.datetime.BeanName;
import org.csystem.util.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.TimeUnit;

@Component
public class ClockApplication implements ApplicationRunner {

    private final DateTimeFormatter formatter;
    private final ApplicationContext applicationContext;

    private String test;

    public ClockApplication(@Qualifier(BeanName.DATE_TIME_FORMATTER_TR) DateTimeFormatter formatter, ApplicationContext applicationContext) {
        this.formatter = formatter;
        this.applicationContext = applicationContext;
    }

    private void clockSchedulerCallback() {
        var localDateTime = applicationContext.getBean(BeanName.CURRENT_DATE_TIME);
        Console.write("TTESSSTT %s\r", formatter.format((TemporalAccessor) localDateTime));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var scheduler = new Scheduler(1, TimeUnit.SECONDS);
        scheduler.schedule(this::clockSchedulerCallback);
    }
}
