package edu.gsu.csc4360.hpark44.employeesystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gsu.csc4360.hpark44.employeesystem.Database.EmployeeOperations;
import edu.gsu.csc4360.hpark44.employeesystem.Database.EmployeeDatabaseHandler;

public class MainActivity extends AppCompatActivity
{
    private Button viewAllEmployeesButton;
    private Button addEmployeeButton;
    private Button editEmployeeButton;
    private Button deleteEmployeeButton;

    private EmployeeOperations employeeOps;

    private static final String EXTRA_EMPLOYEE_ID = "edu.gsu.csc4360.hpark44.EId";
    private static final String EXTRA_ADD_UPDATE = "edu.gsu.csc4360.hpark44.add_update";

    private static final String TAG = "Employee Exits";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewAllEmployeesButton = (Button)findViewById(R.id.button_viewAll);
        addEmployeeButton = (Button)findViewById(R.id.button_employee_add);
        editEmployeeButton = (Button)findViewById(R.id.button_employee_update);
        deleteEmployeeButton = (Button)findViewById(R.id.button_employee_delete);

        addEmployeeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, AddUpdateEmployee.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });

        editEmployeeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getEmployeeIDAndUpdateEmployee();
            }
        });

        deleteEmployeeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getEmployeeIDAndRemoveEmployee();
            }
        });

        viewAllEmployeesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, ViewAllEmployees.class);
                startActivity(i);
            }
        });
    }

    public boolean check_existence(String emp_id)
    {
        SQLiteOpenHelper db = new EmployeeDatabaseHandler(this);
        SQLiteDatabase database = db.getWritableDatabase();

        String select = "SELECT * FROM employees WHERE employeeID =" + emp_id;

        Cursor c = database.rawQuery(select , null);

        if (c.moveToFirst())
        {
            Log.d(TAG, "Employee Exists");
            return true;
        }

        if (c!=null)
        {
            c.close();
        }

        database.close();
        return false;
    }

    public void getEmployeeIDAndUpdateEmployee()
    {
        LayoutInflater li = LayoutInflater.from(this);
        final View getEmployeeIdView = li.inflate(R.layout.dialog_getid,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(getEmployeeIdView);

        final EditText userInput = (EditText)getEmployeeIdView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if (userInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Invalid Input", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // get user input and set it to result
                    // edit text
                    if(check_existence(userInput.getText().toString()) == true)
                    {
                        Intent i = new Intent(MainActivity.this,AddUpdateEmployee.class);
                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
                        i.putExtra(EXTRA_EMPLOYEE_ID,Long.parseLong(userInput.getText().toString()));
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).create().show();
    }

    public void getEmployeeIDAndRemoveEmployee()
    {
        LayoutInflater li = LayoutInflater.from(this);
        View getEmployeeIdView = li.inflate(R.layout.dialog_getid,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(getEmployeeIdView);

        final EditText userInput = (EditText)getEmployeeIdView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if (userInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(check_existence(userInput.getText().toString()) == true)
                    {
                        employeeOps.removeEmployee(employeeOps.getEmployee(Long.parseLong(userInput.getText().toString())));
                        Toast.makeText(MainActivity.this, "Employee has been removed successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Input",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }).create().show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        employeeOps = new EmployeeOperations(MainActivity.this);
        employeeOps.open();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        employeeOps.close();
    }

}
