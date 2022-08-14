package com.ussd.ussdcode.classes;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.*;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.SqlData;
import com.ussd.ussdcode.adapters.InternetToplamAdapter;
import com.ussd.ussdcode.models.ModelDaqiqalar;
import com.ussd.ussdcode.models.ModelInternetToplamlar;

import java.util.ArrayList;
import java.util.Objects;

public class InternetToplamlar extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    String[] data  ={"a","b"};
    ArrayList<String> tablist ;
    ArrayList<String> listdata ;
    ArrayList<ModelInternetToplamlar> dataModalArrayList;
    InternetToplamAdapter adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SqlData sqlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_toplamlar);

        tabLayout = findViewById(R.id.internettabs);
        viewPager = (ViewPager2) findViewById(R.id.internetviewpager);

        sqlData = new SqlData(this);
        firebaseDatabase = FirebaseDatabase.getInstance();

        String company = getIntent().getExtras().getString("tarif").trim();
        databaseReference = firebaseDatabase.getReference(company);

        tablist = new ArrayList<>();
        listdata = new ArrayList<>();
        dataModalArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("Internet To`plamlar").getChildren()) {
                    String daq_name = dataSnapshot.getKey();
                    for (DataSnapshot dataSnapshot1 : snapshot.child("Internet To`plamlar/"+daq_name).getChildren()) {
                        String daq_name1 = dataSnapshot1.getKey();
                        String hiz_ulanish = Objects.requireNonNull(dataSnapshot1.child("Code").getValue()).toString();
                        String hiz_havolasi = Objects.requireNonNull(dataSnapshot1.child("Muddati").getValue()).toString();
                        String hiz_mal = Objects.requireNonNull(dataSnapshot1.child("Narxi").getValue()).toString();
                        dataModalArrayList.add(new ModelInternetToplamlar(daq_name1,hiz_ulanish,hiz_havolasi,hiz_mal,daq_name));
                        //listdata.add(daq_name1+","+hiz_ulanish+","+hiz_havolasi+","+hiz_mal+"^");
                    }
                    tablist.add(daq_name);
                    listdata.add(daq_name+"^"+company);

                    String[] mStringArray = new String[listdata.size()];
                    data = listdata.toArray(mStringArray);
               }
                adapter = new InternetToplamAdapter(InternetToplamlar.this,data);
                viewPager.setAdapter(adapter);
                new TabLayoutMediator(tabLayout, viewPager,
                        new TabLayoutMediator.OnConfigureTabCallback() {
                            @Override
                            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                                //tab.setText(tabtitle[position]);
                                tab.setText((CharSequence) tablist.get(position));
                            }
                        }).attach();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}