package com.example.ecommerceapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView productNameTextView = findViewById(R.id.product_name_detail);

        String productName = getIntent().getStringExtra("product_name");
        if (productName != null) {
            productNameTextView.setText(productName);
        }
    }
}
