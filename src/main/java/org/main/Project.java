package org.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Project {
    private String name;
    private int priority;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<Techno> technoList;
    private Team team;

    public Project(String name, int priority, String description, Date startDate, Date endDate, List<Techno> technoList){
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technoList = technoList;
    }

    public boolean isStartDateValid(){
        if(this.startDate == null){
            return false;
        }
        System.out.println(this.startDate);

        Date today = new Date();
        return !this.startDate.before(today);
    }

    public boolean isEndDateValid(){
        if(this.endDate == null){
            return false;
        }

        return !this.endDate.before(this.startDate);
    }

//    public Date getDateFormat(String date) {
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        try{
//            return format.parse(date);
//        } catch (Exception e){
//            return null;
//        }
//    }
}
