package com.mia.outsiderhelper.main.fragment.store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.store.restaurant.RestaurantActivity;

public class StoreFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);

        LinearLayout storeEating = view.findViewById(R.id.store_linear_eating);
        LinearLayout storeCafe = view.findViewById(R.id.store_linear_cafe);
        LinearLayout storeConvenience = view.findViewById(R.id.store_linear_convenience);
        LinearLayout storeEntertain = view.findViewById(R.id.store_linear_entertain);

        storeEating.setOnClickListener(this);
        storeCafe.setOnClickListener(this);
        storeConvenience.setOnClickListener(this);
        storeEntertain.setOnClickListener(this);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_linear_eating:
                Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                startActivity(intent);
                break;
            case R.id.store_linear_cafe:
                break;
            case R.id.store_linear_convenience:
                break;
            case R.id.store_linear_entertain:
                break;
        }
    }
}
