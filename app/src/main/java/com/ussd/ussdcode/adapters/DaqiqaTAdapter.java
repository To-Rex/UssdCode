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

import java.util.List;

public class DaqiqaTAdapter extends BaseAdapter {
    private final List<ModelDaqiqalar> itemsModelsl;
    private List<ModelDaqiqalar> itemsModelListFiltered;
    private final Context context;

    public DaqiqaTAdapter(List<ModelDaqiqalar> itemsModelsl, Context context) {
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
            listitemView = LayoutInflater.from(context).inflate(R.layout.daqiqa_item, parent, false);
        }

        ModelDaqiqalar dataModal = (ModelDaqiqalar) getItem(position);
        TextView txtdaqname = listitemView.findViewById(R.id.txtdaqname);
        TextView txtdaqnarxi = listitemView.findViewById(R.id.txtdaqnarxi);
        TextView txtdaqmuddati = listitemView.findViewById(R.id.txtdaqmuddati);
        TextView txtdaqcode = listitemView.findViewById(R.id.txtdaqcode);
        String status = dataModal.getStatus();
        if (status.equals("Beeline")) {
            txtdaqname.setTextColor(context.getResources().getColor(R.color.orange));
        }
        if (status.equals("MobiUz")) {
            txtdaqname.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (status.equals("UzMobile")) {
            txtdaqname.setTextColor(context.getResources().getColor(R.color.blue));
        }
        if (status.equals("Usell")) {
            txtdaqname.setTextColor(context.getResources().getColor(R.color.teal_201));
        }
        txtdaqname.setText("  "+dataModal.getName().substring(0,3)+"\n"+dataModal.getName().substring(3));
        txtdaqnarxi.setText(dataModal.getNarxi());
        txtdaqmuddati.setText(dataModal.getMuddati());
        txtdaqcode.setText(dataModal.getCode());
        listitemView.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_tarif_rejalar);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            TextView txtditname = dialog.findViewById(R.id.txtditname);
            TextView txtditinfo = dialog.findViewById(R.id.txtditinfo);
            Button btndiulan = dialog.findViewById(R.id.btndiulan);
            Button btndiorqa = dialog.findViewById(R.id.btndiorqa);
            Button btndibatafsil = dialog.findViewById(R.id.btndibatafsil);
            String ussdcode = dataModal.getCode().replace("#", "");
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
            txtditinfo.setText("Narxi: "+ dataModal.getNarxi()+"\nMuddati: "+dataModal.getMuddati()+
                    "\nUlanish kodi: "+dataModal.getCode());
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
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataModal.getLinks()));
                context.startActivity(intent);
            });
        });
        return listitemView;
    }
}
