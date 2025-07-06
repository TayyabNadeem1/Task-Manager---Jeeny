package com.example.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.model.TaskModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView taskRv;
    ArrayList<TaskModel> dataList=new ArrayList<>();
    TaskListAdapter taskListAdapter;
    FirebaseFirestore db;
    String TAG="Homepage query docs";
    TextView userNameTv;
    ImageView userImageIv;
    SearchView searchView;
    Button btnFilterPersonal, btnFilterWork, btnFilterLearning;

    private void filterTasksByCategory(String category) {
        dataList.clear();
        taskListAdapter.clearAllItems();

        db.collection("tasks")
                .whereEqualTo("userId", FirebaseAuth.getInstance().getUid())
                .whereEqualTo("category", category)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            TaskModel taskModel = document.toObject(TaskModel.class);
                            taskModel.setTaskId(document.getId());
                            dataList.add(taskModel);
                        }
                        taskListAdapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "Error filtering tasks: ", task.getException());
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        db=FirebaseFirestore.getInstance();
        taskRv=findViewById(R.id.taskListRv);
        userNameTv=findViewById(R.id.userNameTv);
        userImageIv=findViewById(R.id.userProfileIv);
        searchView=findViewById(R.id.searchview);

        btnFilterPersonal = findViewById(R.id.btnFilterPersonal);
        btnFilterWork = findViewById(R.id.btnFilterWork);
        btnFilterLearning = findViewById(R.id.btnFilterLearning);

        btnFilterPersonal.setOnClickListener(v -> filterTasksByCategory("Personal"));
        btnFilterWork.setOnClickListener(v -> filterTasksByCategory("Work"));
        btnFilterLearning.setOnClickListener(v -> filterTasksByCategory("Learning"));



        userImageIv = findViewById(R.id.userProfileIv);

        userImageIv.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(HomeActivity.this, view);
            popupMenu.getMenu().add("Sign Out");

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getTitle().equals("Sign Out")) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class)); // Replace with your login screen
                    finish();
                }
                return true;
            });

            popupMenu.show();
        });




        userNameTv.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

//        Picasso.get().load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).into(userImageIv);





        taskListAdapter=new TaskListAdapter(dataList);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        taskRv.setLayoutManager(layoutManager);
        taskRv.setAdapter(taskListAdapter);


        findViewById(R.id.addTaskFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AddTaskActivity.class));
            }
        });


        db.collection("tasks")
                .whereEqualTo("userId", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                TaskModel taskModel= document.toObject(TaskModel.class);
                                taskModel.setTaskId(document.getId());

                                dataList.add(taskModel);
                                taskListAdapter.notifyDataSetChanged();





                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




//test

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("search"," "+s);
                taskListAdapter.clearAllItems();
                dataList.clear();

                taskListAdapter.clearAllItems();
                db.collection("tasks")
                        .orderBy("taskName")
                        .startAt(s).
                        endAt(s+'\uf8ff')

                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {


                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());


                                        TaskModel taskModel= document.toObject(TaskModel.class);
                                        taskModel.setTaskId(document.getId());

                                        dataList.add(taskModel);
                                        taskListAdapter.notifyDataSetChanged();





                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });

                return false;
            }

            // Only define this once at class level — keep this one and delete the inner one
            private void filterTasksByCategory(String category) {
                dataList.clear();
                taskListAdapter.notifyDataSetChanged();

                db.collection("tasks")
                        .whereEqualTo("userId", FirebaseAuth.getInstance().getUid())
                        .whereEqualTo("category", category)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    TaskModel taskModel = document.toObject(TaskModel.class);
                                    taskModel.setTaskId(document.getId());
                                    dataList.add(taskModel);
                                }
                                taskListAdapter.notifyDataSetChanged();
                            } else {
                                Log.d(TAG, "Error filtering tasks: ", task.getException());
                            }
                        });


        }

            @Override
            public boolean onQueryTextChange(String s) {



                return false;
            }
        });







    }
}