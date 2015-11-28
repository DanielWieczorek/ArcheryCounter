package io.dwi.archerycounter.logic.shotcount.to;

/**
 * Created by llllllllllll on 11/8/2015.
 */
public class ShotCountEto {

    private int amount;
    private DistanceEto distance;

    public ShotCountEto(){
    }

    public int getAmount() {
        return amount;
    }

    public DistanceEto getDistance() {
        return distance;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDistance(DistanceEto distance) {
        this.distance = distance;
    }
}
