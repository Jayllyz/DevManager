package domain.developers;

import shared.Status;

import java.util.List;

public class Projects {
    List<Project> projects;

    public boolean hasInProgressProject(){
        for(Project project : projects) {
            if(project.getStatus() == Status.IN_PROGRESS) {
                return true;
            }
        }
        return false;
    }

    public void add(Project project) {
        projects.add(project);
    }

}
