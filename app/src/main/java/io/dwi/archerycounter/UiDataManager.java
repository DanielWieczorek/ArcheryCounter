package io.dwi.archerycounter;

import io.dwi.archerycounter.logic.shotcount.api.ShotCountManager;
import io.dwi.archerycounter.logic.shotcount.impl.ShotCountManagerImpl;
import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;
import io.dwi.archerycounter.logic.trainingday.api.TrainingDayManager;
import io.dwi.archerycounter.logic.trainingday.impl.TrainingDayManagerImpl;
import io.dwi.archerycounter.logic.trainingday.to.TrainingDayCto;

/**
 * Created by llllllllllll on 11/20/2015.
 */
public class UiDataManager {
    private TrainingDayCto currentTrainingDay;
    private ShotCountEto selectedShotCount;

    private TrainingDayManager trainingDayManager;
    private ShotCountManager shotCountManager;

    private static UiDataManager self;

    private UiDataManager() {
        trainingDayManager = new TrainingDayManagerImpl();
        shotCountManager = new ShotCountManagerImpl();
    }

    public void setTrainingDayById(int index) {
        this.currentTrainingDay = trainingDayManager.findTrainingDayByIndex(index);
        this.selectedShotCount = currentTrainingDay.getShotCounts().get(0);
    }


    public TrainingDayCto getCurrentTrainingDay() {
        return currentTrainingDay;
    }

    public void setCurrentTrainingDay(TrainingDayCto currentTrainingDay) {
        this.currentTrainingDay = currentTrainingDay;
    }

    public ShotCountEto getSelectedShotCount() {
        return selectedShotCount;
    }

    public void setSelectedShotCount(ShotCountEto selectedShotCount) {
        this.selectedShotCount = selectedShotCount;
    }

    public void addShots(int amount) {
        this.shotCountManager.addShots(this.selectedShotCount, amount);
    }

    public void removeShots(int amount) {
        this.shotCountManager.removeShots(this.selectedShotCount, amount);
    }

    public static UiDataManager getInstance(){
        if (self == null)
            self = new UiDataManager();
        return self;
    }
}
