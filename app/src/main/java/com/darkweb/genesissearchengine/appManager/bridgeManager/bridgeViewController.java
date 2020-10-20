package com.darkweb.genesissearchengine.appManager.bridgeManager;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.darkweb.genesissearchengine.constants.strings;
import com.darkweb.genesissearchengine.helperManager.helperMethod;
import com.example.myapplication.R;
import java.util.List;

class bridgeViewController
{
    /*Private Variables*/
    private RadioButton mBridgeObfs;
    private RadioButton mBridgeChina;
    private RadioButton mBridgeCustom;
    private Button mBridgeButton;
    private EditText mCustomPort;
    private ImageView mCustomBridgeBlocker;

    /*ViewControllers*/
    private AppCompatActivity mContext;

    /*Initializations*/
    void initialization(EditText pCustomPort, Button pBridgeButton, AppCompatActivity pContext,RadioButton pBridgeObfs,RadioButton pBridgeChina,RadioButton pBridgeCustom, ImageView pCustomBridgeBlocker){
        this.mContext = pContext;
        this.mBridgeObfs = pBridgeObfs;
        this.mBridgeChina = pBridgeChina;
        this.mBridgeCustom = pBridgeCustom;
        this.mBridgeButton = pBridgeButton;
        this.mCustomPort = pCustomPort;
        this.mCustomBridgeBlocker = pCustomBridgeBlocker;

        initPostUI();
    }

    private void initPostUI(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mContext.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                window.setStatusBarColor(mContext.getResources().getColor(R.color.blue_dark));
            }
            else {
                mContext.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
                mContext.getWindow().setStatusBarColor(ContextCompat.getColor(mContext, R.color.white));
            }
        }
    }

    private void animateColor(TextView p_view, int p_from, int p_to, String p_command, int p_duration){
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(p_view, p_command,p_from, p_to);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(p_duration);
        colorAnim.start();
    }

    private void resetRadioButtons(int p_duration){
        animateColor(mBridgeObfs, mBridgeObfs.getCurrentTextColor(), mContext.getResources().getColor(R.color.float_white), "textColor", p_duration);
        animateColor(mBridgeCustom, mBridgeCustom.getCurrentTextColor(), mContext.getResources().getColor(R.color.float_white), "textColor", p_duration);
        animateColor(mBridgeChina, mBridgeChina.getCurrentTextColor(), mContext.getResources().getColor(R.color.float_white), "textColor", p_duration);

        mBridgeObfs.setHighlightColor(mContext.getResources().getColor(R.color.float_white));
        mBridgeCustom.setHighlightColor(mContext.getResources().getColor(R.color.float_white));
        mBridgeChina.setHighlightColor(mContext.getResources().getColor(R.color.float_white));

        mBridgeObfs.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.float_white)));
        mBridgeCustom.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.float_white)));
        mBridgeChina.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.float_white)));

        helperMethod.hideKeyboard(mContext);
        mCustomPort.clearFocus();
        mCustomPort.animate().setDuration(p_duration).alpha(0.2f);
        mBridgeButton.animate().setDuration(p_duration).alpha(0.2f);
        mCustomBridgeBlocker.setVisibility(View.VISIBLE);
    }

    private void initViews(String p_bridge, int p_duration){
        resetRadioButtons(p_duration);
        switch (p_bridge) {
            case strings.BRIDGE_CUSTOM_BRIDGE_OBFS4:

                animateColor(mBridgeChina, mBridgeChina.getCurrentTextColor(), mContext.getResources().getColor(R.color.black), "textColor", p_duration);
                mBridgeChina.setHighlightColor(Color.BLACK);
                mBridgeChina.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.cursor_blue)));
                mBridgeChina.setChecked(true);
                break;
            case strings.BRIDGE_CUSTOM_BRIDGE_MEEK:
                animateColor(mBridgeObfs, mBridgeObfs.getCurrentTextColor(), mContext.getResources().getColor(R.color.black), "textColor", p_duration);
                mBridgeObfs.setHighlightColor(Color.BLACK);
                mBridgeObfs.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.cursor_blue)));
                mBridgeObfs.setChecked(true);
                break;
            case strings.BRIDGE_CUSTOM_BRIDGE_CUSTOM:
                animateColor(mBridgeCustom, mBridgeCustom.getCurrentTextColor(), mContext.getResources().getColor(R.color.black), "textColor", p_duration);
                mBridgeCustom.setHighlightColor(Color.BLACK);
                mBridgeCustom.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.cursor_blue)));
                mBridgeCustom.setChecked(true);

                mCustomPort.animate().setDuration(p_duration).alpha(1f);
                mBridgeButton.animate().setDuration(p_duration).alpha(1f);
                mCustomBridgeBlocker.setVisibility(View.GONE);
                break;
        }
    }

    public void onTrigger(bridgeEnums.eBridgeViewCommands p_commands, List<Object> p_data){
        if(p_commands == bridgeEnums.eBridgeViewCommands.S_INIT_VIEWS){
            initViews((String) p_data.get(0), (int)p_data.get(1));
        }
    }

}
