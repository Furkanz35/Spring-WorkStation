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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Component
public class ClockApplicationWithTimer implements ApplicationRunner {

    private final DateTimeFormatter formatter;
    private final ApplicationContext applicationContext;

    private String test;

    public ClockApplicationWithTimer(@Qualifier(BeanName.DATE_TIME_FORMATTER_TR) DateTimeFormatter formatter, ApplicationContext applicationContext) {
        this.formatter = formatter;
        this.applicationContext = applicationContext;
    }

    private void clockSchedulerCallback() {
        var localDateTime = applicationContext.getBean(BeanName.CURRENT_DATE_TIME);
        Console.write("%s \n", formatter.format((TemporalAccessor) localDateTime));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clockSchedulerCallback();
            }
        }, 5000, 1000);
    }
}
