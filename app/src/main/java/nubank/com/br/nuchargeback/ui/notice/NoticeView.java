package nubank.com.br.nuchargeback.ui.notice;

import nubank.com.br.nuchargeback.model.Notice;

public interface NoticeView {
    void showNotice(Notice notice);
    void showError(String message);
    void showLoading();
    void hideLoading();
}
