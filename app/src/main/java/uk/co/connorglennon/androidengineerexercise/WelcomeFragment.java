package uk.co.connorglennon.androidengineerexercise;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WelcomeFragment extends Fragment {

    private static Class createAccountDest = SignUpActivity.class;
    private static Class signInDest = SignInActivity.class;
    private Intent intent;

//    public void handleOnClick(View view)
//    {
//        switch(view.getId())
//        {
//            case R.id.btnCreateAccount:
////                intent = new Intent(this, createAccountDest);
////                startActivity(intent);
//                break;
//            case R.id.btnSignIn:
////                intent = new Intent(this, signInDest);
////                startActivity(intent);
//                break;
//        }
//    }

    public WelcomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
