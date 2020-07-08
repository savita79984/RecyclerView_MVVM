package com.assesmenttest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assesmenttest.R;
import com.assesmenttest.models.response.Datum;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Datum> data;
    private Context mContext;

    public RecyclerAdapter(Context context, ArrayList<Datum> data) {
        this.data = data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        try {
            if (data.get(i).getEmployeeName() != null)
                ((ViewHolder) viewHolder).tvName.setText("Name:" + data.get(i).getEmployeeName());

            if (data.get(i).getEmployeeSalary() != null)
                ((ViewHolder) viewHolder).tvSalary.setText("Salary:" + data.get(i).getEmployeeSalary());

            if (data.get(i).getEmployeeAge() != null)
                ((ViewHolder) viewHolder).tvAge.setText("Age:" + data.get(i).getEmployeeAge());

            // Set the image
            RequestOptions defaultOptions = new RequestOptions().error(R.drawable.ic_launcher_background);
            if (data.get(i).getProfileImage() != null && !data.get(i).getProfileImage().equalsIgnoreCase(""))
                Glide.with(mContext)
                        .setDefaultRequestOptions(defaultOptions)
                        .load(data.get(i).getProfileImage())
                        .into(((ViewHolder) viewHolder).mImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0)
            return data.size();
        else
            return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mImage;
        private TextView tvName, tvSalary, tvAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            tvName = itemView.findViewById(R.id.tvName);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            tvAge = itemView.findViewById(R.id.tvAge);
        }
    }
}
