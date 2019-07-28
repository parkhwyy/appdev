package edu.gsu.csc4360.hpark44.mvccalculator;

import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.TextView;

import edu.gsu.csc4360.hpark44.mvccalculator.model.CalculatorModel;
import edu.gsu.csc4360.hpark44.mvccalculator.view.CalculatorInputView;
import edu.gsu.csc4360.hpark44.mvccalculator.view.CalculatorOutputView;
import edu.gsu.csc4360.hpark44.mvccalculator.view.CalculatorInputView.GetInput;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GetInput {

    private static final String LOG_TAG = "Calculator";

    private CalculatorInputView CInputView;
    private CalculatorOutputView COutputView;
    private CalculatorModel CModel;

    private String number = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CInputView = new CalculatorInputView(this,this);
        COutputView = new CalculatorOutputView(this);
        CModel = new CalculatorModel();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void operandIn(String operand) {
        Log.d(LOG_TAG, operand);
        number = number.equals("0") ? operand : number + operand;
        COutputView.outputData(number);
    }

    @Override
    public void operatorIn(String operator) {
        Log.d(LOG_TAG, operator);
        if(operator.equalsIgnoreCase("AC")) {
            CModel.reset();
            number = "0";
            COutputView.outputData(number);
            return;
        }
        if(!number.equals("0"))
            CModel.pushOperand(number);
        double tmpResult = CModel.pushOperator(operator);
        if(tmpResult % 1d == 0d) {
            int tmp = Double.valueOf(tmpResult).intValue();
            COutputView.outputData(String.valueOf(tmp));
        } else {
            COutputView.outputData(String.valueOf(tmpResult));
        }
        number = "0";
    }
}