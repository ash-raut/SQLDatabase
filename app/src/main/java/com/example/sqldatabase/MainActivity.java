package com.example.sqldatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

        switch(v.getId())
        {

            case R.id.buttonAdd:
                if(editRollno.getText().toString().trim().length()==0||editName.getText().toString().trim().length()==0||editMarks.getText().toString().trim().length()==0)
                {
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    shwmsg("Error","Invalid Input");
                    return;
                }
                clearText();
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonDelete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonModify:
                Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonView:
                Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonViewAll:
                Toast.makeText(this, "ViewAll", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonShow:
                Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show();
                break;
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
