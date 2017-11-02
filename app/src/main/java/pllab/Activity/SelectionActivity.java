package pllab.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zhy.autolayout.AutoLayoutActivity;

import Global.GV;
import Objects.HRV;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/11/1.
 */

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("debug", "###SelectionActivity onCreate###");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        //關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton hrv_ImageButton = findViewById(R.id.hrv_ImageButton);
        hrv_ImageButton.setOnClickListener(this);
    }

    // 開啟 SelectionActivity
    public void OpenActivity(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); //淡入淡出
    }

    @Override
    public void onClick(View v) {
        ButAct butact = new ButAct();
        Bundle bundle = new Bundle();

        switch (v.getId()) {
            case R.id.hrv_ImageButton:
                GV.query.setDataType("HRV");
                OpenActivity(v);
                break;
        }
    }
}