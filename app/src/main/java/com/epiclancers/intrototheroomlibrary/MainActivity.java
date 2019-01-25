package com.epiclancers.intrototheroomlibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.epiclancers.intrototheroomlibrary.database.Mobile;
import com.epiclancers.intrototheroomlibrary.ui.adapters.MobileAdapters;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements Spinner.OnItemSelectedListener {

    Spinner ramSpinner, processorSpinner;
    EditText editTextMobileImage, editTextMobileName, editTextMobilePrice;
    MobileViewModels viewModel;
    RecyclerView recyclerView;
    private MobileAdapters adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MobileViewModels.class);
        init();
        viewModel.listLiveData.observe(this, new Observer<List<Mobile>>() {
            @Override
            public void onChanged(@Nullable List<Mobile> mobiles) {
                adapters.setMobileList(mobiles);
            }
        });
    }

    private void init() {
        processorSpinner = findViewById(R.id.spinnerProcessor);
        ramSpinner = findViewById(R.id.spinnerRam);

        // Setting Processor Spinner
        processorSpinner.setOnItemSelectedListener(this);
        viewModel.adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        processorSpinner.setAdapter(viewModel.adapter1);

        // Setting Processor Spinner
        ramSpinner.setOnItemSelectedListener(this);
        viewModel.adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ramSpinner.setAdapter(viewModel.adapter2);

        editTextMobileImage = findViewById(R.id.editTextMobileImage);
        editTextMobileName = findViewById(R.id.editTextMobileName);
        editTextMobilePrice = findViewById(R.id.editTextMobilePrice);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        List<Mobile> list = new ArrayList<>();
        adapters = new MobileAdapters(list, this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapters);
    }

    public void addMobile(View view) {
        viewModel.mobileName = editTextMobileName.getText().toString();
        viewModel.mobilePrice = editTextMobilePrice.getText().toString();
        viewModel.mobileUrl = editTextMobileImage.getText().toString();
        if (viewModel.isBasicEmpty()) return;
        viewModel.addMobile();
        clearFields();
    }

    private void clearFields() {
        editTextMobileName.setText("");
        editTextMobilePrice.setText("");
        editTextMobileImage.setText("");
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
