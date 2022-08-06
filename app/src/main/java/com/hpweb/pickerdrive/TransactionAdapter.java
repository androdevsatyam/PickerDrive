package com.hpweb.pickerdrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.FormModel;
import com.model.TransactionModel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionView> {

    Context context;
    ArrayList<FormModel> data;

    public TransactionAdapter(Context context, ArrayList<FormModel> list) {
        this.context = context;
        this.data = list;
    }

    @NonNull
    @Override
    public TransactionView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.transaction_row, parent, false);
        return new TransactionView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionView holder, int position) {
        holder.comp.setText(""+data.get(position).getCOMPANY_NAME());
        holder.mobile.setText(""+data.get(position).getMOBILE());
        holder.addrs.setText(data.get(position).getADDRESS());
        holder.date.setText(data.get(position).getDate());
        holder.from.setText(data.get(position).getFROM());
        holder.to.setText(data.get(position).getTO());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TransactionView extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView comp,mobile,addrs,date, from, to;
        RelativeLayout card;

        public TransactionView(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.icon1);
            date = itemView.findViewById(R.id.date);
            comp = itemView.findViewById(R.id.company);
            mobile = itemView.findViewById(R.id.mobile);
            addrs = itemView.findViewById(R.id.address);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            card = itemView.findViewById(R.id.row);
        }
    }

}
