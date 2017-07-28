package xyz.aungpyaephyo.ma.ac.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.aungpyaephyo.ma.ac.network.responses.AttractionListResponse;
import xyz.aungpyaephyo.ma.ac.utils.AppConstants;

/**
 * Created by aung on 7/9/16.
 */
public interface AttractionApi {

    @FormUrlEncoded
    @POST(AppConstants.API_GET_ATTRACTION_LIST)
    Call<AttractionListResponse> loadAttractions(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String param);
}
