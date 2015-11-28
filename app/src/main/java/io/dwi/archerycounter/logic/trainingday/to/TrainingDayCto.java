package io.dwi.archerycounter.logic.trainingday.to;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;

/**
 * Created by llllllllllll on 11/9/2015.
 */
public class TrainingDayCto {
    private DateTime date;
    private List<ShotCountEto> shotCounts;

    public TrainingDayCto(){
        shotCounts = new ArrayList<ShotCountEto>();
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<ShotCountEto> getShotCounts() {
        return shotCounts;
    }

    public void setShotCounts(List<ShotCountEto> shotCounts) {
        this.shotCounts = shotCounts;
    }

    public void addShotCount(ShotCountEto shotCount){
        shotCounts.add(shotCount);
    }
}
