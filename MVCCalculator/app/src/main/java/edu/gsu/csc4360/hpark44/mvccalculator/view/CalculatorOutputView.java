package edu.gsu.csc4360.hpark44.mvccalculator.view;

import android.app.Activity;
import android.widget.TextView;

import edu.gsu.csc4360.hpark44.mvccalculator.MainActivity;
import edu.gsu.csc4360.hpark44.mvccalculator.R;

public class CalculatorOutputView {

    private TextView OutText;

    public CalculatorOutputView(MainActivity activity) {
        OutText = (TextView) activity.findViewById(R.id.outText);
    }

    public void outputData(String str) {
        OutText.setText(str);
    }

}