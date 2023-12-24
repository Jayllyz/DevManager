package org.main;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Test
    @DisplayName("Test if the start date is valid")
    void testIsStartDateValid() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date nextWeek = new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
        format.format(nextWeek);
        Date nextMonth = new Date(new Date().getTime() + 30L * 24 * 60 * 60 * 1000);
        format.format(nextMonth);
        Project project = new Project("Project 1", 1, "Description 1", nextWeek, nextMonth, null);
        assertTrue(project.isStartDateValid());
    }

}