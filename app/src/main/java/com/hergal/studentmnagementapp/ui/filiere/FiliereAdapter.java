package com.hergal.studentmnagementapp.ui.filiere;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.model.Filiere;

public class FiliereAdapter extends RecyclerView.Adapter<FiliereViewHolder> {

    private Context context;
    private List<Filiere> filiereList;

    @SuppressLint("NotifyDataSetChanged")
    public void setSearchList(List<Filiere> dataSearchList){
        this.filiereList = dataSearchList;
        notifyDataSetChanged();
    }

    public FiliereAdapter(Context context, List<Filiere> filiereList){
        this.context = context;
        this.filiereList = filiereList;
    }

    @NonNull
    @Override
    public FiliereViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_filiere, parent, false);
        return new FiliereViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FiliereViewHolder holder, int position) {

        holder.code.setText(filiereList.get(position).getCode());
        holder.cordonnateur.setText(filiereList.get(position).getCordonateur());
        holder.dateCreation.setText(filiereList.get(position).getDate_ouverture());


        holder.FiliereCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailFiliereActivity.class);


                intent.putExtra("intitule", filiereList.get(holder.getAdapterPosition()).getIntitule());
                intent.putExtra("description", filiereList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("code", filiereList.get(holder.getAdapterPosition()).getCode());
                intent.putExtra("date_creation", filiereList.get(holder.getAdapterPosition()).getDate_ouverture());
                intent.putExtra("cordonnateur", filiereList.get(holder.getAdapterPosition()).getCordonateur());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filiereList.size();
    }
}

class FiliereViewHolder extends RecyclerView.ViewHolder{

    TextView code , dateCreation, cordonnateur;
    CardView FiliereCard;

    public FiliereViewHolder(@NonNull View itemView) {
        super(itemView);

        cordonnateur = itemView.findViewById(R.id.Cordonnateur);
        dateCreation = itemView.findViewById(R.id.DateCreation);
        code = itemView.findViewById(R.id.CodeFiliere);
        FiliereCard = itemView.findViewById(R.id.FiliereCard);
    }
}