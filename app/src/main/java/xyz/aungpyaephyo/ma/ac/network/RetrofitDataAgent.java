package xyz.aungpyaephyo.ma.ac.network;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.aungpyaephyo.ma.ac.events.DataEvent;
import xyz.aungpyaephyo.ma.ac.network.responses.AttractionListResponse;
import xyz.aungpyaephyo.ma.ac.utils.AppConstants;

/**
 * Created by aung on 7/9/16.
 */
public class RetrofitDataAgent implements AttractionDataAgent {

    private static RetrofitDataAgent objInstance;

    private final AttractionApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(AttractionApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadAttractions() {
        Call<AttractionListResponse> loadAttractionCall = theApi.loadAttractions(AppConstants.ACCESS_TOKEN);
        loadAttractionCall.enqueue(new Callback<AttractionListResponse>() {
            @Override
            public void onResponse(Call<AttractionListResponse> call, Response<AttractionListResponse> response) {
                AttractionListResponse attractionListResponse = response.body();
                if (attractionListResponse == null) {
                    EventBus.getDefault().post(new DataEvent.AttractionsLoadedEmptyEvent());
                } else {
                    EventBus.getDefault().post(new DataEvent.AttractionsLoadedEvent(attractionListResponse.getAttractionList()));
                }
            }

            @Override
            public void onFailure(Call<AttractionListResponse> call, Throwable throwable) {
                EventBus.getDefault().post(new DataEvent.AttractionsLoadedErrorEvent(throwable.getMessage()));
            }
        });
    }
}
