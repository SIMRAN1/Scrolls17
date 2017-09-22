package in.silive.scrolls16.Services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import in.silive.scrolls16.Adapters.SpinnerAdapter;
import in.silive.scrolls16.Listeners.FetchDataListener;
import in.silive.scrolls16.Models.Datum;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
import in.silive.scrolls16.Network.FetchData;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.application.Scrolls;
import in.silive.scrolls16.models.LoginSucess;
import in.silive.scrolls16.models.TopicModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Shobhit-pc on 2/16/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    //    parkonmineparking static final String Authorization_Token = "Authorization_Token";
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    SharedPreferences pref;
    private RetrofitApiInterface apiService;
    private Call<LoginSucess> call;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
         pref=Scrolls.getInstance().sharedPrefs;
        System.out.println("refreshedToken" + refreshedToken);
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {

       // String authorization_token = DirectMe.getInstance().sharedPrefs.getString(SyncStateContract.Constants.AUTH_TOKEN, "");

        // checking if auth token is saved or not
        /*if (authorization_token.equals(""))
            return;*/
      /*  call = apiService.Fcm(token);
        if (CheckConnectivity.isNetConnected(getApplicationContext())) {
            final ProgressDialog loading = ProgressDialog.show(getApplicationContext(), "Fetching Data", "Please wait...", false, false);
            call.enqueue(new Callback<LoginSucess>() {
                @Override
                public void onResponse(Call<LoginSucess> call, Response<LoginSucess> response) {
                    if (response.isSuccessful()) {

                        pref =Scrolls.getInstance().sharedPrefs;
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("FirebaseIdSendToServer", "1");//1 means firebase id is registered
                        editor.apply();

                        loading.dismiss();

                        //Log.d("debugg",Integer.toString(topicsList1.size()));


                    }
                }

                @Override
                public void onFailure(Call<LoginSucess> call, Throwable t) {
                    loading.dismiss();
                }


            });

        }*/






    }

    private void storeRegIdInPref(String token) {
        pref = Scrolls.getInstance().sharedPrefs;
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.putString("FirebaseIdSendToServer", "0");//0 means firebase id is not updated to server
        editor.apply();
    }
}