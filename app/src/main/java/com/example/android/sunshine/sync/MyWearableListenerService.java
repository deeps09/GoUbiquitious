package com.example.android.sunshine.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class MyWearableListenerService extends WearableListenerService {

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.d(MyWearableListenerService.class.getSimpleName(), "onDataChanged: Sync request received");
        for (DataEvent dataEvent : dataEventBuffer){
            DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
            String dataPath = dataEvent.getDataItem().getUri().getPath();
            if (dataPath.equals("/weather_request")){
                Intent intentToSyncImmediately = new Intent(this, SunshineSyncIntentService.class);
                getApplicationContext().startService(intentToSyncImmediately);
            }
        }
    }
}

