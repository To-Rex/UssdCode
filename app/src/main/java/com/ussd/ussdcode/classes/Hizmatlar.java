package com.ussd.ussdcode.classes;

import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.SqlData;
import com.ussd.ussdcode.adapters.HIzmatlarAdapter;
import com.ussd.ussdcode.adapters.MobiuzTarifAdapter;
import com.ussd.ussdcode.adapters.UssdCodeAdapter;
import com.ussd.ussdcode.models.ModelDaqiqalar;
import com.ussd.ussdcode.models.ModelTarifRejalar;
import com.ussd.ussdcode.models.ModelUssdCode;

import java.util.ArrayList;
import java.util.Objects;

public class Hizmatlar extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SqlData sqlData;
    HIzmatlarAdapter adapter;
    ArrayList<ModelDaqiqalar> dataModalArrayList;
    ListView listhizmatlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hizmatlar);

        listhizmatlar = findViewById(R.id.listhizmatlar);

        sqlData = new SqlData(this);
        dataModalArrayList = new ArrayList<>();
        listhizmatlar.setDivider(null);
        listhizmatlar.setDividerHeight(1);
        firebaseDatabase = FirebaseDatabase.getInstance();

        String company = getIntent().getExtras().getString("tarif").trim();
        databaseReference = firebaseDatabase.getReference(company);
        adapter = new HIzmatlarAdapter(dataModalArrayList, this);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                listhizmatlar.setAdapter(null);
                for (DataSnapshot dataSnapshot : snapshot.child("Hizmatlar").getChildren()) {
                    String tarif_name = dataSnapshot.getKey();
                    String hiz_ulanish = Objects.requireNonNull(dataSnapshot.child("Code").getValue()).toString();
                    String hiz_havolasi = Objects.requireNonNull(dataSnapshot.child("Link").getValue()).toString();
                    String hiz_mal = Objects.requireNonNull(dataSnapshot.child("Mal").getValue()).toString();
                    dataModalArrayList.add(new ModelDaqiqalar(tarif_name,hiz_ulanish,hiz_mal,"0",company,hiz_havolasi));
                }
                listhizmatlar.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
