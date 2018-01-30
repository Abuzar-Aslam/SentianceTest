package com.abitech.abuzaraslam.sentiancetest.location_provider.location;

import android.content.Context;
import android.location.Location;

import com.abitech.abuzaraslam.sentiancetest.location_provider.OnLocationUpdatedListener;
import com.abitech.abuzaraslam.sentiancetest.location_provider.location.config.LocationParams;
import com.abitech.abuzaraslam.sentiancetest.location_provider.utils.Logger;


/**
 * Created by mrm on 20/12/14.
 */
public interface LocationProvider {
    void init(Context context, Logger logger);

    void start(OnLocationUpdatedListener listener, LocationParams params);

    void stop();

    Location getLastLocation();

}
