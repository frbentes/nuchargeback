package nubank.com.br.nuchargeback.dagger;

import javax.inject.Singleton;

import dagger.Component;
import nubank.com.br.nuchargeback.ui.chargeback.ChargebackActivity;
import nubank.com.br.nuchargeback.ui.chargeback.ChargebackPresenterImpl;
import nubank.com.br.nuchargeback.ui.notice.NoticeActivity;
import nubank.com.br.nuchargeback.ui.notice.NoticePresenterImpl;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(NoticeActivity target);
    void inject(ChargebackActivity target);
    void inject(NoticePresenterImpl target);
    void inject(ChargebackPresenterImpl target);
}
