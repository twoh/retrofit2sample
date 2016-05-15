package id.web.twoh.retrofitsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import id.web.twoh.retrofitsample.api.RetrofitAPIEndpointInterface;
import id.web.twoh.retrofitsample.model.Result;
import id.web.twoh.retrofitsample.util.Const;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btGetAsModel = (Button) findViewById(R.id.bt_getasmodel);
        Button btGetAsJSON = (Button) findViewById(R.id.bt_getasjson);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
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
        setSupportActionBar(toolbar);
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getDataAsModel(){
        RetrofitAPIEndpointInterface apiService = retrofit.create(RetrofitAPIEndpointInterface.class);
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
        RetrofitAPIEndpointInterface apiService = retrofit.create(RetrofitAPIEndpointInterface.class);
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
