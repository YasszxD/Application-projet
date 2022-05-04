package com.example.projectv4.ui;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import com.example.projectv4.R;
import com.example.projectv4.controller.Controller;
import com.example.projectv4.data.AppContract;

public class SignupActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mPasswordEditText;
    private EditText mAgeEditText;
    private EditText mWeightEditText;
    private EditText mHeightEditText;

    private String name;
    private String password;
    private int age;
    private String ageStr;
    private float weight;
    private String weightStr;
    private float height;
    private String heightStr;

    public Controller controller = null;
    private Spinner mGenderSpinner;
    private Spinner mRoleSpinner;

    private Long id;

    private int mGender = 0;
    private int mRole = 0;
    ContentValues values = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        controller = Controller.getInstance();
        init();
        setupSpinnerGender();
        setupSpinnerRole();
    }

    void init() {
        mNameEditText = (EditText) findViewById(R.id.edit_name);
        mPasswordEditText = (EditText) findViewById(R.id.edit_password);
        mAgeEditText = (EditText) findViewById(R.id.edit_age);
        mWeightEditText = (EditText) findViewById(R.id.edit_weight);
        mHeightEditText = (EditText) findViewById(R.id.edit_height);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        mRoleSpinner = (Spinner) findViewById(R.id.spinner_role);
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the actor.
     */
    private void setupSpinnerGender() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = AppContract.Player.MALE; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = AppContract.Player.FEMALE; // Female
                    } else {
                        mGender = AppContract.Player.UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = AppContract.Player.MALE;
            }
        });
    }

    /**
     * Setup the dropdown spinner that allows the user to select the role of the actor.
     */
    private void setupSpinnerRole() {

        ArrayAdapter roleSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_role_options, android.R.layout.simple_spinner_item);

        roleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mRoleSpinner.setAdapter(roleSpinnerAdapter);

        mRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.role_athlete))) {
                        mRole = AppContract.Player.ATHLETE;
                    } else if (selection.equals(getString(R.string.role_coach))) {
                        mRole = AppContract.Player.COACH;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mRole = AppContract.Player.ATHLETE;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                if (checkData()) {
                    if (saveData()) {
                        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "not added", Toast.LENGTH_SHORT).show();
                    }
                }


                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                deleteContent();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteContent() {
        mNameEditText.setText("");
        mAgeEditText.setText("");
        mHeightEditText.setText("");
        mWeightEditText.setText("");
        mPasswordEditText.setText("");

    }

    public boolean checkData() {
        name = mNameEditText.getText().toString().trim().toLowerCase();
        if (name.equals("") || name.split(" ").length != 1) {
            mNameEditText.setError("name incorrect");
            return false;
        }
        if (checkMultipleUsername(name)) {
            mNameEditText.setError("name already used");
            return false;
        }
        password = mPasswordEditText.getText().toString();
        if (password.equals("")) {
            mPasswordEditText.setError("password is empty");
            return false;
        }
        ageStr = mAgeEditText.getText().toString();
        if (ageStr.equals("") || ageStr.equals("0")) {
            mAgeEditText.setError("age is empty");
            return false;
        }
        age = Integer.parseInt(ageStr);

        weightStr = mWeightEditText.getText().toString();
        if (weightStr.equals("") || weightStr.equals("0") || weightStr.charAt(0) == '.') {
            mWeightEditText.setError("Weight is invalid");
            return false;
        }
        weight = Float.parseFloat(weightStr);

        heightStr = mHeightEditText.getText().toString();
        if (heightStr.equals("") || heightStr.equals("0") || heightStr.charAt(0) == '.') {
            mHeightEditText.setError("height is invalid");
            return false;
        }
        height = Float.parseFloat(heightStr);

        return true;
    }

    public boolean saveData() {
        Controller.setContext(this);
        values = new ContentValues();
        values.put(AppContract.Player.COLUMN_NAME, name);
        values.put(AppContract.Player.COLUMN_PASSWORD, password);
        values.put(AppContract.Player.COLUMN_AGE, age);
        values.put(AppContract.Player.COLUMN_GENDER, mGender);
        values.put(AppContract.Player.COLUMN_ROLE, mRole);
        values.put(AppContract.Player.COLUMN_HEIGHT, height);
        values.put(AppContract.Player.COLUMN_WEIGHT, weight);
        id = Controller.setData(AppContract.Player.TABLE_NAME, values);
        if (id == -1)
            return false;
        return true;
    }

    public boolean checkMultipleUsername(String username) {
        Controller.setContext(this);
        return Controller.checkMultipleUsername(username);
    }
}
