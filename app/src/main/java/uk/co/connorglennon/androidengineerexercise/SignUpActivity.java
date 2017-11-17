package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private static Class dest = AccountDetailsActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TitleBarFragment titleBarFragment = (TitleBarFragment) getFragmentManager().
                findFragmentById(R.id.fragmentTitleBar);
        titleBarFragment.setTitle("Sign Up");
    }

    public void handleOnClick(View view)
    {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
        switch(view.getId())
        {
            case R.id.btnNext:
                Intent intent = new Intent(this, dest);
                startActivity(intent);
                break;
        }
    }
}
