package com.example.sismola.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.example.sismola.R
import com.example.sismola.data.model.Device
import com.example.sismola.di.DaggerApplicationComponent
import com.example.sismola.presentation.fragment.*
import com.example.sismola.presentation.viewmodel.DataViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_control_panel.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var dataViewModel: DataViewModel

    private val updateDataEvery = 60 //second
    private var itemPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_control_panel)

        initInject()
        observeAndLoadData()
        autoRefreshData()

        ////////
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fragment = Control_Panel()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
        fragmentTransaction.commit()

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun autoRefreshData() {
        GlobalScope.launch {
            delay((updateDataEvery*1000).toLong())
            dataViewModel.getDevices()
            autoRefreshData()
        }
    }

    private fun observeAndLoadData() {
        dataViewModel.getDevices()
        dataViewModel.devices.observe(this, Observer {
            updateUI(it)
        })
    }

    private fun updateUI(devices: List<Device>) {
        if(itemPosition==-1) itemPosition=0
        populateListDeviceSpinner(devices)
        populateLastUpdate(devices, itemPosition)
        populateAllData(devices, itemPosition)
    }

    private fun populateAllData(devices: List<Device>, position: Int) {
        val currentDataDevice = devices[position].currentDataDevice
        if (currentDataDevice != null) {
            itc.text = currentDataDevice.lightIntensity.toString()
            ch.text = currentDataDevice.rainfall.toString()
            ph.text = currentDataDevice.ph.toString()
            kt.text = currentDataDevice.soilMoisture.toString()
            st.text = currentDataDevice.soilTemp.toString()
            ku.text = currentDataDevice.humidity.toString()
            su.text = currentDataDevice.airTemp.toString()
        }

    }

    private fun populateLastUpdate(devices: List<Device>, position: Int) {
        val lastUpdate = devices[position].currentDataDevice?.lastUpdate
        tv_last_update.text = getString(R.string.last_update_xyz,lastUpdate)
    }

    private fun populateListDeviceSpinner(devices: List<Device>) {
        val devicesId = getDeviceId(devices)
        val spinnerAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, devicesId)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_devices.apply{
            adapter = spinnerAdapter
            setSelection(itemPosition)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    itemPosition = position
                    populateLastUpdate(devices, itemPosition)
                    populateAllData(devices, itemPosition)
                }
            }
        }
    }

    private fun getDeviceId(devices: List<Device>): ArrayList<String> {
        val devicesId = arrayListOf<String>()
        for(device in devices){
            device.infoDevice?.deviceId?.let { devicesId.add(it) }
        }
        return devicesId
    }

    private fun initInject() {
        DaggerApplicationComponent.builder()
                .build()
                .inject(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val fragment = Control_Panel()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.nav_gallery -> {
                val fragment = G_Intensitas_Cahaya()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.nav_slideshow -> {
                val fragment = G_Curah_Hujan()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.nav_tools -> {
                val fragment = G_PH_Tanah()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.nav_share -> {
                val fragment = G_Kelembapan_Tanah()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.nav_send -> {
                val fragment = G_Suhu_Tanah()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.parent -> {
                val fragment = G_Kelembapan_Udara()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
            R.id.percent -> {
                val fragment = G_Suhu_Udara()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.FrameLaoyut, fragment)
                fragmentTransaction.commit()

            }
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
