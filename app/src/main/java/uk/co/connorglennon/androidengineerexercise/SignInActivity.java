package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import uk.co.connorglennon.androidengineerexercise.realm.RealmAccount;
import uk.co.connorglennon.androidengineerexercise.realm.RealmController;
import uk.co.connorglennon.androidengineerexercise.validation.ButtonHandler;
import uk.co.connorglennon.androidengineerexercise.validation.EditTextHandler;
import uk.co.connorglennon.androidengineerexercise.validation.FormActivity;
import uk.co.connorglennon.androidengineerexercise.validation.InputValidatorListener;

public class SignInActivity extends FormActivity {

    public static Class dest = AccountListActivity.class;

    EditText etEmail, etPassword1;
    boolean hasValidEmail, hasValidPassword;
    ImageButton btnRevealPassword1;
    Button btnNextSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = (EditText) findViewById(R.id.inputEmail);
        etPassword1 = (EditText) findViewById(R.id.inputPassword1);

        EditTextHandler.addValidator(this, etEmail, Constants.REGEX_EMAIL_RFC5332_OFFICIAL_STANDARD, "Invalid email");
        EditTextHandler.addValidator(this, etPassword1, Constants.REGEX_PASSWORD, "Invalid password");

        btnRevealPassword1 = (ImageButton) findViewById(R.id.btnRevealPassword1);
        btnRevealPassword1.setTag(0);

        btnNextSignIn = (Button) findViewById(R.id.btnNextSignIn);
        checkValidation();
    }

    public void handleOnClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnNextSignIn:
                if(checkAccount())
                {
                    if(checkPassword())
                    {
                        Intent intent = new Intent(this, dest);
                        intent.putExtra("email", etEmail.getText().toString());
                        startActivity(intent);
                    }
                    else
                    {
                        InputValidatorListener listener = this;
                        listener.isInvalid(etPassword1, "Incorrect password");
                    }
                }
                else
                {
                    InputValidatorListener listener = this;
                    listener.isInvalid(etEmail, "No account with this email");
                }
                break;
            case R.id.btnRevealPassword1:
                EditTextHandler.togglePassword(etPassword1);
                toggleEye(btnRevealPassword1);
                break;
            case R.id.btnBack:
                onBackPressed();
                break;
        }
    }

    public boolean checkAccount()
    {
        RealmAccount realmAccount =
                RealmController.getInstance().getAccount(etEmail.getText().toString());
        if(realmAccount == null) return false;
        else return true;
    }

    public boolean checkPassword()
    {
        RealmAccount realmAccount =
                RealmController.getInstance().getAccount(etEmail.getText().toString());
        return realmAccount.getPassword().equals(etPassword1.getText().toString());
    }

    private void toggleEye(ImageButton imageButton)
    {
        if((Integer)imageButton.getTag() == 0)
        {
            imageButton.setImageDrawable(getDrawable(R.drawable.eye));
            imageButton.setTag(1);
        }
        else
        {
            imageButton.setImageDrawable(getDrawable(R.drawable.eye_off));
            imageButton.setTag(0);
        }
    }

    @Override
    protected void setFlag(EditText editText, boolean flag) {
        switch (editText.getId())
        {
            case R.id.inputEmail:
                hasValidEmail = flag;
                break;
            case R.id.inputPassword1:
                hasValidPassword = flag;
                break;
        }
    }

    @Override
    protected void checkValidation() {
        if(hasValidEmail && hasValidPassword)
        {
            ButtonHandler.enableButton(btnNextSignIn);
        }
        else
        {
            ButtonHandler.disableButton(btnNextSignIn);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TitleBarFragment titleBarFragment = (TitleBarFragment) getFragmentManager().
                findFragmentById(R.id.fragmentTitleBar);
        titleBarFragment.setTitle("Sign In");
    }
}
