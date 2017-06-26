package nubank.com.br.nuchargeback.ui.chargeback;

import nubank.com.br.nuchargeback.model.Contestation;

public interface ChargebackPresenter {
    void setView(ChargebackView view);
    void getChargeback();
    void toggleCardLock();
    void submitChargeback(Contestation contestation);
}
