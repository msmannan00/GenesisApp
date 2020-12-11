package com.darkweb.genesissearchengine.appManager.settingManager.logManager;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.darkweb.genesissearchengine.appManager.activityContextManager;
import com.darkweb.genesissearchengine.appManager.helpManager.helpController;
import com.darkweb.genesissearchengine.constants.constants;
import com.darkweb.genesissearchengine.constants.keys;
import com.darkweb.genesissearchengine.constants.status;
import com.darkweb.genesissearchengine.dataManager.dataController;
import com.darkweb.genesissearchengine.dataManager.dataEnums;
import com.darkweb.genesissearchengine.helperManager.eventObserver;
import com.darkweb.genesissearchengine.helperManager.helperMethod;
import com.darkweb.genesissearchengine.pluginManager.pluginController;
import com.example.myapplication.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class settingLogController extends AppCompatActivity {

    /* PRIVATE VARIABLES */
    private SwitchMaterial mListView;
    private settingLogModel mSettingLogModel;
    private settingLogViewController mSettingLogViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pluginController.getInstance().onCreate(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_log_view);

        viewsInitializations();
    }

    public void viewsInitializations() {
        mListView = findViewById(R.id.pListView);
        mSettingLogViewController = new settingLogViewController(this, new settingLogViewCallback(), mListView);

        mSettingLogModel = new settingLogModel(new settingLogModelCallback());
    }

    public void onOpenInfo(View view) {
        helperMethod.openActivity(helpController.class, constants.CONST_LIST_HISTORY, this,true);
    }

    /* LISTENERS */
    public class settingLogViewCallback implements eventObserver.eventListener{

        @Override
        public Object invokeObserver(List<Object> data, Object e_type)
        {
            return null;
        }
    }


    public class settingLogModelCallback implements eventObserver.eventListener{

        @Override
        public Object invokeObserver(List<Object> data, Object e_type)
        {
            return null;
        }
    }

    /* LOCAL OVERRIDES */

    @Override
    public void onResume()
    {
        activityContextManager.getInstance().setCurrentActivity(this);
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /*UI Redirection*/

    public void onClose(View view){
        finish();
    }

    public void onSwitchLogUIMode(View view){
        mSettingLogModel.onTrigger(settingLogEnums.eLogModel.M_SWITCH_LOG_VIEW, Collections.singletonList(!mListView.isChecked()));
        mListView.toggle();
        activityContextManager.getInstance().getOrbotLogController().initializeLogs();
        dataController.getInstance().invokePrefs(dataEnums.ePreferencesCommands.M_SET_BOOL, Arrays.asList(keys.SETTING_LIST_VIEW, status.sLogListView));
    }
}