package com.abitech.abuzaraslam.sentiancetest.main;

import android.view.View;

/**
 * Created by abuzar.aslam on 1/29/2018.
 */

public class MapsPresenter implements MapsContract.MapsPresenter {

    private MapsContract.MapsView mView;




    MapsPresenter(MapsContract.MapsView mview)
    {
        this.mView=mview;
    }

    @Override
    public void handleClearGeoFancing(View view) {



    }

    @Override
    public void handleAddGeoFancing(View view) {



    }

    @Override
    public void saveGeoFance() {



    }
}
