package com.example.sismola.presentation.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.sismola.R;
import com.example.sismola.data.model.CurrentDataDevice;
import com.example.sismola.data.model.Device;
import com.example.sismola.data.model.InfoDevice;
import com.example.sismola.di.DaggerApplicationComponent;
import com.example.sismola.presentation.fragment.Control_Panel;
import com.example.sismola.presentation.fragment.G_Curah_Hujan;
import com.example.sismola.presentation.fragment.G_Intensitas_Cahaya;
import com.example.sismola.presentation.fragment.G_Kelembapan_Tanah;
import com.example.sismola.presentation.fragment.G_Kelembapan_Udara;
import com.example.sismola.presentation.fragment.G_PH_Tanah;
import com.example.sismola.presentation.fragment.G_Suhu_Tanah;
import com.example.sismola.presentation.fragment.G_Suhu_Udara;
import com.example.sismola.presentation.viewmodel.DataViewModel;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Inject
    public DataViewModel dataViewModel;
    private int itemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_control_panel);

        initInject();
        observeAndLoadData();
        autoRefreshData();
                
        ////////
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Control_Panel fragment = new Control_Panel();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    
    private void observeAndLoadData() {
        dataViewModel.getDevices();
        dataViewModel.getDevicesLiveData().observe(this, new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                updateUI(devices);
            }
        });
    }

    private void autoRefreshData() {
        int updateDataEvery = 30*1000;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                dataViewModel.getDevices();
            }},updateDataEvery,updateDataEvery);
    }

    private void updateUI(List<Device> devices) {
        if (itemPosition == -1) itemPosition = 0;
        populateListDeviceSpinner(devices);
        populateLastUpdate(devices);
        populateAllData(devices);
    }

    private void populateAllData(List<Device> devices) {
        CurrentDataDevice currentDataDevice = devices.get(itemPosition).getCurrentDataDevice();
        if (currentDataDevice != null) {
            TextView textView = findViewById(R.id.fcp_itc);
            textView.setText(String.valueOf(currentDataDevice.getLightIntensity()));

            textView = findViewById(R.id.fcp_ch);
            textView.setText(String.valueOf(currentDataDevice.getRainfall()));

            textView = findViewById(R.id.fcp_ph);
            textView.setText(String.valueOf(currentDataDevice.getPh()));

            textView = findViewById(R.id.fcp_kt);
            textView.setText(String.valueOf(currentDataDevice.getSoilMoisture()));

            textView = findViewById(R.id.fcp_st);
            textView.setText(String.valueOf(currentDataDevice.getSoilTemp()));

            textView = findViewById(R.id.fcp_ku);
            textView.setText(String.valueOf(currentDataDevice.getHumidity()));

            textView = findViewById(R.id.fcp_su);
            textView.setText(String.valueOf(currentDataDevice.getAirTemp()));
        }

    }

    private void populateLastUpdate(List devices) {
        CurrentDataDevice currentDataDevice = ((Device)devices.get(itemPosition)).getCurrentDataDevice();
        String lastUpdate = currentDataDevice != null ? currentDataDevice.getLastUpdate() : null;
        TextView tv_last_update = findViewById(R.id.fcp_tv_last_update);
        tv_last_update.setText(getString(R.string.last_update_xyz, lastUpdate));
    }

    private void populateListDeviceSpinner(final List<Device> devices) {
        ArrayList<String> devicesId = getId(devices);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, devicesId);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinner = findViewById(R.id.fcp_spinner_devices);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(itemPosition);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                itemPosition = position;
                populateLastUpdate(devices);
                populateAllData(devices);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private ArrayList<String> getId(List<Device> devices) {
        ArrayList<String> ids = new ArrayList<>();
        for (Device device : devices) {
            InfoDevice infoDevice = device.getInfoDevice();
            String deviceId = infoDevice.getDeviceId();
            ids.add(deviceId);
        }
        return ids;
    }

    private void initInject() {
        DaggerApplicationComponent.builder().build().inject(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Control_Panel fragment = new Control_Panel();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {
            G_Intensitas_Cahaya fragment = new G_Intensitas_Cahaya();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            G_Curah_Hujan fragment = new G_Curah_Hujan();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_tools) {
            G_PH_Tanah fragment = new G_PH_Tanah();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {
            G_Kelembapan_Tanah fragment = new G_Kelembapan_Tanah();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_send) {
            G_Suhu_Tanah fragment = new G_Suhu_Tanah();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.parent) {
            G_Kelembapan_Udara fragment = new G_Kelembapan_Udara();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.percent) {
            G_Suhu_Udara fragment = new G_Suhu_Udara();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
