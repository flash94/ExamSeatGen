package com.example.examsitgen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examsitgen.AllocatingSeatLoader;
import com.example.examsitgen.R;
import com.example.examsitgen.SitAllocationFailed;
import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.HallDetailsModel;
import com.example.examsitgen.models.StudentDetailsModel;

import java.util.ArrayList;

public class HallsAdapter extends RecyclerView.Adapter<HallsAdapter.ItemHolderRecord>{
    //variables
    private Context context;
    private ArrayList<HallDetailsModel> hallsList;
    private String departmentLevel, departmentName, departmentTotalNo;

    //DB Helper
    DbHelper dbHelper;

    //constructor
    public HallsAdapter(Context context, ArrayList<HallDetailsModel> hallsList, String departmentLevel, String departmentName, String departmentTotalNo) {
        this.context = context;
        this.hallsList= hallsList;
        this.departmentLevel = departmentLevel;
        this.departmentName = departmentName;
        this.departmentTotalNo = departmentTotalNo;
        dbHelper = new DbHelper(context);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HallsAdapter.ItemHolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hall_row, parent,false);
        return new HallsAdapter.ItemHolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallsAdapter.ItemHolderRecord holder, int position) {
        //get data, set data, handle view clicks in this method
        HallDetailsModel model = hallsList.get(position);
        final String id = model.getId();

        final String hallName = model.getHallName();
        final String hallCapacity = model.getHallCapacity().trim();
        final String addedTime = model.getAddedTime();
        final String updatedTime = model.getUpdatedTime();

        //set data to views
        holder.hallNameTv.setText("Hall Name: "+hallName);
        //holder.itemIv.setImageURI(Uri.parse(itemImage));
        holder.hallCapacityTv.setText("Hall Capacity: " +hallCapacity + " Seats");


        //handle onclick (go to detail record activity)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Selected " + hallName, Toast.LENGTH_LONG).show();

                int hallCap = Integer.parseInt(hallCapacity);
                int deptTotalNo = Integer.parseInt(departmentTotalNo);
                if (deptTotalNo > hallCap){
                    Toast.makeText(context, "Sit Allocation failed, Insufficient sits", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, SitAllocationFailed.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, AllocatingSeatLoader.class);
                intent.putExtra("DEPARTMENT", departmentName);
                intent.putExtra("DEPARTMENT_LEVEL", departmentLevel);
                intent.putExtra("HALL_CAPACITY", hallCapacity);
                intent.putExtra("HALL_NAME", hallName);
                    context.startActivity(intent);
                    //will later implement
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hallsList.size();
    }

    public class ItemHolderRecord extends RecyclerView.ViewHolder {
        //variables
        private Context context;
        private ArrayList<HallDetailsModel> hallsList;
        private String departmentId, departmentName, departmentTotalNo;

        //views
        TextView hallNameTv, hallCapacityTv;
        public ItemHolderRecord(@NonNull View itemView) {
            super(itemView);

            //init views

            hallNameTv = itemView.findViewById(R.id.hallNameTv);
            hallCapacityTv = itemView.findViewById(R.id.hallCapacityTv);
        }
    }
}
