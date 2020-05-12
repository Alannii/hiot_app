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
import java.util.List;
import java.util.Map;

public class TestGsonActivity extends AppCompatActivity {

    public static final String TAG = "TestGsonActivity";
    Gson gson = new Gson();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gson);

        //json转对象
        Button btnFromJson = findViewById(R.id.btn_from_json);
        btnFromJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = gson.fromJson("\n" +
                        "{\n" +
                        "\t\"age\": 20,\n" +
                        "\t\"married\": false,\n" +
                        "\t\"name\": \"张三\"\n" +
                        "}",Student.class);

                if(student!=null){
                String str = String.format("姓名:%s,年龄:%d,婚否：%b",student.getName(),student.getAge(),student.isMarried());
                Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
            }
            }
        });

        //对象转json
        Button btnToJson = findViewById(R.id.btn_to_json);
        btnToJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student2 = new Student();
                student2.setName("余");
                student2.setAge(20);
                student2.setMarried(false);
                String json = gson.toJson(student2);
                Log.d(TAG, "onClick: "+json);
            }
        });

        //json转列表
        Button btnJsonList = findViewById(R.id.btn_gson_list);
        btnJsonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type type = new TypeToken<List<Student>>(){}.getType();
                List<Student> studentList = gson.fromJson("[\n" +
                        "\t{\n" +
                        "\t\t\"age\": 20,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"age\": 21,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"李四\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"age\": 22,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"王五\"\n" +
                        "\t}\n" +
                        "]",type);

                if(studentList != null || !studentList.isEmpty()){
                    for (Student student:studentList){
                        String str = String.format("姓名:%s,年龄:%d,婚否：%b",student.getName(),student.getAge(),student.isMarried());
                        Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //json转map
        Button btnJsonMap = findViewById(R.id.btn_gson_map);
        btnJsonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type type = new TypeToken<Map<String,Student>>(){}.getType();
                Map<String,Student> studentMap = gson.fromJson("{\n" +
                        "\t\"1\":{\n" +
                        "\t\t\"age\": 20,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t\"2\":{\n" +
                        "\t\t\"age\": 21,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"李四\"\n" +
                        "\t},\n" +
                        "\t\"3\":{\n" +
                        "\t\t\"age\": 22,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"王五\"\n" +
                        "\t}\n" +
                        "}\n",type);

                if(studentMap != null || !studentMap.isEmpty()){
                    for (String key : studentMap.keySet()){
                        String str = String.format("姓名:%s,年龄:%d,婚否：%b",studentMap.get(key).getName(),studentMap.get(key).getAge(),studentMap.get(key).isMarried());
                        Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //json转嵌套对象
        Button btnJsonNest = findViewById(R.id.btn_gson_nest);
        btnJsonNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type type = new TypeToken<ResultBase<Student>>(){}.getType();
                ResultBase<Student> resultBase = gson.fromJson("{\n" +
                        "\t\"data\":\n" +
                        "\t{\n" +
                        "\t\t\"age\": 20,\n" +
                        "\t\t\"married\": false,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t\"status\":1,\n" +
                        "\t\"msg\":\"正常\"\n" +
                        "}\n",type);

                if(resultBase != null){
                    String str = String.format("姓名:%s,年龄:%d,婚否：%b,响应状态：%d,消息：%s"
                            ,resultBase.getData().getName(),resultBase.getData().getAge(),resultBase.getData().isMarried()
                            ,resultBase.getStatus(),resultBase.getMsg());
                    Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
