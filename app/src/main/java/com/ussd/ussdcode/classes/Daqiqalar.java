package com.ussd.ussdcode.classes;

import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.SqlData;
import com.ussd.ussdcode.adapters.DaqiqaTAdapter;
import com.ussd.ussdcode.models.ModelDaqiqalar;

import java.util.ArrayList;
import java.util.Objects;

public class Daqiqalar extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SqlData sqlData;
    DaqiqaTAdapter adapter;
    ArrayList<ModelDaqiqalar> dataModalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daqiqalar);

        ListView daqiqalist = findViewById(R.id.daqiqalist);

        sqlData = new SqlData(this);
        dataModalArrayList = new ArrayList<>();
        daqiqalist.setDivider(null);
        daqiqalist.setDividerHeight(1);
        firebaseDatabase = FirebaseDatabase.getInstance();

        String company = getIntent().getExtras().getString("tarif").trim();
        databaseReference = firebaseDatabase.getReference(company);
        adapter = new DaqiqaTAdapter(dataModalArrayList, this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                daqiqalist.setAdapter(null);
                for (DataSnapshot dataSnapshot : snapshot.child("Daqiqa Toplamlar").getChildren()) {
                    String daq_name = dataSnapshot.getKey();
                    String daq_code = Objects.requireNonNull(dataSnapshot.child("Code").getValue()).toString();
                    String daq_muddat = Objects.requireNonNull(dataSnapshot.child("Muddati").getValue()).toString();
                    String daq_narx = Objects.requireNonNull(dataSnapshot.child("Narxi").getValue()).toString();
                    String daq_links = Objects.requireNonNull(dataSnapshot.child("Link").getValue()).toString();
                    dataModalArrayList.add(new ModelDaqiqalar(daq_name,daq_code,daq_muddat,daq_narx,company,daq_links));
                }
                daqiqalist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
