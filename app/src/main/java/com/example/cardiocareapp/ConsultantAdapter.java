package com.example.cardiocareapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ConsultantAdapter extends ArrayAdapter<Consultant> {

    Context mCtx;
    int listLayoutRes;
    List<Consultant> consultantList;
    SQLiteDatabase mDatabase;

    public ConsultantAdapter(Context mCtx, int listLayoutRes, List<Consultant> consultantList, SQLiteDatabase mDatabase)
    {
        super(mCtx, listLayoutRes, consultantList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.consultantList = consultantList;
        this.mDatabase = mDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(listLayoutRes, null);

        final Consultant consultant = consultantList.get(position);

        TextView textViewName = view.findViewById(R.id.textViewName);

        textViewName.setText(consultant.getC_first_name());

        Button buttonDelete = view.findViewById(R.id.buttonDelete);
        Button buttonUpdate = view.findViewById(R.id.buttonUpdate);

        //adding a clicklistener to button
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateConsultant(consultant);
            }
        });

        //the delete operation
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM consultant WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{consultant.getId()});
                        reloadConsultantsFromDatabase();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void updateConsultant(final Consultant consultant) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_consultant, null);
        builder.setView(view);

        final EditText editTextCNIC = view.findViewById(R.id.editTextCNIC);
        final EditText editTextCFirstName = view.findViewById(R.id.editTextCFirstName);
        final EditText editTextCLastName = view.findViewById(R.id.editTextCLastName);
        final EditText editTextCPhoneNumber = view.findViewById(R.id.editTextCPhoneNumber);
        final EditText editTextCAddress = view.findViewById(R.id.editTextCAddress);
        final EditText editTextHospital = view.findViewById(R.id.editTextHospital);
        final EditText editTextUnit = view.findViewById(R.id.editTextUnit);
        final EditText editTextDepartment = view.findViewById(R.id.editTextDepartment);
        final EditText editTextWard = view.findViewById(R.id.editTextWard);
        final EditText editTextCDOB = view.findViewById(R.id.editTextCDOB);
        final Spinner spinnerCGender = view.findViewById(R.id.spinnerCGender);
        final Spinner spinnerCRole = view.findViewById(R.id.spinnerCRole);
        final Spinner spinnerCStatus = view.findViewById(R.id.spinnerCStatus);

        editTextCNIC.setText(consultant.getC_nic());
        editTextCFirstName.setText(consultant.getC_first_name());
        editTextCLastName.setText(consultant.getC_last_name());
        spinnerCGender.setSelection(((ArrayAdapter<String>) spinnerCGender.getAdapter()).getPosition(consultant.getC_gender()));
        editTextCAddress.setText(consultant.getC_address());
        editTextCDOB.setText(consultant.getC_dob());
        editTextCPhoneNumber.setText(consultant.getC_phone_number());
        spinnerCRole.setSelection(((ArrayAdapter<String>) spinnerCRole.getAdapter()).getPosition(consultant.getC_role()));
        spinnerCStatus.setSelection(((ArrayAdapter<String>) spinnerCStatus.getAdapter()).getPosition(consultant.getC_status()));
        editTextHospital.setText(String.valueOf(consultant.getHospital_id()));
        editTextDepartment.setText(String.valueOf(consultant.getDepartment_id()));
        editTextUnit.setText(String.valueOf(consultant.getUnit_id()));
        editTextWard.setText(String.valueOf(consultant.getWard_id()));
        //editTextWard.setText(String.valueOf(1));

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.buttonUpdateConsultant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cNIC = editTextCNIC.getText().toString().trim();
                String cFirstName = editTextCFirstName.getText().toString().trim();
                String cLastName = editTextCLastName.getText().toString().trim();
                String cGender = spinnerCGender.getSelectedItem().toString();
                String cAddress = editTextCAddress.getText().toString().trim();
                String cDOB = editTextCDOB.getText().toString().trim();
                String cPhoneNumber = editTextCPhoneNumber.getText().toString().trim();
                String cRole = spinnerCRole.getSelectedItem().toString();
                String cStatus = spinnerCStatus.getSelectedItem().toString();

                int hospital = Integer.parseInt(editTextHospital.getText().toString());
                int department = Integer.parseInt(editTextDepartment.getText().toString());
                int unit = Integer.parseInt(editTextUnit.getText().toString());
                int ward = Integer.parseInt(editTextWard.getText().toString());

                if (cNIC.isEmpty()) {
                    editTextCNIC.setError("NIC can't be blank");
                    editTextCNIC.requestFocus();
                    return;
                }

                if (cFirstName.isEmpty()) {
                    editTextCFirstName.setError("First name can't be blank");
                    editTextCFirstName.requestFocus();
                    return;
                }

                if (cLastName.isEmpty()) {
                    editTextCLastName.setError("Last name can't be blank");
                    editTextCLastName.requestFocus();
                    return;
                }

                if (cAddress.isEmpty()) {
                    editTextCAddress.setError("Please enter Address");
                    editTextCAddress.requestFocus();
                    return;
                }

                if (cDOB.isEmpty()) {
                    editTextCDOB.setError("Please enter Date of Birth");
                    editTextCDOB.requestFocus();
                    return;
                }

                if (cPhoneNumber.isEmpty()) {
                    editTextCPhoneNumber.setError("Please enter Phone Number");
                    editTextCPhoneNumber.requestFocus();
                    return;
                }
                String sql = "UPDATE consultant \n" +
                        "SET c_nic = ?, \n" +
                        "c_first_name = ?, \n" +
                        "c_last_name = ?,\n" +
                        "c_gender = ?, \n" +
                        "c_address = ?, \n" +
                        "c_dob = ?, \n" +
                        "c_phone_number = ?, \n" +
                        "c_role = ?, \n" +
                        "c_status = ?, \n" +
                        "hospital_id = ?, \n" +
                        "department_id = ?, \n" +
                        "unit_id = ?, \n" +
                        "ward_id = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{cNIC, cFirstName, cLastName, cGender, cAddress, cDOB, cPhoneNumber, cRole, cStatus, String.valueOf(hospital), String.valueOf(department), String.valueOf(unit), String.valueOf(ward), String.valueOf(consultant.getId())});
                Toast.makeText(mCtx, "Consultant Updated", Toast.LENGTH_SHORT).show();
                reloadConsultantsFromDatabase();

                dialog.dismiss();
            }
        });
    }

    private void reloadConsultantsFromDatabase() {
        Cursor cursorConsultants = mDatabase.rawQuery("SELECT * FROM consultant", null);
        if (cursorConsultants.moveToFirst()) {
            consultantList.clear();
            do {
                consultantList.add(new Consultant(
                        cursorConsultants.getInt(0),
                        cursorConsultants.getString(1),
                        cursorConsultants.getString(2),
                        cursorConsultants.getString(3),
                        cursorConsultants.getString(4),
                        cursorConsultants.getString(5),
                        cursorConsultants.getString(6),
                        cursorConsultants.getString(7),
                        cursorConsultants.getString(8),
                        cursorConsultants.getString(9),
                        cursorConsultants.getInt(10),
                        cursorConsultants.getInt(11),
                        cursorConsultants.getInt(12),
                        cursorConsultants.getInt(13)
                ));
            } while (cursorConsultants.moveToNext());
        }
        cursorConsultants.close();
        notifyDataSetChanged();
    }

}

