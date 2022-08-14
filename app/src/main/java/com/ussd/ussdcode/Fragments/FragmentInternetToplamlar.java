package com.ussd.ussdcode.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.*;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.adapters.DaqiqaTAdapter;
import com.ussd.ussdcode.adapters.MobiuzTarifAdapter;
import com.ussd.ussdcode.models.ModelDaqiqalar;
import com.ussd.ussdcode.models.ModelInternetToplamlar;
import com.ussd.ussdcode.models.ModelTarifRejalar;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentInternetToplamlar extends Fragment {
    DaqiqaTAdapter adapter;
    ArrayList<ModelDaqiqalar> dataModalArrayList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_internet_toplamlar, container, false);
    }
    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        assert bundle != null;
        String data = bundle.getString("key");

        ListView listitoplamlar = view.findViewById(R.id.listitoplamlar);
        listitoplamlar.setDivider(null);
        listitoplamlar.setDividerHeight(1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dataModalArrayList = new ArrayList<>();
        adapter = new DaqiqaTAdapter(dataModalArrayList, getContext());
        String[] l = data.split("\\^");
        databaseReference = firebaseDatabase.getReference(l[1]);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                listitoplamlar.setAdapter(null);
                for (DataSnapshot dataSnapshot1 : snapshot.child("Internet To`plamlar/"+l[0]).getChildren()) {
                    String daq_name1 = dataSnapshot1.getKey();
                    String hiz_ulanish = Objects.requireNonNull(dataSnapshot1.child("Code").getValue()).toString();
                    String hiz_havolasi = Objects.requireNonNull(dataSnapshot1.child("Muddati").getValue()).toString();
                    String hiz_mal = Objects.requireNonNull(dataSnapshot1.child("Narxi").getValue()).toString();
                    dataModalArrayList.add(new ModelDaqiqalar(daq_name1,hiz_ulanish,hiz_havolasi,hiz_mal,l[1],"esfserfer"));

                }
                listitoplamlar.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*ModelInternetToplamlar modelInternetToplamlar = null;
        String a = bundle.getString("key");
        Toast.makeText(getContext(), a, Toast.LENGTH_SHORT).show();

        String[] dat = data.split(",");*/

    }
}
