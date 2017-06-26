package nubank.com.br.nuchargeback.ui.chargeback;

import android.content.Context;

import javax.inject.Inject;

import nubank.com.br.nuchargeback.R;
import nubank.com.br.nuchargeback.app.AppUtils;
import nubank.com.br.nuchargeback.app.NuChargebackApplication;
import nubank.com.br.nuchargeback.model.Chargeback;
import nubank.com.br.nuchargeback.model.ChargebackResponse;
import nubank.com.br.nuchargeback.model.Contestation;
import nubank.com.br.nuchargeback.network.NuApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChargebackPresenterImpl implements ChargebackPresenter {

    private static final String OK = "Ok";

    @Inject
    NuApi nuApi;

    @Inject
    Context context;

    private boolean cardLocked;
    private ChargebackView view;


    public ChargebackPresenterImpl(Context context) {
        ((NuChargebackApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(ChargebackView view) {
        this.view = view;
        this.cardLocked = false;
    }

    @Override
    public void getChargeback() {
        boolean isConnected = AppUtils.isNetworkAvailable(context);
        if (isConnected) {
            this.view.showLoading();
            this.nuApi.getChargeback().enqueue(new Callback<Chargeback>() {
                @Override
                public void onResponse(Call<Chargeback> call, Response<Chargeback> response) {
                    Chargeback chargeback = response.body();
                    if (chargeback != null) {
                        view.loadChargeback(chargeback);
                    } else {
                        view.showChargebackError(context.getString(R.string.snackbar_data_not_received));
                    }
                    view.hideLoading();
                }

                @Override
                public void onFailure(Call<Chargeback> call, Throwable t) {
                    view.hideLoading();
                    view.showChargebackError(context.getString(R.string.snackbar_data_not_received));
                }
            });
        } else {
            this.view.showChargebackError(context.getString(R.string.snackbar_connection_failed));
        }
    }

    @Override
    public void toggleCardLock() {
        boolean isConnected = AppUtils.isNetworkAvailable(context);
        if (isConnected) {
            this.view.showCardLoading();
            if (cardLocked) {
                unblockCard();
            } else {
                blockCard();
            }
        } else {
            this.view.showError(context.getString(R.string.snackbar_connection_failed));
        }
    }

    @Override
    public void submitChargeback(Contestation contestation) {
        boolean isConnected = AppUtils.isNetworkAvailable(context);
        if (isConnected) {
            this.nuApi.submitChargeback(contestation).enqueue(new Callback<ChargebackResponse>() {
                @Override
                public void onResponse(Call<ChargebackResponse> call, Response<ChargebackResponse> response) {
                    if (isValidResponse(response)) {
                        view.openContestationDialog();
                    } else {
                        view.showError(context.getString(R.string.snackbar_data_not_sent));
                    }
                }

                @Override
                public void onFailure(Call<ChargebackResponse> call, Throwable t) {
                    view.showError(context.getString(R.string.snackbar_data_not_sent));
                }
            });
        } else {
            this.view.showError(context.getString(R.string.snackbar_connection_failed));
        }
    }

    private void blockCard() {
        this.nuApi.blockCard().enqueue(new Callback<ChargebackResponse>() {
            @Override
            public void onResponse(Call<ChargebackResponse> call, Response<ChargebackResponse> response) {
                if (isValidResponse(response)) {
                    cardLocked = true;
                    view.changeCardLock(true);
                } else {
                    view.showError(context.getString(R.string.snackbar_card_lock_failed));
                }
                view.hideCardLoading();
            }

            @Override
            public void onFailure(Call<ChargebackResponse> call, Throwable t) {
                view.hideCardLoading();
                view.showError(context.getString(R.string.snackbar_card_lock_failed));
            }
        });
    }

    private void unblockCard() {
        this.nuApi.unblockCard().enqueue(new Callback<ChargebackResponse>() {
            @Override
            public void onResponse(Call<ChargebackResponse> call, Response<ChargebackResponse> response) {
                if (isValidResponse(response)) {
                    cardLocked = false;
                    view.changeCardLock(false);
                } else {
                    view.showError(context.getString(R.string.snackbar_card_lock_failed));
                }
                view.hideCardLoading();
            }

            @Override
            public void onFailure(Call<ChargebackResponse> call, Throwable t) {
                view.hideCardLoading();
                view.showError(context.getString(R.string.snackbar_card_lock_failed));
            }
        });
    }

    private boolean isValidResponse(Response<ChargebackResponse> response) {
        if (response.isSuccessful()) {
            ChargebackResponse chargebackResponse = response.body();
            if (chargebackResponse.getStatus().equals(OK)) {
                return true;
            }
        }
        return false;
    }

}
