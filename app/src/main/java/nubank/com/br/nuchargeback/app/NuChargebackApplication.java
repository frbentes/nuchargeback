package nubank.com.br.nuchargeback.app;

import android.app.Application;

import nubank.com.br.nuchargeback.dagger.AppComponent;
import nubank.com.br.nuchargeback.dagger.AppModule;
import nubank.com.br.nuchargeback.dagger.DaggerAppComponent;

public class NuChargebackApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = initDagger(this);
    }

    protected AppComponent initDagger(NuChargebackApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
