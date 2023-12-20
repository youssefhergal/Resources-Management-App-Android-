package com.hergal.studentmnagementapp.ui.student;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.model.Etudiant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {


    private Context context;
    private List<Etudiant> etudiantList;

    public StudentAdapter(Context context, List<Etudiant> etudiantList) {
        this.context = context;
        this.etudiantList = etudiantList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Etudiant etudiant = etudiantList.get(position);

        // Mettez à jour les données avec les propriétés d'Etudiant
        holder.recStudentID.setText(String.valueOf(etudiant.getId()));
        holder.recStudentFName.setText(etudiant.getPrenom());
        holder.recStudentLName.setText(etudiant.getNom());
        holder.recFiliere.setText(etudiant.getFiliere());


        holder.studentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentProfileActivity.class);
                intent.putExtra("Nom", etudiant.getNom());
                intent.putExtra("Prenom", etudiant.getPrenom());
                intent.putExtra("Filiere", etudiant.getFiliere());
                intent.putExtra("Image",etudiant.getImage());
                intent.putExtra("Id",String.valueOf(etudiant.getId()));
                intent.putExtra("Email", etudiant.getEmail());
                intent.putExtra("DateInscription", etudiant.getDate_inscriptiopn());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return etudiantList.size();
    }

    public void searchDataList(ArrayList<Etudiant> searchList){
        etudiantList = searchList;
        notifyDataSetChanged();
    }
}

class StudentViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage;
    TextView recStudentID, recStudentFName, recFiliere, recStudentLName;
    CardView studentCard;


    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        studentCard = itemView.findViewById(R.id.recCard);
        recStudentID = itemView.findViewById(R.id.recStudentID);
        recStudentFName = itemView.findViewById(R.id.recStudentFName);
        recFiliere = itemView.findViewById(R.id.recFiliere);
        recStudentLName = itemView.findViewById(R.id.recStudentLName);
        recImage = itemView.findViewById(R.id.recImage);

    }
}
