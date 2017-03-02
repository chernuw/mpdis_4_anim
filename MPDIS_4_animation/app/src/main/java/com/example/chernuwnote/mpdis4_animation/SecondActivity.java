package com.example.chernuwnote.mpdis4_animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity implements View.OnClickListener {

    Button changeActivity;
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.changeActivity:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        changeActivity = (Button) findViewById(R.id.changeActivity);
        changeActivity.setOnClickListener(this);
    }
}
