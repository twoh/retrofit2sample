package id.web.twoh.retrofitsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import id.web.twoh.retrofitsample.api.TWOHAPIService;
import id.web.twoh.retrofitsample.api.UserAPIService;
import id.web.twoh.retrofitsample.model.Result;
import id.web.twoh.retrofitsample.util.Const;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private Retrofit twohRetro;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btGetAsModel = (Button) findViewById(R.id.bt_getasmodel);
        Button btGetAsJSON = (Button) findViewById(R.id.bt_getasjson);
        Button btGetAsXML = (Button) findViewById(R.id.bt_getasxml);
        Button btGetQuery = (Button) findViewById(R.id.bt_httpget);
        Button btPostForm = (Button) findViewById(R.id.bt_httppost);

        final EditText etFirstName = (EditText) findViewById(R.id.et_firstname);
        final EditText etLastName = (EditText) findViewById(R.id.et_lastname);

        final EditText etUsername = (EditText) findViewById(R.id.et_username);
        final EditText etMessage = (EditText) findViewById(R.id.et_message);
        final EditText etAge = (EditText) findViewById(R.id.et_age);
        final EditText etSex = (EditText) findViewById(R.id.et_sex);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setMessage("Loading");

        initializeRetrofit();
        btGetAsModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                getDataAsModel();
            }
        });
        btGetAsJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                getDataAsJSON();
            }
        });
        btGetQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> params = new HashMap<>();
                params.put("firstname", etFirstName.getText().toString());
                params.put("lastname", etLastName.getText().toString());
                queryJSON(params);
            }
        });
        btPostForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> params = new HashMap<>();
                params.put("username", etUsername.getText().toString());
                params.put("message", etMessage.getText().toString());
                params.put("sex", etSex.getText().toString());
                params.put("age", etAge.getText().toString());
                postMessage(params);
            }
        });

        btGetAsXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> params = new HashMap<>();
                params.put("firstname", etFirstName.getText().toString());
                params.put("lastname", etLastName.getText().toString());
                params.put("datatype", "xml");
                queryXML(params);
            }
        });

        setSupportActionBar(toolbar);
    }

    private void initializeRetrofit(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        twohRetro = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getDataAsModel(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<Result> result = apiService.getResultInfo();
        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                dialog.dismiss();
                try {
                    Toast.makeText(MainActivity.this," response version "+response.body().getInfo().getVersion()+"\n response seed " + response.body().getInfo().getSeed(),Toast.LENGTH_SHORT).show();
                    System.out.println("response output version " + response.body().getInfo().getVersion());
                    System.out.println("response output seed " + response.body().getInfo().getSeed());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void getDataAsJSON(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<ResponseBody> result = apiService.getResultAsJSON();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    Toast.makeText(MainActivity.this," response version "+response.body().string(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void queryJSON(HashMap<String, String> params){
        TWOHAPIService apiService = twohRetro.create(TWOHAPIService.class);
        Call<ResponseBody> result = apiService.getStoryOfMe(params);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    if(response.body()!=null)
                        Toast.makeText(MainActivity.this," response message "+response.body().string(),Toast.LENGTH_LONG).show();
                    if(response.errorBody()!=null)
                        Toast.makeText(MainActivity.this," response message "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void queryXML(HashMap<String, String> params){
        TWOHAPIService apiService = twohRetro.create(TWOHAPIService.class);
        Call<id.web.twoh.retrofitsample.model.Map> result = apiService.getStoryOfMeXML(params);
        result.enqueue(new Callback<id.web.twoh.retrofitsample.model.Map>() {
            @Override
            public void onResponse(Call<id.web.twoh.retrofitsample.model.Map> call, Response<id.web.twoh.retrofitsample.model.Map> response) {
                dialog.dismiss();
                try {
                    if(response.body()!=null)
                        Toast.makeText(MainActivity.this," response message "+response.body().getMessage(),Toast.LENGTH_LONG).show();
                    if(response.errorBody()!=null)
                        Toast.makeText(MainActivity.this," response message "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<id.web.twoh.retrofitsample.model.Map> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void postMessage(HashMap<String, String> params){
        TWOHAPIService apiService = twohRetro.create(TWOHAPIService.class);
        Call<ResponseBody> result = apiService.postMessage(params);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    if(response.body()!=null)
                        Toast.makeText(MainActivity.this," response message "+response.body().string(),Toast.LENGTH_LONG).show();
                    if(response.errorBody()!=null)
                        Toast.makeText(MainActivity.this," response message "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
