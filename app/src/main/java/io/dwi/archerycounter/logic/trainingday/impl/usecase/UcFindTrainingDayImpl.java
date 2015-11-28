package io.dwi.archerycounter.logic.trainingday.impl.usecase;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import io.dwi.archerycounter.logic.shotcount.to.DistanceEto;
import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;
import io.dwi.archerycounter.logic.trainingday.api.UcFindTrainingDay;
import io.dwi.archerycounter.logic.trainingday.to.TrainingDayCto;

/**
 * Created by llllllllllll on 11/9/2015.
 */
public class UcFindTrainingDayImpl implements UcFindTrainingDay {
    @Override
    public TrainingDayCto findTrainingDay(DateTime date) {
        TrainingDayCto trainingDay = new TrainingDayCto();

        ShotCountEto shotCount = new ShotCountEto();
        DistanceEto distance = new DistanceEto();
        shotCount.setDistance(distance);
        shotCount.setAmount(20);
        distance.setDistance(50);
        trainingDay.addShotCount(shotCount);

        ShotCountEto shotCount2 = new ShotCountEto();
        DistanceEto distance2 = new DistanceEto();
        shotCount2.setDistance(distance2);
        shotCount2.setAmount(45);
        distance2.setDistance(18);
        trainingDay.addShotCount(shotCount2);

        trainingDay.setDate(date);
        return trainingDay;
    }
    private TrainingDayCto theOnlyOne;

    @Override
    public TrainingDayCto findTrainingDayByIndex(int index) {
        if(theOnlyOne == null) {
            TrainingDayCto trainingDay = new TrainingDayCto();

            ShotCountEto shotCount = new ShotCountEto();
            DistanceEto distance = new DistanceEto();
            shotCount.setDistance(distance);
            shotCount.setAmount(index);
            distance.setDistance(50);
            trainingDay.addShotCount(shotCount);
            DateTime date = DateTime.now();
            date = date.minusDays(index);
            trainingDay.setDate(date);

            ShotCountEto shotCount2 = new ShotCountEto();
            DistanceEto distance2 = new DistanceEto();
            shotCount2.setDistance(distance2);
            shotCount2.setAmount(45);
            distance2.setDistance(18);
            trainingDay.addShotCount(shotCount2);
            theOnlyOne = trainingDay;
        }
        return theOnlyOne;
    }
}
