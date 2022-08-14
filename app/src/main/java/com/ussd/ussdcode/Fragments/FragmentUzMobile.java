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

public class FragmentUzMobile extends Fragment {

    ViewPager viewPager;
    String sl = "0";
    int[] images = {R.drawable.uzmobi1, R.drawable.uzmobi2, R.drawable.uzmobi3};
    int currentPageCunter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uzmobile, container, false);
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewpageruzmobile);
        viewPager.setAdapter(new SliderAdapter(images, requireContext()));
        fun();
        Button btnuzmob1 = view.findViewById(R.id.btnuzmob1);
        Button btnuzmob2 = view.findViewById(R.id.btnuzmob2);
        Button btnuzmob3 = view.findViewById(R.id.btnuzmob3);
        Button btnuzmob4 = view.findViewById(R.id.btnuzmob4);
        Button btnuzmob5 = view.findViewById(R.id.btnuzmob5);
        Button btnuzmob6 = view.findViewById(R.id.btnuzmob6);
        Button btnuzmob7 = view.findViewById(R.id.btnuzmob7);
        Button btnuzmob8 = view.findViewById(R.id.btnuzmob8);

        btnuzmob1.setText(" " + getString(R.string.tarif_rejalar));
        btnuzmob2.setText(" " + getString(R.string.internet_to_plamlar));
        btnuzmob3.setText(" " + getString(R.string.sms_to_plamlar));
        btnuzmob4.setText(" " + getString(R.string.daqiqa_to_plamlar));
        btnuzmob5.setText(" " + getString(R.string.hizmatlar));
        btnuzmob6.setText(" " + getString(R.string.ussd_kodlar));
        btnuzmob7.setText(" " + getString(R.string.balans_tekshirish));
        btnuzmob8.setText(" " + getString(R.string.raqamni_aniqlash));

        btnuzmob1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TarifRejalar.class);
            intent.putExtra("tarif", "UzMobile");
            startActivity(intent);
        });
        btnuzmob2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InternetToplamlar.class);
            intent.putExtra("tarif", "UzMobile");
            startActivity(intent);
        });
        btnuzmob3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InternetToplamlar.class);
            intent.putExtra("tarif", "UzMobile");
            startActivity(intent);
        });
        btnuzmob4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Daqiqalar.class);
            intent.putExtra("tarif", "UzMobile");
            startActivity(intent);
        });
        btnuzmob5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Hizmatlar.class);
            intent.putExtra("tarif", "UzMobile");
            startActivity(intent);
        });
        btnuzmob6.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UssdCodlar.class);
            intent.putExtra("tarif", "UzMobile");
            startActivity(intent);
        });
        btnuzmob7.setOnClickListener(v -> {
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

        btnuzmob8.setOnClickListener(v -> {
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