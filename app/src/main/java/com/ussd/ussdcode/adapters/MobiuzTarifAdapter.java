package com.ussd.ussdcode.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.core.app.ActivityCompat;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.models.ModelTarifRejalar;

import java.util.List;

public class MobiuzTarifAdapter extends BaseAdapter {

    private final List<ModelTarifRejalar> itemsModelsl;
    private List<ModelTarifRejalar> itemsModelListFiltered;
    private final Context context;

    public MobiuzTarifAdapter(List<ModelTarifRejalar> itemsModelsl, Context context) {
        this.itemsModelsl = itemsModelsl;
        this.itemsModelListFiltered = itemsModelsl;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemsModelListFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"SetTextI18n", "NewApi", "ClickableViewAccessibility", "ResourceAsColor"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(context).inflate(R.layout.tarif_reja_item, parent, false);
        }

        ModelTarifRejalar dataModal = (ModelTarifRejalar) getItem(position);
        TextView txtttreja = listitemView.findViewById(R.id.txtttreja);
        TextView txttcoin = listitemView.findViewById(R.id.txttcoin);
        Button btntulan = listitemView.findViewById(R.id.btntulan);

        txtttreja.setText(dataModal.getName());
        txttcoin.setText(dataModal.getCoin() + " so`m");
        String ussdcode = dataModal.getCode().replace("#", "");
        String status = dataModal.getStatus();
        if (status.equals("Beeline")) {
            btntulan.setBackground(context.getResources().getDrawable(R.drawable.shadovbeeline));
            txtttreja.setTextColor(context.getResources().getColor(R.color.orange));
        }
        if (status.equals("MobiUz")) {
            btntulan.setBackground(context.getResources().getDrawable(R.drawable.shadovmobiuz));
            txtttreja.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (status.equals("Usell")) {
            btntulan.setBackground(context.getResources().getDrawable(R.drawable.shadovusel));
            txtttreja.setTextColor(context.getResources().getColor(R.color.teal_201));
        }
        if (status.equals("UzMobile")) {
            btntulan.setBackground(context.getResources().getDrawable(R.drawable.shadovuzmob));
            txtttreja.setTextColor(context.getResources().getColor(R.color.blue));
        }

        btntulan.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_tarif_rejalar);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            TextView txtditname = dialog.findViewById(R.id.txtditname);
            TextView txtditinfo = dialog.findViewById(R.id.txtditinfo);
            Button btndiulan = dialog.findViewById(R.id.btndiulan);
            Button btndiorqa = dialog.findViewById(R.id.btndiorqa);
            Button btndibatafsil = dialog.findViewById(R.id.btndibatafsil);

            txtditname.setText(dataModal.getName());
            if (dataModal.getStatus().equals("Beeline")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.orange));
                btndibatafsil.setTextColor(context.getResources().getColor(R.color.orange));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.orange));
            }
            if (dataModal.getStatus().equals("MobiUz")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.red));
                btndibatafsil.setTextColor(context.getResources().getColor(R.color.red));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.red));
           }
            if (dataModal.getStatus().equals("Usell")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.teal_201));
                btndibatafsil.setTextColor(context.getResources().getColor(R.color.teal_201));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.teal_201));
           }
            if (dataModal.getStatus().equals("UzMobile")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.blue));
                btndibatafsil.setTextColor(context.getResources().getColor(R.color.blue));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.blue));
            }
            txtditinfo.setText(dataModal.getAlldata().replace("^", "\n"));
            btndiulan.setOnClickListener(v1 -> {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ussdcode + Uri.encode("#")));
                dialog.dismiss();
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) !=
                        android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Iltimos ilovaga ruxsat bering", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                } else {
                    context.startActivity(intent);
                }
            });
            dialog.show();
            btndiorqa.setOnClickListener(v1 -> dialog.dismiss());
            btndibatafsil.setOnClickListener(v1 -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataModal.getUrlcompany()));
                context.startActivity(intent);
            });
        });
        return listitemView;
    }
}
