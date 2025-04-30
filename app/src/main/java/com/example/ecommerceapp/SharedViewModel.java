package com.example.ecommerceapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> userName = new MutableLiveData<>("User Name");
    private final MutableLiveData<String> deliveryAddress = new MutableLiveData<>("Delivery Address");

    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<String> getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setUserName(String name) {
        userName.setValue(name);
    }

    public void setDeliveryAddress(String address) {
        deliveryAddress.setValue(address);
    }
}
