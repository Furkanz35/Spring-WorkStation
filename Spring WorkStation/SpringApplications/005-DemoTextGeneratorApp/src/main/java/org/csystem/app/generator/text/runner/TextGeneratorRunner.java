package org.csystem.app.generator.text.runner;

import com.karandev.util.console.Console;
import com.karandev.util.spring.datetime.BeanName;
import org.csystem.util.scheduler.Scheduler;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@Component
public class TextGeneratorRunner implements ApplicationRunner {
    private final Scheduler m_scheduler;
    private final BufferedWriter m_bufferedWriter;
    private final ApplicationContext m_applicationContext;
    private final RandomGenerator m_randomGenerator;
    private final DateTimeFormatter m_dateTimeFormatter;
    @Value("${generator.min:1}")
    private  int min;
    @Value("${generator.bound:10}")
    private  int bound;
    @Value("${generator.count:10}")
    private int count;
    @Value("${generator.n:5}")
    private int n;


    private void writeTextCallback(String text)
    {
        try {
            var nowStr = m_dateTimeFormatter.format(m_applicationContext.getBean(BeanName.CURRENT_DATE_TIME, LocalDateTime.class));

            m_bufferedWriter.write(text + " " + nowStr);
            m_bufferedWriter.newLine();
            m_bufferedWriter.flush();
        }
        catch (IOException ignore) {
            Console.Error.writeLine("Problem occurred while writing text!...:");
            m_scheduler.cancel();
        }
    }

    private String generateCallback()
    {
        return StringUtil.getRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(min, bound));
    }

    private void textGeneratorCallback()
    {
        Console.writeLine(n + ". scheduler");
        if (n-- > 0)
            Stream.generate(this::generateCallback).limit(count).forEach(this::writeTextCallback);
        else
            m_scheduler.cancel();
    }

    public TextGeneratorRunner(Scheduler scheduler, BufferedWriter bufferedWriter, ApplicationContext applicationContext,
                               RandomGenerator randomGenerator,
                               @Qualifier(BeanName.DATE_TIME_FORMATTER_TR) DateTimeFormatter dateTimeFormatter)
    {
        m_scheduler = scheduler;
        m_bufferedWriter = bufferedWriter;
        m_applicationContext = applicationContext;
        m_randomGenerator = randomGenerator;
        m_dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_scheduler.schedule(this::textGeneratorCallback);
    }
}
