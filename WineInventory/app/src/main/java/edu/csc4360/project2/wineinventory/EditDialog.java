package edu.csc4360.project2.wineinventory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

public class EditDialog extends AppCompatDialogFragment {
    private EditText idText;
    private EditText nameText;
    private EditText brandText;
    private EditText typeText;
    private EditText yearText;
    private EditText quantityText;
    private EditText priceText;
    //private ExampleDialogListener listener;
    private WineDatabaseHandler db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment, null);

        db = new WineDatabaseHandler(getActivity());

        idText = getActivity().findViewById(R.id.idText);
        nameText = view.findViewById(R.id.nameText);
        brandText = view.findViewById(R.id.typeText);
        typeText = view.findViewById(R.id.brandText);
        yearText = view.findViewById(R.id.yearText);
        quantityText = view.findViewById(R.id.quantityText);
        priceText = view.findViewById(R.id.priceText);

        search();

        builder.setView(view)
                .setTitle("Edit/Delete Item")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData();
                        Toast.makeText(getActivity(), "Item Successfully Deleted", Toast.LENGTH_SHORT)
                            .show();

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        getContext().startActivity(intent);

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateData();
                        Toast.makeText(getActivity(), "Item Successfully Updated", Toast.LENGTH_SHORT)
                            .show();

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        getContext().startActivity(intent);

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

    private void deleteData(){
        db.deleteData(idText.getText().toString());
    }

    private void updateData(){
        db.updateData(idText.getText().toString(),
                nameText.getText().toString(),
                brandText.getText().toString(),
                typeText.getText().toString(),
                yearText.getText().toString(),
                priceText.getText().toString(),
                quantityText.getText().toString());
    }

    private void search(){
        Cursor result = db.searchById(idText.getText().toString());
        if (result.getCount() == 0) {
            Toast.makeText(getActivity(), "ERROR: No Such ID Exists", Toast.LENGTH_SHORT)
                    .show();
            dismiss();
        }
        else {
            result.moveToFirst();
            nameText.setText(result.getString(1));
            typeText.setText(result.getString(2));
            brandText.setText(result.getString(3));
            yearText.setText(result.getString(4));
            priceText.setText(result.getString(5));
        }
    }

//    public interface ExampleDialogListener {
//        void applyTexts(String name1, String brand1, String type1, String year1, String quantity1, String price1);
//    }
}