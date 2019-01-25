package com.epiclancers.intrototheroomlibrary;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.epiclancers.intrototheroomlibrary.database.Mobile;

public class EditMobile extends AppCompatActivity
    implements Spinner.OnItemSelectedListener{

    MobileViewModels viewModel;
    EditText editTextMobileName,editTextMobileImage,editTextMobilePrice;
    Spinner spinnerRam,spinnerProcessor;
    ImageView mobileImage;
    Button editMobile;
    private Mobile mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mobile);
        viewModel = ViewModelProviders.of(this).get(MobileViewModels.class);
        mobile = getIntent().getExtras().getParcelable("mobile");
        init();
        setUpDummyData();
    }

    private void setUpDummyData() {
        editTextMobileName.setText(mobile.getMobileName());
        editTextMobileImage.setText(mobile.getMobileImageUrl());
        editTextMobilePrice.setText(mobile.getMobilePrice());
        spinnerRam.setSelection(viewModel.adapter2.getPosition(mobile.getMobileRam()));
        spinnerProcessor.setSelection(viewModel.adapter1.getPosition(mobile.getMobileProcessor()));
    }

    private void init() {
        editTextMobileName = findViewById(R.id.editTextMobileName);
        editTextMobileImage = findViewById(R.id.editTextMobileImage);
        editTextMobilePrice = findViewById(R.id.editTextMobilePrice);
        spinnerRam = findViewById(R.id.spinnerRam);
        spinnerProcessor = findViewById(R.id.spinnerProcessor);
        editMobile = findViewById(R.id.editMobile);


        // Setting Processor Spinner
        spinnerProcessor.setOnItemSelectedListener(this);
        viewModel.adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProcessor.setAdapter(viewModel.adapter1);

        // Setting Processor Spinner
        spinnerRam.setOnItemSelectedListener(this);
        viewModel.adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRam.setAdapter(viewModel.adapter2);

        mobileImage = findViewById(R.id.mobImg);
        Glide.with(this).load(mobile.getMobileImageUrl()).into(mobileImage);

    }

    public void editMobile(View view) {
        viewModel.mobileName = editTextMobileName.getText().toString();
        viewModel.mobilePrice = editTextMobilePrice.getText().toString();
        viewModel.mobileUrl = editTextMobileImage.getText().toString();
        if (viewModel.isBasicEmpty()) return;
        viewModel.updateMobile(mobile.getMobileId());
        finish();
    }

    public void deleteMobile(View view){
        viewModel.deleteMobile(mobile.getMobileId());
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idOfSpinner = parent.getId();
        if (idOfSpinner == R.id.spinnerProcessor) {
            if (position != 0) viewModel.mobileProcessor = viewModel.mobileProcs[position];
        } else if (idOfSpinner == R.id.spinnerRam) {
            if (position != 0) viewModel.mobileRam = viewModel.mobileRams[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
