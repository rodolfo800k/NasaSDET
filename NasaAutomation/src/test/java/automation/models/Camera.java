package automation.models;

public class Camera {

    private long id;
    private String name;
    private long rover_id;
    private String full_name;

    public Camera(){}

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

    public long getRover_id() {
        return rover_id;
    }

    public void setRover_id(long rover_id) {
        this.rover_id = rover_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }

        if(object.getClass() != this.getClass()){
            return false;
        }

        Camera camera = (Camera) object;
        if(this.id != camera.id){
            return false;
        }

        if(!this.name.equalsIgnoreCase(camera.name)){
            return false;
        }

        if(this.rover_id != camera.rover_id){
            return false;
        }

        if(!this.full_name.equalsIgnoreCase( camera.full_name)){
            return false;
        }

        return true;
    }


}
