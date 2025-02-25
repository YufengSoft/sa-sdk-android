package com.sensorsdata.analytics.android.sdk.visual;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.core.SAModuleManager;
import com.sensorsdata.analytics.android.sdk.core.mediator.ModuleConstants;
import com.sensorsdata.analytics.android.sdk.dialog.SensorsDataDialogUtils;
import com.sensorsdata.analytics.android.sdk.visual.model.ViewNode;

import org.json.JSONObject;

public class SAVisual {
    private static final String TAG = "SA.SaVisual";

    //1、VisualPropertiesManager
    public static void requestVisualConfig() {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME) && SensorsDataAPI.getConfigOptions().isVisualizedPropertiesEnabled()) {
            SAModuleManager.getInstance().getVisualModuleService().requestVisualConfig();
        }
    }

    public static void mergeVisualProperties(JSONObject srcObject, ViewNode viewNode) {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME) && viewNode != null && SensorsDataAPI.getConfigOptions().isVisualizedPropertiesEnabled()) {
            SAModuleManager.getInstance().getVisualModuleService().mergeVisualProperties(srcObject, viewNode);
        }
    }

    public static String getAppVisualConfig() {
        // 可视化的开关未打开，此时 App 内嵌 H5 场景无需支持采集自定义属性；
        if (!SensorsDataAPI.getConfigOptions().isVisualizedPropertiesEnabled() || !SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            return null;
        }
        return SAModuleManager.getInstance().getVisualModuleService().getAppVisualConfig();
    }

    //2、VisualizedAutoTrackService
    public static void resumeVisualService() {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SAModuleManager.getInstance().getVisualModuleService().resumeVisualService();
        }
    }

    public static void stopVisualService() {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SAModuleManager.getInstance().getVisualModuleService().stopVisualService();
        }
    }

    //3、HeatMapService
    public static void resumeHeatMapService() {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SAModuleManager.getInstance().getVisualModuleService().resumeHeatMapService();
        }
    }

    public static void stopHeatMapService() {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SAModuleManager.getInstance().getVisualModuleService().stopHeatMapService();
        }
    }

    //4、h5
    public static void addVisualJavascriptInterface(View webView) {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SAModuleManager.getInstance().getVisualModuleService().addVisualJavascriptInterface(webView);
        }
    }

    //5、SensorsDataDialogUtils
    public static void showPairingCodeInputDialog(Context context) {
        if (SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SAModuleManager.getInstance().getVisualModuleService().showPairingCodeInputDialog(context);
        } else {
            SensorsDataDialogUtils.showDialog(context, "点击热图/可视化模块 SDK 没有被正确集成，请联系贵方技术人员正确集成。");
        }
    }

    public static void showOpenHeatMapDialog(final Activity context, final String featureCode, final String postUrl) {
        if (!SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SensorsDataDialogUtils.showDialog(context, "点击热图 SDK 没有被正确集成，请联系贵方技术人员正确集成。");
            return;
        }
        if (!SensorsDataAPI.sharedInstance().isNetworkRequestEnable()) {
            SensorsDataDialogUtils.showDialog(context, "已关闭网络请求（NetworkRequest），无法使用 App 点击分析，请开启后再试！");
            return;
        }
        if (!SensorsDataAPI.sharedInstance().isHeatMapEnabled()) {
            SensorsDataDialogUtils.showDialog(context, "SDK 没有被正确集成，请联系贵方技术人员开启点击分析。");
            return;
        }
        SAModuleManager.getInstance().getVisualModuleService().showOpenHeatMapDialog(context, featureCode, postUrl);
    }

    public static void showOpenVisualizedAutoTrackDialog(final Activity context, final String featureCode, final String postUrl) {
        if (!SAModuleManager.getInstance().hasModuleByName(ModuleConstants.ModuleName.VISUAL_NAME)) {
            SensorsDataDialogUtils.showDialog(context, "点击可视化 SDK 没有被正确集成，请联系贵方技术人员正确集成。");
            return;
        }
        if (!SensorsDataAPI.sharedInstance().isNetworkRequestEnable()) {
            SensorsDataDialogUtils.showDialog(context, "已关闭网络请求（NetworkRequest），无法使用 App 可视化全埋点，请开启后再试！");
            return;
        }
        if (!SensorsDataAPI.sharedInstance().isVisualizedAutoTrackEnabled()) {
            SensorsDataDialogUtils.showDialog(context, "SDK 没有被正确集成，请联系贵方技术人员开启可视化全埋点。");
            return;
        }
        SAModuleManager.getInstance().getVisualModuleService().showOpenVisualizedAutoTrackDialog(context, featureCode, postUrl);
    }
}



