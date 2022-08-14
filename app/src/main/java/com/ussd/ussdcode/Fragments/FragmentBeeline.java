package com.ussd.ussdcode.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.adapters.SliderAdapter;
import com.ussd.ussdcode.classes.*;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentBeeline extends Fragment {
    ViewPager viewPager;
    String sl = "0";
    int[] images = {R.drawable.belline1, R.drawable.belline2, R.drawable.belline3};
    int currentPageCunter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beeline, container, false);
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SliderAdapter(images, requireContext()));
        fun();
        Button btnbeelin1 = view.findViewById(R.id.btnbeelin1);
        Button btnbeelin2 = view.findViewById(R.id.btnbeelin2);
        Button btnbeelin3 = view.findViewById(R.id.btnbeelin3);
        Button btnbeelin4 = view.findViewById(R.id.btnbeelin4);
        Button btnbeelin5 = view.findViewById(R.id.btnbeelin5);
        Button btnbeelin6 = view.findViewById(R.id.btnbeelin6);
        Button btnbeelin7 = view.findViewById(R.id.btnbeelin7);
        Button btnbeelin8 = view.findViewById(R.id.btnbeelin8);

        btnbeelin1.setText(" " + getString(R.string.tarif_rejalar));
        btnbeelin2.setText(" " + getString(R.string.internet_to_plamlar));
        btnbeelin3.setText(" " + getString(R.string.sms_to_plamlar));
        btnbeelin4.setText(" " + getString(R.string.daqiqa_to_plamlar));
        btnbeelin5.setText(" " + getString(R.string.hizmatlar));
        btnbeelin6.setText(" " + getString(R.string.ussd_kodlar));
        btnbeelin7.setText(" " + getString(R.string.balans_tekshirish));
        btnbeelin8.setText(" " + getString(R.string.raqamni_aniqlash));

        btnbeelin1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TarifRejalar.class);
            intent.putExtra("tarif", "Beeline");
            startActivity(intent);
        });
        btnbeelin2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InternetToplamlar.class);
            intent.putExtra("tarif", "Beeline");
            startActivity(intent);
        });
        btnbeelin3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InternetToplamlar.class);
            intent.putExtra("tarif", "Beeline");
            startActivity(intent);
        });
        btnbeelin4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Daqiqalar.class);
            intent.putExtra("tarif", "Beeline");
            startActivity(intent);
        });
        btnbeelin5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Hizmatlar.class);
            intent.putExtra("tarif", "Beeline");
            startActivity(intent);
        });
        btnbeelin6.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UssdCodlar.class);
            intent.putExtra("tarif", "Beeline");
            startActivity(intent);
        });
        btnbeelin7.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:*100*1" + Uri.encode("#")));
            if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) !=
                    android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Iltimos ilovaga ruxsat bering", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            } else {
                startActivity(intent);
            }
        });
        btnbeelin8.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:*100*1" + Uri.encode("#")));
            if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) !=
                    android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Iltimos ilovaga ruxsat bering", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            } else {
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        sl = "1";
    }

    @Override
    public void onResume() {
        super.onResume();
        sl = "0";
    }

    private void fun() {
        if (!sl.equals("1")) {
            final Handler handler = new Handler();
            final Runnable update = () -> {
                if (currentPageCunter == images.length) {
                    currentPageCunter = 0;
                }
                viewPager.setCurrentItem(currentPageCunter++, true);
            };
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, 3500, 3500);
        }
    }
}