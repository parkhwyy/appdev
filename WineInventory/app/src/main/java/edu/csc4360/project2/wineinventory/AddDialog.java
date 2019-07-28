package edu.csc4360.project2.wineinventory;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

public class AddDialog extends AppCompatDialogFragment {
    private EditText nameText;
    private EditText brandText;
    private EditText typeText;
    private EditText yearText;
    private EditText quantityText;
    private EditText priceText;
    private WineDatabaseHandler db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_dialog_fragment, null);

        db = new WineDatabaseHandler(getActivity());

        nameText = view.findViewById(R.id.nameText);
        brandText = view.findViewById(R.id.typeText);
        typeText = view.findViewById(R.id.brandText);
        yearText = view.findViewById(R.id.yearText);
        quantityText = view.findViewById(R.id.quantityText);
        priceText = view.findViewById(R.id.priceText);

        builder.setView(view)
                .setTitle("Add Item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AddData();
                        Toast.makeText(getActivity(), "Item Successfully Added", Toast.LENGTH_SHORT)
                                .show();

                        dismiss();

//                        listener.applyTexts(name1, brand1, type1, year1, quantity1, price1);
                    }
                });





        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//
//        try {
//            listener = (ExampleDialogListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() +
//                    "must implement ExampleDialogListener");
//        }
    }

    public void AddData() {
        db.insertData(nameText.getText().toString(),
                brandText.getText().toString(),
                typeText.getText().toString(),
                yearText.getText().toString(),
                priceText.getText().toString(),
                quantityText.getText().toString());
    }
}
