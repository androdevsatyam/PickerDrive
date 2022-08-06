package com.hpweb.pickerdrive;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.OptionView> {

    Context context;
    String[] title = new String[]{};
    int[] icon = new int[]{};

    public MainAdapter(Context context, String[] title, int[] icon) {
        this.context = context;
        this.title = title;
        this.icon = icon;
    }

    @NonNull
    @Override
    public OptionView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.button_item, parent, false);
        return new OptionView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionView holder, int position) {
        holder.title.setText(title[position]);
        holder.imageView.setImageResource(icon[position]);

        holder.card.setOnClickListener(view -> {
            if(context instanceof MainActivity) {
                Form.Type = holder.title.getText().toString();
                Intent intent = new Intent(context, Form.class);
                context.startActivity(intent);
            }else if(context instanceof Transaction){
                ((Transaction)context).showList(holder.getAdapterPosition());
            }else{
                Form.Type = holder.title.getText().toString();
                Intent intent = new Intent(context, Form.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class OptionView extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        RelativeLayout card;

        public OptionView(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.icon1);
            title = itemView.findViewById(R.id.title1);
            card = itemView.findViewById(R.id.card);
        }
    }

}
