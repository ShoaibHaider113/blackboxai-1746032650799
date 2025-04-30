package com.example.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private TextView userNameTextView;
    private TextView deliveryAddressTextView;
    private Button goToProfileButton;
    private RecyclerView productsRecyclerView;

    private List<String> products = Arrays.asList("Shoes", "T-Shirts", "Laptops");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        userNameTextView = view.findViewById(R.id.user_name);
        deliveryAddressTextView = view.findViewById(R.id.delivery_address);
        goToProfileButton = view.findViewById(R.id.go_to_profile_button);
        productsRecyclerView = view.findViewById(R.id.products_recycler_view);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getUserName().observe(getViewLifecycleOwner(), name -> userNameTextView.setText(name));
        sharedViewModel.getDeliveryAddress().observe(getViewLifecycleOwner(), address -> deliveryAddressTextView.setText(address));

        goToProfileButton.setOnClickListener(v -> {
            // Switch to ProfileFragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .addToBackStack(null)
                    .commit();
        });

        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productsRecyclerView.setAdapter(new ProductsAdapter(products, product -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("product_name", product);
            startActivity(intent);
        }));

        return view;
    }
}
