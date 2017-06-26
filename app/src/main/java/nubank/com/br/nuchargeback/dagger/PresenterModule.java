package nubank.com.br.nuchargeback.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nubank.com.br.nuchargeback.ui.chargeback.ChargebackPresenter;
import nubank.com.br.nuchargeback.ui.chargeback.ChargebackPresenterImpl;
import nubank.com.br.nuchargeback.ui.notice.NoticePresenter;
import nubank.com.br.nuchargeback.ui.notice.NoticePresenterImpl;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    NoticePresenter provideNoticePresenter(Context context) {
        return new NoticePresenterImpl(context);
    }

    @Provides
    @Singleton
    ChargebackPresenter provideChargebackPresenter(Context context) {
        return new ChargebackPresenterImpl(context);
    }

}
