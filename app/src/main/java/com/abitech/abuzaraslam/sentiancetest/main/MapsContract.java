package com.abitech.abuzaraslam.sentiancetest.main;

import android.view.View;

/**
 * Created by abuzar.aslam on 1/29/2018.
 */

public interface MapsContract {

    interface MapsView {

        void ShowGeoFancing();
        void ClearGeoFancing();

    }

    interface MapsPresenter {

        void handleClearGeoFancing(View view);
        void handleAddGeoFancing(View view);
        void saveGeoFance();

    }

}
