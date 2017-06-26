package nubank.com.br.nuchargeback.network;

import nubank.com.br.nuchargeback.model.Chargeback;
import nubank.com.br.nuchargeback.model.ChargebackResponse;
import nubank.com.br.nuchargeback.model.Notice;
import nubank.com.br.nuchargeback.model.Contestation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NuApi {

    @GET("notice")
    Call<Notice> getNotice();

    @GET("chargeback")
    Call<Chargeback> getChargeback();

    @POST("card_block")
    Call<ChargebackResponse> blockCard();

    @POST("card_unblock")
    Call<ChargebackResponse> unblockCard();

    @POST("chargeback")
    Call<ChargebackResponse> submitChargeback(@Body Contestation contestation);

}
