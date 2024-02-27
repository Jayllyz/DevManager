package domain.developers;

import shared.Status;
import shared.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Projects {
    List<Project> projects;

    public Projects() {
        projects = new ArrayList<>();
    }

    public boolean hasOngoingProject(){
        for(Project project : projects) {
            if(project.getStatus() == Status.IN_PROGRESS
            || project.getStatus() == Status.WAITING) {
                return true;
            }
        }
        return false;
    }

    public Project blablaabla() throws EntityNotFoundException {
        for(Project project : projects) {
            if(project.getStatus() == Status.IN_PROGRESS
                    || project.getStatus() == Status.WAITING) {
                return project;
            }
        }
        throw new EntityNotFoundException("No project is ongoing");
    }

    public void add(Project project) {
        projects.add(project);
    }

    public List<Project> listProjects() {
        return projects;
    }
}
