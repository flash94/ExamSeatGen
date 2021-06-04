package com.example.examsitgen.adapters;

import android.content.Context;
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
import com.example.examsitgen.models.StudentDetailsModel;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ItemHolderRecord>{

    //variables
    private Context context;
    private ArrayList<StudentDetailsModel> studentsList;
    String allocatedHall;
    String allocatedSitNo;

    //DB Helper
    DbHelper dbHelper;

    //constructor
    public StudentsAdapter(Context context, ArrayList<StudentDetailsModel> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
        dbHelper = new DbHelper(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentsAdapter.ItemHolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_row, parent,false);
        return new StudentsAdapter.ItemHolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.ItemHolderRecord holder, int position) {
        //get data, set data, handle view clicks in this method
        StudentDetailsModel model = studentsList.get(position);
        final String id = model.getId();

        final String studentName = model.getStudentName();
        final String studentId = model.getStudentId();
        final String studentLevel= model.getStudentLevel();
        final String studentDepartment= model.getStudentDepartment();
        final String studentCourse= model.getStudentCourse();
        final String addedTime = model.getAddedTime();
        final String updatedTime = model.getUpdatedTime();

        //set data to views
        holder.studentNameTv.setText("Name: "+studentName);
        //holder.itemIv.setImageURI(Uri.parse(itemImage));
        holder.studentDeptTv.setText("Department:" +studentDepartment);
        holder.studentHallTv.setText("Hall: "+allocatedHall);
        holder.studentLevelTv.setText("Level: "+studentLevel);
        holder.seatNo.setText("Sit No: "+allocatedSitNo);


        //handle onclick (go to detail record activity)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Selected " + studentName, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra("DEPARTMENT_ID", id);
//                intent.putExtra("STUDENT_NUMBER", departmentStudentNo);
//                context.startActivity(intent);
                //will later implement

            }
        });

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ItemHolderRecord extends RecyclerView.ViewHolder {
        //variables
        private Context context;
        private ArrayList<StudentDetailsModel> studentsList;

        //views
        ImageView itemIv;
        TextView studentNameTv, studentDeptTv, studentHallTv, studentLevelTv,seatNo ;
        ImageButton moreBtn;
        public ItemHolderRecord(@NonNull View itemView) {
            super(itemView);

            //init views

            studentNameTv = itemView.findViewById(R.id.studentNameTv);
            studentDeptTv = itemView.findViewById(R.id.studentDeptTv);
            studentHallTv = itemView.findViewById(R.id.studentHallTv);
            studentLevelTv = itemView.findViewById(R.id.studentLevelTv);
            seatNo = itemView.findViewById(R.id.seatNo);
        }


    }
}
