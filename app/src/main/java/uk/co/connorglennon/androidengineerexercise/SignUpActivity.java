package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import uk.co.connorglennon.androidengineerexercise.realm.RealmAccount;
import uk.co.connorglennon.androidengineerexercise.realm.RealmAccountDetails;
import uk.co.connorglennon.androidengineerexercise.realm.RealmController;
import uk.co.connorglennon.androidengineerexercise.validation.ButtonHandler;
import uk.co.connorglennon.androidengineerexercise.validation.EditTextHandler;
import uk.co.connorglennon.androidengineerexercise.validation.FormActivity;
import uk.co.connorglennon.androidengineerexercise.validation.ValidatableEditText;

public class SignUpActivity extends FormActivity implements ValidationListener {

    private static Class dest = AccountDetailsActivity.class;

    //RealmDatabase object that holds the data the form is validating.
    private RealmAccount signUpAccount;

    //Validation flags.
    //TODO: Make these bitwise flags.
    private boolean isValidEmail, isValidPassword1, isValidPassword2, isMatchingPassword, isEmailUsed;

    Button btnNext;
    ImageButton btnRevealPassword1, btnRevealPassword2;

    private ValidatableEditText etEmail, etPassword1, etPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        signUpAccount = new RealmAccount();

        /*
        TODO: have these checked based on save state if it exists.
        Bugs could arise from setting the values to false as a saved state could hold valid values.
         */
        isValidEmail = false;
        isValidPassword1 = false;
        isValidPassword2 = false;
        isMatchingPassword = false;
        isEmailUsed = false;

        btnNext = (Button) findViewById(R.id.btnNext);
        btnRevealPassword1 = (ImageButton) findViewById(R.id.btnRevealPassword1);
        btnRevealPassword2 = (ImageButton) findViewById(R.id.btnRevealPassword2);
        btnRevealPassword1.setTag(0);
        btnRevealPassword2.setTag(0);
        checkValidation();

        /*
        TODO: Consider moving object creation to the addValidator method or another factory method.
        ****** This could reduce lines of code further.
         */



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TitleBarFragment titleBarFragment = (TitleBarFragment) getFragmentManager().
                findFragmentById(R.id.fragmentTitleBar);
        titleBarFragment.setTitle("Sign Up");
    }

    public void initAccount()
    {
        signUpAccount.setEmail(etEmail.editText.getText().toString());
        signUpAccount.setPassword(etPassword1.editText.getText().toString());
        signUpAccount.setAccountDetails(new RealmAccountDetails());
    }

    public boolean checkAccount()
    {
        if(RealmController.getInstance().isEmailUsed(signUpAccount.getEmail()))
        {
            ValidationListener listener = this;
            listener.isInvalid(etEmail.editText, "Email already in use");
            return false;
        }
        return true;
    }

    public void saveAccount()
    {
        RealmController.getInstance().saveAccount(signUpAccount);
    }

    public void handleOnClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnNext:
                initAccount();
                if(checkAccount())
                {
                    saveAccount();
                    Intent intent = new Intent(this, dest);
                    intent.putExtra("email", signUpAccount.getEmail());
                    startActivity(intent);
                }
                break;
            case R.id.btnRevealPassword1:
                EditTextHandler.togglePassword(etPassword1.editText);
                toggleEye(btnRevealPassword1);
                break;
            case R.id.btnRevealPassword2:
                EditTextHandler.togglePassword(etPassword2.editText);
                toggleEye(btnRevealPassword2);
                break;
            case R.id.btnBack:
                onBackPressed();
                break;
        }
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

    protected void setFlag(EditText editText, boolean flag)
    {
        switch (editText.getId())
        {
            case R.id.inputEmail:
                isValidEmail = flag;
                break;
            case R.id.inputPassword1:
                isValidPassword1 = flag;
                break;
            case R.id.inputPassword2:
                isValidPassword2 = flag;
                break;
        }
    }

    @Override
    protected void checkValidation()
    {
        if(isValidEmail && isValidPassword1 && isValidPassword2) {
            if (etPassword1.editText.getText().toString().equals(etPassword2.editText.getText().toString())) {
                ButtonHandler.enableButton(btnNext);
                return;
            }
            else
            {
                EditTextHandler.setInvalid(etPassword2.editText, "Passwords do not match", MyApp.cross);
                ButtonHandler.disableButton(btnNext);
            }
        }
        ButtonHandler.disableButton(btnNext);
    }
}
