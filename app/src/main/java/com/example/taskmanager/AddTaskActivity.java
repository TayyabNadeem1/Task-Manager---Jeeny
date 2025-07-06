package com.example.taskmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.taskmanager.model.TaskModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTaskActivity extends AppCompatActivity {

    EditText etTaskInput;
    Button saveBtn;
    FirebaseFirestore db;
    String TAG="Task Manager";
    CheckBox cbPersonal, cbWork, cbLearning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        cbPersonal = findViewById(R.id.checkbox_personal);
        cbWork = findViewById(R.id.checkbox_work);
        cbLearning = findViewById(R.id.checkbox_learning);


        cbPersonal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbWork.setChecked(false);
                cbLearning.setChecked(false);
            }
        });

        cbWork.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbPersonal.setChecked(false);
                cbLearning.setChecked(false);
            }
        });

        cbLearning.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbPersonal.setChecked(false);
                cbWork.setChecked(false);
            }
        });

//        getSupportActionBar().setTitle("Add Task");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveBtn=findViewById(R.id.taskSaveBtn);
        etTaskInput=findViewById(R.id.inputTaskName);

        db = FirebaseFirestore.getInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = etTaskInput.getText().toString().trim();

                // Check if task name is empty
                if (taskName.isEmpty()) {
                    etTaskInput.setError("Task name cannot be empty");
                    return;
                }

                // ✅ Check if at least one category is selected
                if (!cbPersonal.isChecked() && !cbWork.isChecked() && !cbLearning.isChecked()) {
                    // Show a toast or inline error
                    Toast.makeText(AddTaskActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
                    return;
                }

                findViewById(R.id.progress).setVisibility(View.VISIBLE);

                String category = "";
                if (cbPersonal.isChecked()) category = "Personal";
                else if (cbWork.isChecked()) category = "Work";
                else if (cbLearning.isChecked()) category = "Learning";

                TaskModel taskModel = new TaskModel("", taskName, "PENDING", FirebaseAuth.getInstance().getUid(), category);

                db.collection("tasks")
                        .add(taskModel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                                findViewById(R.id.successLayout).setVisibility(View.VISIBLE);
                                findViewById(R.id.progress).setVisibility(View.GONE);
                                findViewById(R.id.addTaskLayout).setVisibility(View.GONE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });







    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}