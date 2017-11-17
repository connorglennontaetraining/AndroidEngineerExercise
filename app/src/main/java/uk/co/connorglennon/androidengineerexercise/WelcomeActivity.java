package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private static Class dest = SignUpActivity.class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void handleOnClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnNext:
                Intent intent = new Intent(this, dest);
                startActivity(intent);
                break;
        }
    }
}
