package nubank.com.br.nuchargeback.ui.chargeback;

import nubank.com.br.nuchargeback.model.Chargeback;

public interface ChargebackView {

    interface Key {
        String ID = "id";
        String TITLE = "title";
        String RESPONSE = "response";
    }

    void loadChargeback(Chargeback chargeback);
    void changeCardLock(boolean blocked);
    void openContestationDialog();
    void showChargebackError(String message);
    void showError(String message);
    void showLoading();
    void hideLoading();
    void showCardLoading();
    void hideCardLoading();
}
