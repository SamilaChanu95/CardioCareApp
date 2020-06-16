package com.example.cardiocareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String DATABASE_NAME = "cardiologydatabase";

    SQLiteDatabase mDatabase;

    //defining views
    EditText editTextCNIC, editTextCFirstName, editTextCLastName,
            editTextCAddress, editTextCDOB, editTextCPhoneNumber,
            editTextHospital, editTextDepartment, editTextUnit,
            editTextWard;
    Spinner spinnerCGender, spinnerCRole, spinnerCStatus;
    TextView textViewViewConsultants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCNIC = findViewById(R.id.editTextCNIC);
        editTextCFirstName = findViewById(R.id.editTextCFirstName);
        editTextCLastName = findViewById(R.id.editTextCLastName);
        editTextCPhoneNumber = findViewById(R.id.editTextCPhoneNumber);
        editTextCAddress = findViewById(R.id.editTextCAddress);
        editTextHospital = findViewById(R.id.editTextHospital);
        editTextUnit = findViewById(R.id.editTextUnit);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        editTextWard = findViewById(R.id.editTextWard);
        editTextCDOB = findViewById(R.id.editTextCDOB);
        spinnerCGender = findViewById(R.id.spinnerCGender);
        spinnerCRole = findViewById(R.id.spinnerCRole);
        spinnerCStatus = findViewById(R.id.spinnerCStatus);

        textViewViewConsultants = findViewById(R.id.textViewViewConsultants);

        findViewById(R.id.buttonAddConsultant).setOnClickListener(this);
        textViewViewConsultants.setOnClickListener(this);

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createConsultantTable();
    }

    //this method will create the table
    //as we are going to call this method everytime we will launch the application
    //I have added IF NOT EXISTS to the SQL
    //so it will only create the table when the table is not already created
    private void createConsultantTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS consultant (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT consultant_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    c_nic varchar(50) NOT NULL,\n" +
                        "    c_first_name varchar(100) NOT NULL,\n" +
                        "    c_last_name varchar(100) NOT NULL,\n" +
                        "    c_gender varchar(50) NOT NULL,\n" +
                        "    c_address varchar(255) NOT NULL,\n" +
                        "    c_dob varchar(50) NOT NULL,\n" +
                        "    c_phone_number varchar(20) NOT NULL,\n" +
                        "    c_role varchar(50) NOT NULL,\n" +
                        "    c_status varchar(255) NOT NULL,\n" +
                        "    hospital_id INTEGER NOT NULL,\n" +
                        "    department_id INTEGER NOT NULL,\n" +
                        "    unit_id INTEGER NOT NULL,\n" +
                        "    ward_id INTEGER NOT NULL\n" +
                        ");"
        );
    }

    //this method will validate the inputs
    //dept does not need validation as it is a spinner and it cannot be empty
    private boolean inputsAreCorrect(String c_nic, String c_first_name, String c_last_name, String c_address, String c_dob, String c_phone_number) {
        if (c_nic.isEmpty()) {
            editTextCNIC.setError("Please enter NIC");
            editTextCNIC.requestFocus();
            return false;
        }

        if (c_first_name.isEmpty()) {
            editTextCFirstName.setError("Please enter First name");
            editTextCFirstName.requestFocus();
            return false;
        }

        if (c_last_name.isEmpty()) {
            editTextCLastName.setError("Please enter Last name");
            editTextCLastName.requestFocus();
            return false;
        }

        if (c_address.isEmpty()) {
            editTextCAddress.setError("Please enter Address");
            editTextCAddress.requestFocus();
            return false;
        }

        if (c_dob.isEmpty()) {
            editTextCDOB.setError("Please enter Date of Birth");
            editTextCDOB.requestFocus();
            return false;
        }

        if (c_phone_number.isEmpty()) {
            editTextCPhoneNumber.setError("Please enter Phone Number");
            editTextCPhoneNumber.requestFocus();
            return false;
        }

        return true;
    }

    private void createConsultant() {

        String cNIC = editTextCNIC.getText().toString().trim();
        String cFirstName = editTextCFirstName.getText().toString().trim();
        String cLastName = editTextCLastName.getText().toString().trim();
        String cGender = spinnerCGender.getSelectedItem().toString();
        String cAddress = editTextCAddress.getText().toString().trim();
        String cDOB = editTextCDOB.getText().toString().trim();
        String cPhoneNumber = editTextCPhoneNumber.getText().toString().trim();
        String cRole = spinnerCRole.getSelectedItem().toString();
        String cStatus = spinnerCStatus.getSelectedItem().toString();

        //int hospital_id = (int) editTextHospital.getHospital_id();

        int hospital = Integer.parseInt(editTextHospital.getText().toString());
        int department = Integer.parseInt(editTextDepartment.getText().toString());
        int unit = Integer.parseInt(editTextUnit.getText().toString());
        int ward = Integer.parseInt(editTextWard.getText().toString());

        //String Hospital = editTextHospital.getText().toString();
        //int cWard = Integer.parseInt(ward);


        /*
            //validating the inputs
            if (TextUtils.isEmpty(cNIC)){
                editTextCNIC.setError("Please enter NIC");
                editTextCNIC.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(cFirstName)) {
                editTextCFirstName.setError("Please enter First name");
                editTextCFirstName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(cLastName)) {
                editTextCLastName.setError("Please enter Last name");
                editTextCLastName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(cAddress)) {
                editTextCAddress.setError("Please enter Address");
                editTextCAddress.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(cDOB)) {
                editTextCDOB.setError("Please enter DOB");
                editTextCDOB.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(cPhoneNumber)) {
                editTextCPhoneNumber.setError("Please enter Phone Number");
                editTextCPhoneNumber.requestFocus();
                return;
            }

         */

        if(inputsAreCorrect(cNIC, cFirstName, cLastName, cAddress, cDOB, cPhoneNumber))
        {
            String insertSQL = "INSERT INTO consultant \n" +
                    "(c_nic, c_first_name, c_last_name, c_gender, c_address, c_dob, c_phone_number, c_role, c_status, hospital_id, department_id, unit_id, ward_id)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query
            mDatabase.execSQL(insertSQL, new String[]{cNIC, cFirstName, cLastName, cGender, cAddress, cDOB, cPhoneNumber, cRole, cStatus, String.valueOf(hospital), String.valueOf(department), String.valueOf(unit), String.valueOf(ward)});

            Toast.makeText(this, "Consultant Added Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonAddConsultant:
                createConsultant();
                break;

            case R.id.textViewViewConsultants:
                startActivity(new Intent(this, ConsultantActivity.class));
                break;
        }
    }
}
