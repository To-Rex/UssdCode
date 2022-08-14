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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.adapters.SliderAdapter;
import com.ussd.ussdcode.classes.*;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentUsell extends Fragment {

    ViewPager viewPager;
    String sl = "0";
    int[] images = {R.drawable.usell1, R.drawable.usell2, R.drawable.usell3};
    int currentPageCunter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usell, container, false);
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewpagerusel);
        viewPager.setAdapter(new SliderAdapter(images, requireContext()));
        fun();
        Button btnusel1 = view.findViewById(R.id.btnusel1);
        Button btnusel2 = view.findViewById(R.id.btnusel2);
        Button btnusel3 = view.findViewById(R.id.btnusel3);
        Button btnusel4 = view.findViewById(R.id.btnusel4);
        Button btnusel5 = view.findViewById(R.id.btnusel5);
        Button btnusel6 = view.findViewById(R.id.btnusel6);
        Button btnusel7 = view.findViewById(R.id.btnusel7);
        Button btnusel8 = view.findViewById(R.id.btnusel8);

        btnusel1.setText(" " + getString(R.string.tarif_rejalar));
        btnusel2.setText(" " + getString(R.string.internet_to_plamlar));
        btnusel3.setText(" " + getString(R.string.sms_to_plamlar));
        btnusel4.setText(" " + getString(R.string.daqiqa_to_plamlar));
        btnusel5.setText(" " + getString(R.string.hizmatlar));
        btnusel6.setText(" " + getString(R.string.ussd_kodlar));
        btnusel7.setText(" " + getString(R.string.balans_tekshirish));
        btnusel8.setText(" " + getString(R.string.raqamni_aniqlash));

        btnusel1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TarifRejalar.class);
            intent.putExtra("tarif", "Usell");
            startActivity(intent);
        });
        btnusel2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InternetToplamlar.class);
            intent.putExtra("tarif", "Usell");
            startActivity(intent);
        });
        btnusel3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InternetToplamlar.class);
            intent.putExtra("tarif", "Usell");
            startActivity(intent);
        });
        btnusel4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Daqiqalar.class);
            intent.putExtra("tarif", "Usell");
            startActivity(intent);
        });
        btnusel5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Hizmatlar.class);
            intent.putExtra("tarif", "Usell");
            startActivity(intent);
        });
        btnusel6.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UssdCodlar.class);
            intent.putExtra("tarif", "Usell");
            startActivity(intent);
        });
        btnusel7.setOnClickListener(v -> {
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

        btnusel8.setOnClickListener(v -> {
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