package com.example.sismola.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sismola.data.DataRepository
import com.example.sismola.data.model.Device
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author by M on 10/11/19
 */
class DataViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {
    val devices = MutableLiveData<List<Device>>()

    fun getDevices(){
        GlobalScope.launch {
            val retrievedResponse = dataRepository.getData()
            devices.postValue(retrievedResponse.response.devices)
        }
    }
}