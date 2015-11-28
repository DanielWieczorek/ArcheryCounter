package io.dwi.archerycounter.logic.trainingday.api;

import org.joda.time.DateTime;

import io.dwi.archerycounter.logic.trainingday.to.TrainingDayCto;

/**
 * Created by llllllllllll on 11/9/2015.
 */
public interface UcFindTrainingDay {

    public TrainingDayCto findTrainingDay(DateTime date);

    public TrainingDayCto findTrainingDayByIndex(int index);
}
