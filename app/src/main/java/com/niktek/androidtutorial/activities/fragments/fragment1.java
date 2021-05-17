package com.niktek.androidtutorial.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.niktek.androidtutorial.R;

public class fragment1 extends Fragment {

    public interface phoneCall{
        void setPhone();
    }

    private String Number;
    View view;
    EditText textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        textView = (EditText) view.findViewById(R.id.editTextTextPersonNam1);
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getNumber() {
        return Number;
    }
}
