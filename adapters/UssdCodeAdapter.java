package com.ussd.ussdcode.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.ussd.ussdcode.R;
import com.ussd.ussdcode.models.ModelDaqiqalar;
import com.ussd.ussdcode.models.ModelUssdCode;

import java.util.List;

public class UssdCodeAdapter extends BaseAdapter {
    private final List<ModelUssdCode> itemsModelsl;
    private List<ModelUssdCode> itemsModelListFiltered;
    private final Context context;

    public UssdCodeAdapter(List<ModelUssdCode> itemsModelsl, Context context) {
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
            listitemView = LayoutInflater.from(context).inflate(R.layout.ussdcod_item, parent, false);
        }

        ModelUssdCode dataModal = (ModelUssdCode) getItem(position);
        TextView txtusitemcode = listitemView.findViewById(R.id.txtusitemcode);
        TextView txtusitemname = listitemView.findViewById(R.id.txtusitemname);
        Button btnusitemfaoll = listitemView.findViewById(R.id.btnusitemfaoll);

        String status = dataModal.getStatus();
        if (status.equals("Beeline")) {
            txtusitemcode.setTextColor(context.getResources().getColor(R.color.orange));
            btnusitemfaoll.setBackground(context.getResources().getDrawable(R.drawable.shadovbeeline));
        }
        if (status.equals("MobiUz")) {
            txtusitemcode.setTextColor(context.getResources().getColor(R.color.red));
            btnusitemfaoll.setBackground(context.getResources().getDrawable(R.drawable.shadovmobiuz));
        }
        if (status.equals("UzMobile")) {
            txtusitemcode.setTextColor(context.getResources().getColor(R.color.blue));
            btnusitemfaoll.setBackground(context.getResources().getDrawable(R.drawable.shadovuzmob));
        }
        if (status.equals("Usell")) {
            txtusitemcode.setTextColor(context.getResources().getColor(R.color.teal_201));
            btnusitemfaoll.setBackground(context.getResources().getDrawable(R.drawable.shadovusel));
        }
        txtusitemcode.setText(dataModal.getCode());
        txtusitemname.setText(dataModal.getName());
        if (dataModal.getName().length()>32){
            String f = dataModal.getName().substring(0,17)+"-\n"+dataModal.getName().substring(17,32)+"...";
            txtusitemname.setText(f);
        }else{
            txtusitemname.setText(dataModal.getName());
        }

        btnusitemfaoll.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_ussd_code);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            TextView txtditname = dialog.findViewById(R.id.txtdiussdname);
            TextView txtditinfo = dialog.findViewById(R.id.txtdiussdmal);
            Button btndiulan = dialog.findViewById(R.id.txtdiussdxa);
            Button btndiorqa = dialog.findViewById(R.id.txtdiussdyoq);
            String ussdcode = dataModal.getCode().replace("#", "");
            txtditname.setText(dataModal.getCode());

            if (dataModal.getStatus().equals("Beeline")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.orange));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.orange));
            }
            if (dataModal.getStatus().equals("MobiUz")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.red));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.red));
            }
            if (dataModal.getStatus().equals("Usell")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.teal_201));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.teal_201));
            }
            if (dataModal.getStatus().equals("UzMobile")) {
                btndiulan.setTextColor(context.getResources().getColor(R.color.blue));
                btndiorqa.setTextColor(context.getResources().getColor(R.color.blue));
            }
            txtditinfo.setText("Haqiqatdan ham "+dataModal.getCode()+" USSD kodini faollashtirmoqchimisiz?\n"+dataModal.getName());
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

        });


        return listitemView;
    }
}
