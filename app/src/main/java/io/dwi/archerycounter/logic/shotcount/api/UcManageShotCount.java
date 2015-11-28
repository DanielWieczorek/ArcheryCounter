package io.dwi.archerycounter.logic.shotcount.api;

import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;

/**
 * Created by llllllllllll on 11/8/2015.
 */
public interface UcManageShotCount {

    void saveShotCount(ShotCountEto shotCount);

    void addShots(ShotCountEto shotCount, int amount);
    void removeShots(ShotCountEto shotCount, int amount);
}
