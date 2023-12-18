package org.example;

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

    public Project(String name, int priority, String description, String startDate, String endDate, List<Techno> technoList){
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.startDate = getDateFormat(startDate);
        this.endDate = getDateFormat(endDate);
        this.technoList = technoList;
    }

    public boolean isStardDateValid(){
        if(this.startDate == null){
            return false;
        }

        Date today = new Date();
        if(this.startDate.before(today)){
            return false;
        }

        return true;
    }

    public boolean isEndDateValid(){
        if(this.endDate == null){
            return false;
        }

        if(this.endDate.before(this.startDate)){
            return false;
        }

        return true;
    }

    public Date getDateFormat(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date parsedDate = format.parse(date);
            return parsedDate;
        } catch (Exception e){
            return null;
        }
    }
}
