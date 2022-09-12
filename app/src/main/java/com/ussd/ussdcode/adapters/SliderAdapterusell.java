package com.ussd.ussdcode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.ussd.ussdcode.R;

public class SliderAdapterusell extends PagerAdapter {

    int[] images;
    LayoutInflater layoutInflater;
    Context context;

    public SliderAdapterusell(int[] images, Context context) {
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View myImageLayout = layoutInflater.inflate(R.layout.slide_usel,container, false);
        ImageView imageview = myImageLayout.findViewById(R.id.imageviewuslell);

        imageview.setImageDrawable(context.getDrawable(images[position]));

        container.addView(myImageLayout);

        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view.equals(object);
    }
}
