package uk.co.connorglennon.androidengineerexercise;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import uk.co.connorglennon.androidengineerexercise.realm.RealmAccount;
import uk.co.connorglennon.androidengineerexercise.realm.RealmAccountDetails;
import uk.co.connorglennon.androidengineerexercise.realm.RealmController;
import uk.co.connorglennon.androidengineerexercise.validation.ButtonHandler;
import uk.co.connorglennon.androidengineerexercise.validation.EditTextHandler;
import uk.co.connorglennon.androidengineerexercise.validation.FormActivity;

public class AccountDetailsActivity extends FormActivity implements CalendarDatePickerDialogFragment.OnDateSetListener{

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    public static final int PICK_IMAGE = 1;
    public static final int CAPTURE_IMAGE = 2;

    RealmAccount currentAccount;
    RealmAccountDetails accountDetails;

    Button btnNext, btnChangePhoto;
    private boolean hasForename, hasSurname, hasUsername, hasDOB,
            hasAge, hasCountry, hasGender, hasAddress;

    private EditText etForename, etSurname, etUsername, etDOB, etAge, etAddress;
    private Spinner spinnerCountry;
    private RadioGroup rgGender;
    private RadioButton rbChecked;
    private ImageView imgProfilepicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        btnNext = (Button) findViewById(R.id.btnNext);
        checkValidation();

        currentAccount = RealmController.getInstance()
                .getAccount(getIntent().getStringExtra("email"));

