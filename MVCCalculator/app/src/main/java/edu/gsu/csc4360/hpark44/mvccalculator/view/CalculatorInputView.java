package edu.gsu.csc4360.hpark44.mvccalculator.view;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gsu.csc4360.hpark44.mvccalculator.MainActivity;
import edu.gsu.csc4360.hpark44.mvccalculator.R;
import java.util.ArrayList;

public class CalculatorInputView {

    private ArrayList<Button> buttonOperands;
    private ArrayList<Button> buttonOperators;

    private CalculatorInputView.GetInput Assign;

    public CalculatorInputView(MainActivity activity, GetInput assign) {
        Assign = assign;
        buttonOperands = new ArrayList<Button>();
        buttonOperators = new ArrayList<Button>();
        setUIComponent(activity);
    }



    private void setUIComponent(Activity activity) {
        Resources tmpRes = activity.getResources();
        for(int i = 0; i < 10; i++) {
            int tmpOperandId = tmpRes.getIdentifier("button" + i, "id", activity.getPackageName());
            Button tmpButtonOperand = (Button) activity.findViewById(tmpOperandId);
            buttonOperands.add(tmpButtonOperand);
        }

        buttonOperators.add((Button) activity.findViewById(R.id.buttonAC));
        buttonOperators.add((Button) activity.findViewById(R.id.buttonDiv));
        buttonOperators.add((Button) activity.findViewById(R.id.buttonMul));
        buttonOperators.add((Button) activity.findViewById(R.id.buttonSub));
        buttonOperators.add((Button) activity.findViewById(R.id.buttonAdd));
        buttonOperators.add((Button) activity.findViewById(R.id.buttonAns));

        for(Button btn : buttonOperands) {
            btn.setOnClickListener(onClickOperand);
        }

        for(Button btn : buttonOperators) {
            btn.setOnClickListener(onClickOperate);
        }
    }

    private Button.OnClickListener onClickOperand = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button tmpClickButton = (Button) v;
            String tmpText = tmpClickButton.getText().toString();
            Assign.operandIn(tmpText);
        }
    };

    private Button.OnClickListener onClickOperate = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button tmpClickButton = (Button) v;
            String tmpText = tmpClickButton.getText().toString();
            Assign.operatorIn(tmpText);
        }
    };

    public interface GetInput {

        public void operandIn(String operand);

        public void operatorIn(String operator);

    }

}