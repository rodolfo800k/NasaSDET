package automation.models;

import java.time.LocalDate;

public class MarsRoverPhoto {

    private Long id;
    private Long sol;
    private Camera camera;
    private String img_src;
    private LocalDate earth_date;
    private Rover rover;

    public MarsRoverPhoto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getSol() {
        return sol;
    }

    public void setSol(long sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public LocalDate getEarth_date() {
        return earth_date;
    }

    public void setEarth_date(LocalDate earth_date) {
        this.earth_date = earth_date;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }


    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }

        if(object.getClass() != this.getClass()){
            return false;
        }

        MarsRoverPhoto photo = (MarsRoverPhoto) object;
        if(!this.id.equals(photo.id)){
            return false;
        }

        if(!this.sol.equals(photo.sol)){
            return false;
        }

        if(!this.camera.equals(photo.camera)){
            return false;
        }

        if(!this.img_src.equalsIgnoreCase( photo.img_src)){
            return false;
        }

        if(!this.earth_date.isEqual(photo.earth_date)){
            return false;
        }

        if(!this.rover.equals(photo.rover)){
            return false;
        }

        return true;
    }
}
