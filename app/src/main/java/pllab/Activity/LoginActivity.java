package pllab.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import Global.GV;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/11/1.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("debug", "###LoginActivity onCreate###");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText uid_text = findViewById(R.id.uid_editText);
                if (!uid_text.getText().toString().equals(null)) {
                    // TODO update userID
                    //GV.query_list.setUserID(uid_text.getText().toString());
                    OpenMainActivity(v);
                }
                else {
                    // TODO user ID could not be null hint(dialog).
                }
            }
        });
    }

    // 開啟 MainActivity
    public void OpenMainActivity(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); //淡入淡出
    }
}
