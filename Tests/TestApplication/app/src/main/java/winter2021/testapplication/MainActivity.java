package winter2021.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        jsonParse();

//        mTextViewResult = findViewById(R.id.text_view_result);
//        Button buttonParse = findViewById(R.id.button_parse);
//
        mQueue = Volley.newRequestQueue(this);
//
//        buttonParse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jsonParse();
//            }
//        });
    }

    private void jsonParse() {

        String url = "https://api.myjson.com/bins/kp9wz";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("firstname");
                                int age = employee.getInt("age");
                                String mail = employee.getString("mail");

                                mTextViewResult.add(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    public void buttonAddaNewAccount(View view) {
        jsonParse();

        String result = "";
        for (int i = 0; i < mTextViewResult.size(); i ++ ) {
            result += mTextViewResult.get(i);
        }
        setContentsOfTextView(R.id.text_view_result, result);
    }
}