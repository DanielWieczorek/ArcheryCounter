package io.dwi.archerycounter.logic.shotcount.impl.usecase;

import io.dwi.archerycounter.logic.shotcount.api.UcManageShotCount;
import io.dwi.archerycounter.logic.shotcount.to.ShotCountEto;

/**
 * Created by llllllllllll on 11/8/2015.
 */
public class UcManageShotCountImpl implements UcManageShotCount {

    @Override
    public void saveShotCount(ShotCountEto shotCount) {

    }

    @Override
    public void addShots(ShotCountEto shotCount, int amount) {
        int newAmount = shotCount.getAmount() + amount;
        shotCount.setAmount(newAmount);
    }

    @Override
    public void removeShots(ShotCountEto shotCount, int amount) {
        int newAmount = Math.max(shotCount.getAmount() - amount,0);
        shotCount.setAmount(newAmount);
    }
}
