package com.epiclancers.intrototheroomlibrary.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.epiclancers.intrototheroomlibrary.EditMobile;
import com.epiclancers.intrototheroomlibrary.R;
import com.epiclancers.intrototheroomlibrary.database.Mobile;

import java.util.List;

public class MobileAdapters extends RecyclerView.Adapter<MobileAdapters.MobileHolder> {

    List<Mobile> mobileList;
    Context context;

    public MobileAdapters(List<Mobile> mobileList, Context context) {
        this.mobileList = mobileList;
        this.context = context;
    }

    public void setMobileList(List<Mobile> list){
        mobileList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MobileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product_layout,viewGroup,false);
        return new MobileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileHolder mobileHolder, int i) {
        Mobile mobile = mobileList.get(i);
        mobileHolder.bindDataToUi(mobile);
    }

    @Override
    public int getItemCount() {
        return mobileList.size();
    }

    public class MobileHolder extends RecyclerView.ViewHolder{

        ImageView mobileImage,cartButton;
        TextView mobileName,mobilePrice,mobileRam;

        public MobileHolder(@NonNull View itemView) {
            super(itemView);
            mobileImage = itemView.findViewById(R.id.mobileImage);
            cartButton = itemView.findViewById(R.id.cartButton);
            mobileName = itemView.findViewById(R.id.mobileName);
            mobilePrice = itemView.findViewById(R.id.mobilePrice);
            mobileRam = itemView.findViewById(R.id.mobileRam);
        }

        public void bindDataToUi(final Mobile mobile){
            Glide.with(context).load(mobile.getMobileImageUrl()).into(mobileImage);
            cartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Product Added to Cart", Toast.LENGTH_SHORT).show();
                }
            });
            mobileName.setText(mobile.getMobileName());
            mobilePrice.setText("₹"+mobile.getMobilePrice());
            mobileRam.setText("RAM : " + mobile.getMobileRam());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( context , EditMobile.class );
                    intent.putExtra("mobile" , mobile);
                    context.startActivity(intent);
                }
            });
        }
    }
}
