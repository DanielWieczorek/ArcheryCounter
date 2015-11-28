package io.dwi.archerycounter.logic.shotcount.to;

/**
 * Created by llllllllllll on 11/8/2015.
 */
public class DistanceEto {
    private int distance;

    public DistanceEto(){

    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String toString(){
        return getDistance()+" m";
    }
}
