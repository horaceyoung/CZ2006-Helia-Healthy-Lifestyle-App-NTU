package com.zy.helia.RecommendedActivities;

public class RecommendedActivities {
    private String name = "Jogging";
    private boolean outdoor = true;
    private float outdoorTemperatureLimit = 35;
    private float rainPrediction = 2;
    private float UVIndexLimit = 8;
    private float pollutantStandardIndexLimit = 100;
    private int imageReference;

    public RecommendedActivities(String name, boolean outdoor){
        this.name = name;
        this.outdoor = outdoor;
    }


    public RecommendedActivities(String name, boolean outdoorOrIndoor, float outdoorTemperatureLimit, float rainPrediction, float UVIndexLimit, float pollutantStandardIndexLimit, int imageReference){
        this.name = name;
        this.outdoor = outdoorOrIndoor;
        this.outdoorTemperatureLimit = outdoorTemperatureLimit;
        this.rainPrediction = rainPrediction;
        this.UVIndexLimit = UVIndexLimit;
        this.pollutantStandardIndexLimit = pollutantStandardIndexLimit;
        this.imageReference = imageReference;
    }


    public boolean getOutdoorOrIndoor(){
        return outdoor;
    }

    public int getImageReference() { return imageReference;}

    public boolean isOutdoorTemperatureApproprieate(float currentTemperature){
        if(!outdoor){
            return true;
        }
        else if(currentTemperature <= outdoorTemperatureLimit){
            return true;
        }
        else{
            return false;
        }
    }
}
