package com.example.sqldatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editRollno,editName,editMarks;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShow;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editRollno=findViewById(R.id.editRollno);
        editName=findViewById(R.id.editName);
        editMarks=findViewById(R.id.editMarks);

        btnAdd=findViewById(R.id.buttonAdd);
        btnDelete=findViewById(R.id.buttonDelete);
        btnModify=findViewById(R.id.buttonModify);
        btnView=findViewById(R.id.buttonView);
        btnViewAll=findViewById(R.id.buttonViewAll);
        btnShow=findViewById(R.id.buttonShow);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShow.setOnClickListener(this);

        db=openOrCreateDatabase("StudentDB",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR ,marks VARCHAR)");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonAdd:
                if (editRollno.getText().toString().trim().length() == 0 || editName.getText().toString().trim().length() == 0 || editMarks.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    shwmsg("Error", "Invalid Input");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('" + editRollno.getText() + "','" + editName.getText() +
                        "','" + editMarks.getText() + "');");
                shwmsg("Success", "Record added");
                clearText();


                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonDelete:
                if (editRollno.getText().toString().trim().length() == 0) {
                    shwmsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM student WHERE rollno='" + editRollno.getText() + "'");
                    shwmsg("Success", "Record Deleted");
                } else {
                    shwmsg("Error", "Invalid Rollno");
                }
                clearText();

                break;

            case R.id.buttonModify:
                if (editRollno.getText().toString().trim().length() == 0) {
                    shwmsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c1 = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c1.moveToFirst()) {
                    db.execSQL("UPDATE student SET name='" + editName.getText() + "',marks='" + editMarks.getText() +
                            "' WHERE rollno='" + editRollno.getText() + "'");
                    shwmsg("Success", "Record Modified");
                } else {
                    shwmsg("Error", "Invalid Rollno");
                }
                clearText();


                break;

            case R.id.buttonView:

                if (editRollno.getText().toString().trim().length() == 0) {
                    shwmsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c2 = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c2.moveToFirst()) {
                    editName.setText(c2.getString(1));
                    editMarks.setText(c2.getString(2));
                } else {
                    shwmsg("Error", "Invalid Rollno");
                    clearText();
                }
                break;

            case R.id.buttonViewAll:

                Cursor c3 = db.rawQuery("SELECT * FROM student", null);
                if (c3.getCount() == 0) {
                    shwmsg("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();//dynamic approach
                while (c3.moveToNext()) {
                    buffer.append("Rollno: " + c3.getString(0) + "\n");
                    buffer.append("Name: " + c3.getString(1) + "\n");
                    buffer.append("Marks: " + c3.getString(2) + "\n\n");
                }
                shwmsg("Student Details", buffer.toString());
                break;
            case R.id.buttonShow:
                shwmsg("Student Record Application", "Developed By Ashwini");
        }


    }






    private void clearText() {

        editMarks.setText("");
        editName.setText("");
        editRollno.setText("");
    }

    private void shwmsg(String title, String msg) {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setIcon(R.mipmap.ic_launcher_round);
        alertDialog.show();
    }
}
