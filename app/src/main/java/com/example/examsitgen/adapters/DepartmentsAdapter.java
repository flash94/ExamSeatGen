package com.example.examsitgen.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examsitgen.R;
import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.DepartmentModel;

import java.util.ArrayList;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.ItemHolderRecord> {

    //variables
    private Context context;
    private ArrayList<DepartmentModel> departmentsList;

    //DB Helper
    DbHelper dbHelper;

    //constructor
    public DepartmentsAdapter(Context context, ArrayList<DepartmentModel> departmentsList) {
        this.context = context;
        this.departmentsList = departmentsList;
        dbHelper = new DbHelper(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DepartmentsAdapter.ItemHolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.department_row, parent,false);
        return new DepartmentsAdapter.ItemHolderRecord(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentsAdapter.ItemHolderRecord holder, int position) {
        //get data, set data, handle view clicks in this method
        DepartmentModel model = departmentsList.get(position);
        final String id = model.getId();
        final String departmentName = model.getDepartment();
        final String departmentStudentNo = model.getStudentNo();
        final String departmentLevel = model.getLevel();
        final String addedTime = model.getAddedTime();
        final String updatedTime = model.getUpdatedTime();

        //set data to views
        holder.departmentNameTv.setText("Department: "+departmentName);
        //holder.itemIv.setImageURI(Uri.parse(itemImage));
        holder.deptLevelTv.setText("Level:" +departmentLevel);
        holder.deptStudentNoTv.setText("Students: "+departmentStudentNo);


        //handle onclick (go to detail record activity)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Login Failed! Invalid Login credentials", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra("DEPARTMENT_ID", id);
//                intent.putExtra("STUDENT_NUMBER", departmentStudentNo);
//                context.startActivity(intent);
                //will later implement

            }
        });

        //handle more button click listener (show options like edit, delete etc)
//        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //show option menu
//                showMoreDialog(
//                        ""+position,
//                        ""+id,
//                        ""+itemName,
//                        ""+itemImage,
//                        ""+itemPrice,
//                        ""+itemManufacturer,
//                        ""+itemMfd,
//                        ""+itemExp,
//                        ""+itemDesc,
//                        ""+daysToExpiry,
//                        ""+itemStatus,
//                        ""+addedTime,
//                        ""+updatedTime
//                );
//            }
//        });

        Log.d("ImagePath", "onBindViewHolder: "+departmentName);
    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }

    public class ItemHolderRecord extends RecyclerView.ViewHolder {
        //variables
        private Context context;
        private ArrayList<DepartmentModel> departmentsList;

        //views
        ImageView itemIv;
        TextView departmentNameTv, deptStudentNoTv, deptLevelTv;
        ImageButton moreBtn;
        public ItemHolderRecord(@NonNull View itemView) {
            super(itemView);

            //init views

            departmentNameTv = itemView.findViewById(R.id.departmentNameTv);
            deptLevelTv = itemView.findViewById(R.id.deptLevelTv);
           deptStudentNoTv = itemView.findViewById(R.id.deptStudentNoTv);
        }
    }
}
