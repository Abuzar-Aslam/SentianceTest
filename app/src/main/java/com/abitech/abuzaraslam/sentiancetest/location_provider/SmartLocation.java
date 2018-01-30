package com.abitech.abuzaraslam.sentiancetest.location_provider;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.abitech.abuzaraslam.sentiancetest.location_provider.location.LocationProvider;
import com.abitech.abuzaraslam.sentiancetest.location_provider.location.config.LocationParams;
import com.abitech.abuzaraslam.sentiancetest.location_provider.location.providers.LocationManagerProvider;
import com.abitech.abuzaraslam.sentiancetest.location_provider.utils.Logger;
import com.abitech.abuzaraslam.sentiancetest.location_provider.utils.LoggerFactory;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * Managing class for SmartLocation library.
 */
public class SmartLocation {

    private Context context;
    private Logger logger;
    private boolean preInitialize;

    /**
     * Creates the SmartLocation basic instance.
     *
     * @param context       execution context
     * @param logger        logger interface
     * @param preInitialize TRUE (default) if we want to instantiate directly the default providers. FALSE if we want to initialize them ourselves.
     */
    private SmartLocation(Context context, Logger logger, boolean preInitialize) {
        this.context = context;
        this.logger = logger;
        this.preInitialize = preInitialize;
    }

    public static SmartLocation with(Context context) {
        return new Builder(context).build();
    }

    /**
     * @return request handler for location operations
     */
    public LocationControl location() {
        return location(new LocationManagerProvider());
    }

    /**
     * @param provider location provider we want to use
     * @return request handler for location operations
     */
    public LocationControl location(LocationProvider provider) {
        return new LocationControl(this, provider);
    }




    public static class Builder {
        private final Context context;
        private boolean loggingEnabled;
        private boolean preInitialize;

        public Builder(@NonNull Context context) {
            this.context = context;
            this.loggingEnabled = false;
            this.preInitialize = true;
        }

        public Builder logging(boolean enabled) {
            this.loggingEnabled = enabled;
            return this;
        }

        public SmartLocation build() {
            return new SmartLocation(context, LoggerFactory.buildLogger(loggingEnabled), preInitialize);
        }

    }

    public static class LocationControl {

        private static final Map<Context, LocationProvider> MAPPING = new WeakHashMap<>();

        private final SmartLocation smartLocation;
        private LocationParams params;
        private LocationProvider provider;

        public LocationControl(@NonNull SmartLocation smartLocation, @NonNull LocationProvider locationProvider) {
            this.smartLocation = smartLocation;
            params = LocationParams.BEST_EFFORT;

            if (!MAPPING.containsKey(smartLocation.context)) {
                MAPPING.put(smartLocation.context, locationProvider);
            }
            provider = MAPPING.get(smartLocation.context);

            if (smartLocation.preInitialize) {
                provider.init(smartLocation.context, smartLocation.logger);
            }
        }

        public void start(OnLocationUpdatedListener listener) {
            if (provider == null) {
                throw new RuntimeException("A provider must be initialized");
            }
            provider.start(listener, params);
        }

        public void stop() {
            provider.stop();
        }
    }

}
