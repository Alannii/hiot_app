package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huatec.hiot_cloud.R;

import java.lang.reflect.Type;

public class Student2Activity extends AppCompatActivity {
    private static final String TAG = "Student2Activity";

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student2);

        Button button1 = findViewById(R.id.btn_from_student2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type type = new TypeToken<ResultBase<Student2>>(){}.getType();
                ResultBase<Student2> resultBase = gson.fromJson("{\n" +
                        "\t\"data\":\n" +
                        "\t{\n" +
                        "\t\t\"id\": 101,\n" +
                        "\t\t\"graduated\": true,\n" +
                        "\t\t\"height\":180,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t\"status\":1,\n" +
                        "\t\"msg\":\"\"\n" +
                        "}",type);

                String str = String.format("姓名：%s，id：%d，身高：%d，是否毕业：%b",resultBase.getData().getName(),resultBase.getData().getId(),resultBase.getData().getHeight(),resultBase.getData().isGraduated());
                Toast.makeText(Student2Activity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = findViewById(R.id.btn_to_student2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Student2 student2 = new Student2();
               student2.setName("李四");
               student2.setHeight(175);
               student2.setId(101);
               student2.setGraduated(true);
                Log.d(TAG,gson.toJson(student2));
            }
        });
    }
}
