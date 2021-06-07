package com.example.examsitgen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examsitgen.R;
import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.AllocatedSitModel;
import com.example.examsitgen.models.DepartmentModel;

import java.util.ArrayList;

public class AllocatedSitsAdapter extends RecyclerView.Adapter<AllocatedSitsAdapter.ItemHolderRecord> {

    private Context context;
    private ArrayList<AllocatedSitModel> allocatedSitsList;

    //DB Helper
    DbHelper dbHelper;

    public AllocatedSitsAdapter(Context context, ArrayList<AllocatedSitModel> allocatedSitsList) {
        this.context = context;
        this.allocatedSitsList = allocatedSitsList;
        dbHelper = new DbHelper(context);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AllocatedSitsAdapter.ItemHolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.student_assigned_row, parent,false);
        return new AllocatedSitsAdapter.ItemHolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllocatedSitsAdapter.ItemHolderRecord holder, int position) {

        AllocatedSitModel model = allocatedSitsList.get(position);
        final String id = model.getId();
        final String studentName = model.getStudentName();
        final String studentDept = model.getStudentDepartment();
        final String studentLevl = model.getStudentLevel();
        final String studentHall = model.getHallName();
        final String seatNumber = model.getSitNumber();

        //set data to views
        holder.studentNameTv.setText("Student Name: "+studentName);
        //holder.itemIv.setImageURI(Uri.parse(itemImage));
        holder.studentDeptTv.setText("Department:" +studentDept);
        holder.studentLevelTv.setText("Level: "+studentLevl);
        holder.studentHallTv.setText("Hall Name: "+studentHall);
        holder.seatNo.setText("Sit Number: "+seatNumber);
    }

    @Override
    public int getItemCount() {
        return allocatedSitsList.size();
    }

    public class ItemHolderRecord extends RecyclerView.ViewHolder {
        //variables
        private Context context;
        private ArrayList<AllocatedSitModel> allocatedSitsList;

        //views
        ImageView itemIv;
        TextView studentNameTv, studentDeptTv, studentLevelTv, studentHallTv, seatNo;
        ImageButton moreBtn;
        public ItemHolderRecord(@NonNull View itemView) {
            super(itemView);

            studentDeptTv = itemView.findViewById(R.id.studentDeptTv);
            studentNameTv= itemView.findViewById(R.id.studentNameTv);
            studentLevelTv = itemView.findViewById(R.id.studentLevelTv);
           studentHallTv = itemView.findViewById(R.id.studentHallTv);
           seatNo = itemView.findViewById(R.id.seatNo);
        }
    }
}
