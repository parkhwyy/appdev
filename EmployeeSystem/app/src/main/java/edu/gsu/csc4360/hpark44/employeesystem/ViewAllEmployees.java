package edu.gsu.csc4360.hpark44.employeesystem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuItem;

import edu.gsu.csc4360.hpark44.employeesystem.Database.EmployeeOperations;
import edu.gsu.csc4360.hpark44.employeesystem.Model.Employee;

import java.util.List;

public class ViewAllEmployees extends ListActivity
{
    private static final String EXTRA_ADD_UPDATE = "edu.gsu.csc4360.hpark44.add_update";

    private EmployeeOperations employeeOps;
    List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        employeeOps = new EmployeeOperations(this);
        employeeOps.open();

        employees = employeeOps.getAllEmployees();
        employeeOps.close();

        ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this,R.layout.sample_list_item,employees);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_add:
                actionAdd();
                break;

            case R.id.action_view:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionAdd()
    {
        Intent i = new Intent(ViewAllEmployees.this, AddUpdateEmployee.class);
        i.putExtra(EXTRA_ADD_UPDATE, "Add");
        startActivity(i);
    }

}
