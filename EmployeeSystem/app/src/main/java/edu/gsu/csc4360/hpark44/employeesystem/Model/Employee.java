package edu.gsu.csc4360.hpark44.employeesystem.Model;

public class Employee
{
    public long employeeID;

    private String firstname;
    private String lastname;
    private String email;
    private String job;
    private String salary;

    public Employee(long employeeID, String firstname, String lastname, String email, String job, String salary)
    {
        this.employeeID = employeeID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.job = job;
        this.salary = salary;
    }

    public Employee()
    {

    }

    public long getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(long employeeID)
    {
        this.employeeID = employeeID;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String toString()
    {
        return  "Employee ID: "+getEmployeeID()+ "\n" +
                "Name: "+getFirstname() + " " + getLastname() + "\n" +
                "Email: "+getEmail() + "\n" +
                "Job Title: " +getJob() + "\n" +
                "Salary: " +getSalary();
    }

}
