package com.example.ecommerceapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ProfileFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private EditText userNameEditText;
    private EditText deliveryAddressEditText;
    private Button saveChangesButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userNameEditText = view.findViewById(R.id.edit_user_name);
        deliveryAddressEditText = view.findViewById(R.id.edit_delivery_address);
        saveChangesButton = view.findViewById(R.id.save_changes_button);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getUserName().observe(getViewLifecycleOwner(), name -> userNameEditText.setText(name));
        sharedViewModel.getDeliveryAddress().observe(getViewLifecycleOwner(), address -> deliveryAddressEditText.setText(address));

        saveChangesButton.setOnClickListener(v -> {
            String newName = userNameEditText.getText().toString().trim();
            String newAddress = deliveryAddressEditText.getText().toString().trim();

            if (TextUtils.isEmpty(newName)) {
                Toast.makeText(getContext(), "User name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            sharedViewModel.setUserName(newName);
            sharedViewModel.setDeliveryAddress(newAddress);
            Toast.makeText(getContext(), "Changes saved", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
