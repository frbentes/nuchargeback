package nubank.com.br.nuchargeback.ui.notice;

import android.content.Context;

import javax.inject.Inject;

import nubank.com.br.nuchargeback.R;
import nubank.com.br.nuchargeback.app.AppUtils;
import nubank.com.br.nuchargeback.app.NuChargebackApplication;
import nubank.com.br.nuchargeback.model.Notice;
import nubank.com.br.nuchargeback.network.NuApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticePresenterImpl implements NoticePresenter {

    @Inject
    NuApi nuApi;

    @Inject
    Context context;

    private NoticeView view;

    public NoticePresenterImpl(Context context) {
        ((NuChargebackApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(NoticeView view) {
        this.view = view;
    }

    @Override
    public void getNotice() {
        boolean isConnected = AppUtils.isNetworkAvailable(context);
        if (isConnected) {
            this.view.showLoading();
            this.nuApi.getNotice().enqueue(new Callback<Notice>() {
                @Override
                public void onResponse(Call<Notice> call, Response<Notice> response) {
                    Notice notice = response.body();
                    if (notice != null) {
                        view.showNotice(notice);
                    } else {
                        view.showError(context.getString(R.string.snackbar_data_not_received));
                    }
                    view.hideLoading();
                }

                @Override
                public void onFailure(Call<Notice> call, Throwable t) {
                    view.hideLoading();
                    view.showError(context.getString(R.string.snackbar_data_not_received));
                }
            });
        } else {
            this.view.showError(context.getString(R.string.snackbar_connection_failed));
        }
    }

}
