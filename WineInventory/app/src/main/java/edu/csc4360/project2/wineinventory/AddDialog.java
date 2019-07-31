package edu.csc4360.project2.wineinventory;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

import static android.app.Activity.RESULT_OK;

public class AddDialog extends AppCompatDialogFragment {
    private EditText nameText;
    private EditText brandText;
    private EditText typeText;
    private EditText yearText;
    private EditText quantityText;
    private EditText priceText;
    private WineDatabaseHandler db;

    private ImageView uploadImage;
    final int REQUEST_CODE_GALLERY = 999;

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

        uploadImage = view.findViewById(R.id.imageView);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
