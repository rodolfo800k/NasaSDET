package automation.models;

import java.time.LocalDate;

public class Rover {

    private long id;
    private String name;
    private LocalDate landing_date;
    private LocalDate launch_date;
    private String status;

    public Rover(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLanding_date() {
        return landing_date;
    }

    public void setLandingDate(LocalDate landingDate) {
        this.landing_date = landingDate;
    }

    public LocalDate getLaunch_date() {
        return launch_date;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launch_date = launchDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }

        if(object.getClass() != this.getClass()){
            return false;
        }

        Rover rover = (Rover) object;
        if(this.id != rover.id){
            return false;
        }

        if(!this.name.equalsIgnoreCase( rover.name)){
            return false;
        }

        if(!this.status.equalsIgnoreCase(rover.status)){
            return false;
        }

        if(!this.landing_date.isEqual(rover.landing_date)){
            return false;
        }

        if(!this.launch_date.isEqual(rover.launch_date)){
            return false;
        }

        return true;
    }



}
