package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uk.co.connorglennon.androidengineerexercise.validation.ButtonFormHandler;
import uk.co.connorglennon.androidengineerexercise.validation.EditTextFormHandler;
import uk.co.connorglennon.androidengineerexercise.validation.FormActivity;
import uk.co.connorglennon.androidengineerexercise.validation.InputValidator;
import uk.co.connorglennon.androidengineerexercise.validation.InputValidatorListener;

public class SignUpActivity extends FormActivity implements InputValidatorListener{

    private static Class dest = AccountDetailsActivity.class;

    private boolean isValidEmail, isValidPassword1, isValidPassword2, isMatchingPassword;

    Button btnNext;
    private EditText etEmail, etPassword1, etPassword2;
    private InputValidator emailValidator, password1Validator, password2Validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        isValidEmail = false;
        isValidPassword1 = false;
        isValidPassword2 = false;
        isMatchingPassword = false;

        btnNext = (Button) findViewById(R.id.btnNext);
        //checkValidation();

        etEmail = (EditText) findViewById(R.id.inputEmail);
        etPassword1 = (EditText) findViewById(R.id.inputPassword1);
        etPassword2 = (EditText) findViewById(R.id.inputPassword2);

        emailValidator = new InputValidator(etEmail, Constants.REGEX_EMAIL_RFC5332_OFFICIAL_STANDARD, "Invalid email");
        password1Validator = new InputValidator(etPassword1, Constants.REGEX_PASSWORD, "Invalid password");
        password2Validator = new InputValidator(etPassword2, Constants.REGEX_PASSWORD, "Invalid password");

        emailValidator.addListener(this);
        password1Validator.addListener(this);
        password2Validator.addListener(this);

        etEmail.addTextChangedListener(emailValidator);
        etPassword1.addTextChangedListener(password1Validator);
        etPassword2.addTextChangedListener(password2Validator);
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
        switch(view.getId())
        {
            case R.id.btnNext:
                Intent intent = new Intent(this, dest);
                startActivity(intent);
                break;
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
            if (etPassword1.getText().toString().equals(etPassword2.getText().toString())) {
                ButtonFormHandler.enableButton(btnNext);
                return;
            }
            else
            {
                EditTextFormHandler.setInvalid(etPassword2, "Passwords do not match");
                ButtonFormHandler.disableButton(btnNext);
            }
        }
        ButtonFormHandler.disableButton(btnNext);
    }
}
