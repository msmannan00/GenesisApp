package com.darkweb.genesissearchengine.appManager.externalNavigationManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.darkweb.genesissearchengine.appManager.activityContextManager;
import com.darkweb.genesissearchengine.appManager.homeManager.homeController.homeController;
import com.darkweb.genesissearchengine.appManager.landingManager.landingController;
import com.darkweb.genesissearchengine.constants.constants;
import com.darkweb.genesissearchengine.constants.status;
import com.darkweb.genesissearchengine.helperManager.helperMethod;
import com.example.myapplication.R;

import java.util.Collections;

import static com.darkweb.genesissearchengine.constants.enums.etype.M_INITIALIZE_TAB_LINK;

public class externalNavigationController extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(status.sSettingIsAppStarted){
            finish();
            Uri data = externalNavigationController.this.getIntent().getData();
            activityContextManager.getInstance().getHomeController().onOpenLinkNewTab(data.toString());


            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.darkweb.genesissearchengine");
                startActivity(launchIntent);
            }, 500);

            return;
        }
        setContentView(R.layout.home_view);
        Intent intent = new Intent(this.getIntent());
        intent.setClassName(this.getApplicationContext(), homeController.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri data = this.getIntent().getData();
        if(data!=null){
            activityContextManager.getInstance().getHomeController().onOpenLinkNewTab(data.toString());
        }
        this.startActivity(intent);

        new Thread(){
            public void run(){
                try {
                    sleep(1000);
                    externalNavigationController.this.runOnUiThread(() -> finish());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.darkweb.genesissearchengine");
        startActivity(launchIntent);

    }


    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        Uri data = intent.getData();
        if(data!=null){
            activityContextManager.getInstance().getHomeController().onOpenLinkNewTab(data.toString());
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.darkweb.genesissearchengine");
            startActivity(launchIntent);
        }
    }

}
