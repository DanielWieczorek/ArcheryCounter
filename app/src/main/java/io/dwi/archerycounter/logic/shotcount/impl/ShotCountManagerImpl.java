package io.dwi.archerycounter.logic.shotcount.impl;

import io.dwi.archerycounter.logic.shotcount.api.UcManageShotCount;
import io.dwi.archerycounter.logic.shotcount.impl.usecase.UcManageShotCountImpl;
import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;

/**
 * Created by llllllllllll on 11/8/2015.
 */
public class ShotCountManagerImpl implements io.dwi.archerycounter.logic.shotcount.api.ShotCountManager{

    private UcManageShotCount shotCountUc;

    public ShotCountManagerImpl(){
        shotCountUc = new UcManageShotCountImpl();
    }

    @Override
    public void saveShotCount(ShotCountEto shotCount) {
        shotCountUc.saveShotCount(shotCount);
    }

    @Override
    public void addShots(ShotCountEto shotCount, int amount) {
       shotCountUc.addShots(shotCount, amount);
    }

    @Override
    public void removeShots(ShotCountEto shotCount, int amount) {
        shotCountUc.removeShots(shotCount, amount);
    }
}
