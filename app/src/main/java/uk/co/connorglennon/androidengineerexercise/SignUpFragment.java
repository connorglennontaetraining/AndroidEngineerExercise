package uk.co.connorglennon.androidengineerexercise;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import uk.co.connorglennon.androidengineerexercise.validation.ValidatableEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private ValidatableEditText etEmail, etPassword1, etPassword2;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etEmail = (EditText) getActivity().findViewById(R.id.inputEmail);
        EditText etPassword1 = (EditText) getActivity().findViewById(R.id.inputPassword1);
        EditText etPassword2 = (EditText) getActivity().findViewById(R.id.inputPassword2);

        ValidatableEditText.ValidationMethod emailRegex = new ValidatableEditText.RegexMethod(Constants.REGEX_EMAIL_RFC5332_OFFICIAL_STANDARD);
        ValidatableEditText.ValidationMethod passwordRegex = new ValidatableEditText.RegexMethod(Constants.REGEX_PASSWORD);
        ValidatableEditText.ValidationMarkers validationMarkers = new ValidatableEditText.ValidationMarkers(MyApp.tick, MyApp.cross);
        this.etEmail = new ValidatableEditText(etEmail, "Invalid email", emailRegex, validationMarkers);
        this.etPassword1 = new ValidatableEditText(etPassword1, "Invalid password", passwordRegex, validationMarkers);
        this.etPassword2 = new ValidatableEditText(etPassword2, "Invalid password", passwordRegex, validationMarkers);
    }
}
