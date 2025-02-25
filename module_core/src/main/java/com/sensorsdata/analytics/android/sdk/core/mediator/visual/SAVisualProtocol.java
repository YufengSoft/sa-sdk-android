package com.sensorsdata.analytics.android.sdk.core.mediator.visual;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.core.mediator.protocol.SAModuleProtocol;
import com.sensorsdata.analytics.android.sdk.visual.model.ViewNode;

import org.json.JSONObject;

public interface SAVisualProtocol extends SAModuleProtocol {
    void requestVisualConfig();

    void mergeVisualProperties(JSONObject srcObject, ViewNode viewNode);

    String getAppVisualConfig();

    void resumeVisualService();

    void stopVisualService();

    void addVisualJavascriptInterface(View webView);

    void resumeHeatMapService();

    void stopHeatMapService();

    void showPairingCodeInputDialog(Context context);

    void showOpenHeatMapDialog(final Activity context, final String featureCode, final String postUrl);

    void showOpenVisualizedAutoTrackDialog(final Activity context, final String featureCode, final String postUrl);

}
