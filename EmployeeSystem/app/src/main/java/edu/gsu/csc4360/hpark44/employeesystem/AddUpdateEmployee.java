package edu.gsu.csc4360.hpark44.employeesystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import edu.gsu.csc4360.hpark44.employeesystem.Model.Employee;
import edu.gsu.csc4360.hpark44.employeesystem.Database.EmployeeOperations;

public class AddUpdateEmployee extends AppCompatActivity
{
    private static final String EXTRA_EMPLOYEE_ID = "edu.gsu.csc4360.hpark44.EId";
    private static final String EXTRA_ADD_UPDATE = "edu.gsu.csc4360.hpark44.add_update";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText jobTitleEditText;
    private EditText salaryEditText;

    private Button addUpdateButton;

    private ImageView uploadImage;
    final int REQUEST_CODE_GALLERY = 999;

    private Employee newEmployee;
    private Employee oldEmployee;

    private String mode;

    private long employeeID;

    private EmployeeOperations employeeData;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);

        newEmployee = new Employee();
        oldEmployee = new Employee();

        firstNameEditText = (EditText) findViewById(R.id.edit_text_first_name);
        lastNameEditText = (EditText) findViewById(R.id.edit_text_last_name);
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        jobTitleEditText = (EditText) findViewById(R.id.edit_text_jobtitle);
        salaryEditText = (EditText) findViewById(R.id.edit_text_salary);

        addUpdateButton = (Button) findViewById(R.id.button_add_update_employee);

        uploadImage = findViewById(R.id.imageView);

        employeeData = new EmployeeOperations(this);
        employeeData.open();

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        if (mode.equals("Update")) {
            addUpdateButton.setText("Update Employee");
            employeeID = getIntent().getLongExtra(EXTRA_EMPLOYEE_ID, 0);

            initializeEmployee(employeeID);
        }

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddUpdateEmployee.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode.equals("Add")) {
                    newEmployee.setFirstname(firstNameEditText.getText().toString());
                    newEmployee.setLastname(lastNameEditText.getText().toString());
                    newEmployee.setEmail(emailEditText.getText().toString());
                    newEmployee.setJob(jobTitleEditText.getText().toString());
                    newEmployee.setSalary(salaryEditText.getText().toString());

                    if (firstNameEditText.getText().toString().isEmpty() || lastNameEditText.getText().toString().isEmpty() || emailEditText.getText().toString().isEmpty() || jobTitleEditText.getText().toString().isEmpty() || salaryEditText.getText().toString().isEmpty()) {
                        Toast.makeText(AddUpdateEmployee.this, "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                    } else {
                        employeeData.addEmployee(newEmployee);
                        Toast.makeText(AddUpdateEmployee.this, "Employee " + newEmployee.getFirstname() + " has been added successfully.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddUpdateEmployee.this, MainActivity.class);
                        startActivity(i);
                    }
                } else {
                    oldEmployee.setFirstname(firstNameEditText.getText().toString());
                    oldEmployee.setLastname(lastNameEditText.getText().toString());
                    oldEmployee.setEmail(emailEditText.getText().toString());
                    oldEmployee.setJob(jobTitleEditText.getText().toString());
                    oldEmployee.setSalary(salaryEditText.getText().toString());

                    if (firstNameEditText.getText().toString().isEmpty() || lastNameEditText.getText().toString().isEmpty() || emailEditText.getText().toString().isEmpty() || jobTitleEditText.getText().toString().isEmpty() || salaryEditText.getText().toString().isEmpty()) {
                        Toast.makeText(AddUpdateEmployee.this, "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                    } else {
                        employeeData.updateEmployee(oldEmployee);
                        Toast.makeText(AddUpdateEmployee.this, "Employee " + oldEmployee.getFirstname() + " has been updated successfully.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddUpdateEmployee.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //enable image guidlines
                    .setAspectRatio(1,1)// image will be square
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result =CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                //set image choosed from gallery to image view
                uploadImage.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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
                break;

            case R.id.action_view:
                actionView();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void actionView()
    {
        Intent i = new Intent(AddUpdateEmployee.this, ViewAllEmployees.class);
        startActivity(i);
    }


    private void initializeEmployee(long employeeID)
    {
        oldEmployee = employeeData.getEmployee(employeeID);

        firstNameEditText.setText(oldEmployee.getFirstname());
        lastNameEditText.setText(oldEmployee.getLastname());
        emailEditText.setText(oldEmployee.getEmail());
        jobTitleEditText.setText(oldEmployee.getJob());
        salaryEditText.setText(oldEmployee.getSalary());
    }
}
