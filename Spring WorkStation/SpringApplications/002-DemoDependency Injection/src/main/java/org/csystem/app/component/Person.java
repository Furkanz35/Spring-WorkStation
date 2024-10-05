package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import com.karandev.util.spring.datetime.configuration.formatter.EducationStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component()
public class Person {
    private String name;

    private String surname;

    private LocalDate birthDate;

    private EducationStatus educationStatus;

    public Person() {
    }

    @Autowired
    public Person(@Qualifier("melike") String name, @Qualifier("melikezengin") String surname, @Qualifier("melikebirthdate")LocalDate birthDate, @Qualifier("melikedoctor") EducationStatus educationStatus) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.educationStatus = educationStatus;
    }

    @Autowired
    public void setEducationStatus(@Qualifier("melikemaster") EducationStatus educationStatus) {
        this.educationStatus = educationStatus;
    }


    @PostConstruct
    void write() {
        Console.writeLine(toString());
    }

    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", educationStatus=" + educationStatus.name() +
                '}';
    }
}

