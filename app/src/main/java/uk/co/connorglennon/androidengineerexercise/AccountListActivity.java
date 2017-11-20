package uk.co.connorglennon.androidengineerexercise;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.connorglennon.androidengineerexercise.realm.AccountsAdapter;
import uk.co.connorglennon.androidengineerexercise.realm.RealmAccount;
import uk.co.connorglennon.androidengineerexercise.realm.RealmAccountDetails;
import uk.co.connorglennon.androidengineerexercise.realm.RealmController;
import uk.co.connorglennon.androidengineerexercise.validation.EditTextHandler;
import uk.co.connorglennon.androidengineerexercise.validation.InputValidatorListener;

public class AccountListActivity extends AppCompatActivity {

    private RealmController realmController;
    private ArrayList<RealmAccount> accounts;
    private RecyclerView rvAccountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        RealmAccount currentAccount;
        if(getIntent().getStringExtra("email") != null)
        {
            currentAccount = RealmController.getInstance().getAccount(getIntent().getStringExtra("email"));
            RealmAccountDetails accountDetails = currentAccount.getAccountDetails();
            ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
            profilePicture.setImageBitmap(accountDetails.getProfilePhoto());

            TextView forename = (TextView) findViewById(R.id.forename);
            forename.setText(accountDetails.getForename());

            TextView surname = (TextView) findViewById(R.id.surname);
            surname.setText(accountDetails.getSurname());
        }

        realmController = RealmController.getInstance();
        accounts = realmController.getAllAccounts();
        initRecyclerView();
    }

    public void initRecyclerView()
    {
        rvAccountList = (RecyclerView) findViewById(R.id.rvAccounts);
        rvAccountList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvAccountList.setAdapter(new AccountsAdapter(accounts, R.layout.rv_row_account_details, getApplicationContext()));
    }

    public void handleOnClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnBack:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TitleBarFragment titleBarFragment = (TitleBarFragment) getFragmentManager().
                findFragmentById(R.id.fragmentTitleBar);
        titleBarFragment.setTitle("Accounts");
    }
}
