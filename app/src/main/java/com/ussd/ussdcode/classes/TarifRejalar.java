package com.ussd.ussdcode.classes;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.SqlData;
import com.ussd.ussdcode.adapters.MobiuzTarifAdapter;
import com.ussd.ussdcode.models.ModelTarifRejalar;

import java.util.ArrayList;
import java.util.Objects;

public class TarifRejalar extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SqlData sqlData;
    MobiuzTarifAdapter adapter;
    ArrayList<ModelTarifRejalar> dataModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarif_rejalar);

        sqlData = new SqlData(this);
        ListView tarif_list = findViewById(R.id.tarif_list);
        dataModalArrayList = new ArrayList<>();
        tarif_list.setDivider(null);
        tarif_list.setDividerHeight(1);

        firebaseDatabase = FirebaseDatabase.getInstance();

        String company = getIntent().getExtras().getString("tarif").trim();
        databaseReference = firebaseDatabase.getReference(company);
        adapter = new MobiuzTarifAdapter(dataModalArrayList, this);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                tarif_list.setAdapter(null);
                for (DataSnapshot dataSnapshot : snapshot.child("Tarif Rejalar").getChildren()) {
                    String tarif_name = dataSnapshot.getKey();
                    String tarif_narxi = Objects.requireNonNull(dataSnapshot.child("Narxi").getValue()).toString();
                    String tarif_ulanish = Objects.requireNonNull(dataSnapshot.child("Ulanish").getValue()).toString();
                    String tarif_havolasi = Objects.requireNonNull(dataSnapshot.child("Link").getValue()).toString();
                    String tarif_mal = Objects.requireNonNull(dataSnapshot.child("Mal").getValue()).toString();
                    dataModalArrayList.add(new ModelTarifRejalar(tarif_name,
                            tarif_narxi,tarif_ulanish, company,tarif_havolasi,tarif_mal));
                }
                tarif_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}