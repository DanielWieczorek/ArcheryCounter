package io.dwi.archerycounter.logic.trainingday.impl;

import org.joda.time.DateTime;

import io.dwi.archerycounter.logic.trainingday.api.TrainingDayManager;
import io.dwi.archerycounter.logic.trainingday.api.UcFindTrainingDay;
import io.dwi.archerycounter.logic.trainingday.impl.usecase.UcFindTrainingDayImpl;
import io.dwi.archerycounter.logic.trainingday.to.TrainingDayCto;

/**
 * Created by llllllllllll on 11/9/2015.
 */
public class TrainingDayManagerImpl implements TrainingDayManager{

    private UcFindTrainingDay findTrainingDayUc;

    public TrainingDayManagerImpl(){
        findTrainingDayUc = new UcFindTrainingDayImpl();
    }

    @Override
    public TrainingDayCto findTrainingDay(DateTime date) {
        return findTrainingDayUc.findTrainingDay(date);
    }

    @Override
    public TrainingDayCto findTrainingDayByIndex(int index) {
        return findTrainingDayUc.findTrainingDayByIndex(index);
    }
}
