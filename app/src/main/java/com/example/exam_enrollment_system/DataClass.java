package com.example.exam_enrollment_system;

public class DataClass {
    private String imageURl;

    public DataClass(){
    }

    public DataClass(String toString) {
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }



    public DataClass(String imageURl, String caption) {
        this.imageURl = imageURl;

    }
}
