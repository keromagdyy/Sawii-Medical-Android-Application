package com.example.sawii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class AdviceRecyclerViewAdaptor extends RecyclerView.Adapter<AdviceRecyclerViewAdaptor.MyViewHolder> {
    String title[];
    String body[];
    Context context;

    public AdviceRecyclerViewAdaptor(Context context, String[] title, String[] body) {
        this.context = context;
        this.title = title;
        this.body = body;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_advice, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.txtBody.setText(body[position]);
        holder.txtTitle.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return body.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtBody, txtTitle;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtBody = itemView.findViewById(R.id.txtBody);
            txtTitle = itemView.findViewById(R.id.txtTitle);

        }
    }
}
