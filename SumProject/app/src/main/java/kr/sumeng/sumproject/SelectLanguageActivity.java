package kr.sumeng.sumproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

//언어선택 엑티비티
public class SelectLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
    }

    public void btnChinaClicked(View v) {
        Intent mMap = new Intent(SelectLanguageActivity.this, dMapActivity.class);
        if (v.getId() == R.id.btn1) {
            mMap.putExtra("lang", "korean");
        } else if (v.getId() == R.id.btn2) {
            mMap.putExtra("lang", "english");
        } else if (v.getId() == R.id.btn3) {
            mMap.putExtra("lang", "chinese");
        }

        startActivity(mMap);

    }



}