        if(currentAccount == null)
        {
            Toast.makeText(this, "Error loading account", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            EditText email = (EditText) findViewById(R.id.inputEmail);
            email.setText(getIntent().getStringExtra("email"));
        }

        accountDetails = new RealmAccountDetails();

        etForename = (EditText) findViewById(R.id.inputForename);
        etSurname = (EditText) findViewById(R.id.inputSurname);
        etUsername = (EditText) findViewById(R.id.inputUserName);
        etDOB = (EditText) findViewById(R.id.inputDOB);
        etAge = (EditText) findViewById(R.id.inputAge);
        etAddress = (EditText) findViewById(R.id.inputAddress);

        EditTextHandler.addValidator(this, etForename, "", "Invalid forename");
        EditTextHandler.addValidator(this, etSurname, "", "Invalid surname");
        EditTextHandler.addValidator(this, etUsername, "", "Invalid username");
        EditTextHandler.addValidator(this, etDOB, "", "Invalid dob");
        EditTextHandler.addValidator(this, etAge, "", "Invalid age");
        EditTextHandler.addValidator(this, etAddress, "", "Invalid address");

        hasForename = false;
        hasSurname = false;
        hasUsername = false;
        hasDOB = false;
        hasAge = false;
        hasCountry = false;
        hasGender = false;
        hasAddress = false;

        spinnerCountry = (Spinner)findViewById(R.id.spinnerCountryPicker);
        initSpinner(spinnerCountry);
        /*
        TODO: Redo radio buttons in a better way.
         */
        rgGender = (RadioGroup) findViewById(R.id.radioGroupGenderSelector);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rbChecked = (RadioButton) findViewById(i);
                hasGender = true;
                switch(i)
                {
                    case R.id.rbMale:
                        accountDetails.setGender("Male");
                        checkValidation();
                        break;
                    case R.id.rbFemale:
                        accountDetails.setGender("Female");
                        checkValidation();
                        break;
                    case R.id.rbNotSpecified:
                        accountDetails.setGender("Prefer not to say");
                        checkValidation();
                        break;
                }
                }
        });

        imgProfilepicture = (ImageView) findViewById(R.id.imgProfilePicture);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TitleBarFragment titleBarFragment = (TitleBarFragment) getFragmentManager().
                findFragmentById(R.id.fragmentTitleBar);
        titleBarFragment.setTitle("Edit Details");
    }

    public void initAccountDetails()
    {
        accountDetails.setForename(etForename.getText().toString());
        accountDetails.setSurname(etSurname.getText().toString());
        accountDetails.setUsername(etUsername.getText().toString());
        accountDetails.setDob(etDOB.getText().toString());
        accountDetails.setAge(etAge.getText().toString());
        accountDetails.setCountry(spinnerCountry.getSelectedItem().toString());
        accountDetails.setAddress(etAddress.getText().toString());
    }

    public void handleOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnNext:

                initAccountDetails();
                RealmController.getInstance().saveAccountDetails(currentAccount, accountDetails);
                RealmController.getInstance().saveAccount(currentAccount);
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);

                break;
            case R.id.btnDatePicker:
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(AccountDetailsActivity.this)
                        .setThemeCustom(R.style.betterPickerCalendar);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
                break;
            case R.id.btnChangePhotoGallery:
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;

            case R.id.btnChangePhotoCamera:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAPTURE_IMAGE);
                }
                break;
            case R.id.btnBack:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;

        switch (requestCode) {

            case PICK_IMAGE:
                if (data != null) {
                    Uri path = data.getData();
                    imgProfilepicture.setImageURI(path);
                    //imgProfilepicture.setImageBitmap(bitmap);
                    BitmapDrawable drawable = (BitmapDrawable) imgProfilepicture.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    accountDetails.setProfilePhoto(saveImage(bitmap));
                }
                break;
            case CAPTURE_IMAGE:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imgProfilepicture.setImageBitmap(imageBitmap);
                BitmapDrawable drawable = (BitmapDrawable) imgProfilepicture.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                accountDetails.setProfilePhoto(saveImage(bitmap));
                break;
        }
    }

    private byte[] saveImage(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        StringBuilder dobBuilder = new StringBuilder();
        if(dayOfMonth < 10) dobBuilder.append("0");
        dobBuilder.append(dayOfMonth).append("/");
        if(monthOfYear < 10) dobBuilder.append("0");
        dobBuilder.append(monthOfYear).append("/");
        if(year < 10) dobBuilder.append("0");
        dobBuilder.append(year);
        etDOB.setText(dobBuilder.toString());
    }

    @Override
    protected void setFlag(EditText editText, boolean flag) {
        switch (editText.getId())
        {
            case R.id.inputForename:
                hasForename = flag;
                break;

            case R.id.inputSurname:
                hasSurname = flag;
                break;

            case R.id.inputUserName:
                hasUsername = flag;
                break;

            case R.id.inputDOB:
                hasDOB = flag;
                break;

            case R.id.inputAge:
                hasAge = flag;
                break;

            case R.id.inputAddress:
                hasAddress = flag;
                break;
        }
    }

    @Override
    protected void checkValidation() {
        if(hasForename && hasSurname && hasUsername
                && hasDOB && hasAge && hasGender)
        {
            ButtonHandler.enableButton(btnNext);
        }
        else
        {
            ButtonHandler.disableButton(btnNext);
        }
    }

    private void initSpinner(Spinner spinner)
    {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countriesArrayList = new ArrayList<>();
        for(Locale locale: locales)
        {
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countriesArrayList.contains(country)) {
                countriesArrayList.add(country);
            }
        }
        Collections.sort(countriesArrayList);
        String[] countriesStrings = new String[countriesArrayList.size()];
        countriesArrayList.toArray(countriesStrings);
        int spinnerItemResource =
                android.R.layout.simple_spinner_item;
        int spinnerDropDownItemResource =
                android.R.layout.simple_spinner_dropdown_item;
        ArrayAdapter<String> countriesAdapter =
                new ArrayAdapter<String>(this, spinnerItemResource, countriesStrings);
        countriesAdapter.setDropDownViewResource(spinnerDropDownItemResource);
        spinner.setAdapter(countriesAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hasCountry = true;
                checkValidation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hasCountry = false;
                checkValidation();
            }
        });
    }
}
