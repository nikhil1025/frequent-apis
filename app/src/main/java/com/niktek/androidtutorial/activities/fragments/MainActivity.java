package com.niktek.androidtutorial.activities.fragments;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.niktek.androidtutorial.R;
// import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements fragment1.phoneCall {

    Button frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Fragment frag = new Fragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, frag, "fragmentView");*/


        frag2 = findViewById(R.id.frag2);

//        dataModel model = new ViewModelProvider(this).get(dataModel.class);
//        TextView text = findViewById(R.id.textField);
//        text.setText(model.printData());

        //Attaching First Fragment
        fragment1 frag_one = new fragment1();
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.layout, frag_one).commit();


//        frag1.setOnClickListener(view -> {
//            Toast.makeText(MainActivity2.this, "", Toast.LENGTH_SHORT).show();
//        });

        frag2.setOnClickListener(view -> {
            fragment2 frag_two = new fragment2();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.layout, frag_two).commit();
        });

//        fragContent.setOnClickListener(v -> {
////            dataModel model = new ViewModelProvider(this).get(dataModel.class);
////            model.printData();
//        });
    }


    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        super.onAttachFragment(fragment);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Started", "Activity");
        fragment1.phoneCall ph = new MainActivity();
        ph.setPhone();
    }

    @Override
    public void setPhone() {
        Log.d("sds", "sdcscs");
        Button frag1 = findViewById(R.id.frag1);
        frag1.setOnClickListener(v -> {
            Log.e("sds", "sdcsdcfsdcscs");
         //   fragment1 frag_one_again = new fragment1(true);
           // Toast.makeText(this, "sdsfsd", Toast.LENGTH_SHORT).show();
        });
    }
}