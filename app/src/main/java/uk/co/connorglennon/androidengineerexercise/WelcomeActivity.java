package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    private static Class createAccountDest = SignUpActivity.class;
    private static Class signInDest = SignInActivity.class;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void handleOnClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnCreateAccount:
                intent = new Intent(this, createAccountDest);
                startActivity(intent);
                break;
            case R.id.btnSignIn:
                intent = new Intent(this, signInDest);
                startActivity(intent);
                break;
        }
    }
}
