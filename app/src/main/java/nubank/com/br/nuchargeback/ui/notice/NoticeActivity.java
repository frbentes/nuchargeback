package nubank.com.br.nuchargeback.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pixplicity.htmlcompat.HtmlCompat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import nubank.com.br.nuchargeback.R;
import nubank.com.br.nuchargeback.app.NuChargebackApplication;
import nubank.com.br.nuchargeback.model.Notice;
import nubank.com.br.nuchargeback.ui.chargeback.ChargebackActivity;

public class NoticeActivity extends AppCompatActivity implements NoticeView {

    @Inject
    NoticePresenter presenter;

    @BindView(R.id.rl_notice)
    RelativeLayout rlNotice;

    @BindView(R.id.tv_notice_title)
    TextView tvTitle;

    @BindView(R.id.tv_notice_description)
    TextView tvDescription;

    @BindView(R.id.btn_primary)
    Button btnContinue;

    @BindView(R.id.btn_secondary)
    Button btnClose;

    @BindView(R.id.pb_notice)
    ProgressBar pbNotice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ((NuChargebackApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        this.presenter.setView(this);
        this.presenter.getNotice();
    }

    @Override
    public void showNotice(Notice notice) {
        this.tvTitle.setText(notice.getTitle());
        Spanned fromHtml = HtmlCompat.fromHtml(this, notice.getDescription(), 0);
        this.tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
        this.tvDescription.setText(fromHtml);
        this.btnContinue.setText(notice.getPrimaryAction().getTitle());
        this.btnClose.setText(notice.getSecondaryAction().getTitle());
        this.btnContinue.setTextColor(ContextCompat.getColor(getApplicationContext(),
                R.color.btnEnabledPurple));
        this.btnContinue.setEnabled(true);
    }

    @Override
    public void showError(String message) {
        this.tvTitle.setText(R.string.error_title);
        this.tvDescription.setText(message);
        this.btnContinue.setText(R.string.button_continue);
        this.btnContinue.setTextColor(ContextCompat.getColor(getApplicationContext(),
                R.color.btnDisabledGray));
        this.btnContinue.setEnabled(false);
    }

    @Override
    public void showLoading() {
        this.pbNotice.setVisibility(View.VISIBLE);
        this.tvDescription.setVisibility(View.GONE);
        this.btnContinue.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        this.pbNotice.setVisibility(View.GONE);
        this.tvDescription.setVisibility(View.VISIBLE);
        this.btnContinue.setVisibility(View.VISIBLE);
    }

    public void onContinueClicked(View view){
        Intent intent = new Intent(this, ChargebackActivity.class);
        startActivity(intent);
    }

    public void onCloseClicked(View view){
        finish();
    }

}
