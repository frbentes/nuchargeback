package nubank.com.br.nuchargeback.ui.chargeback;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pixplicity.htmlcompat.HtmlCompat;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import nubank.com.br.nuchargeback.R;
import nubank.com.br.nuchargeback.app.NuChargebackApplication;
import nubank.com.br.nuchargeback.model.Chargeback;
import nubank.com.br.nuchargeback.model.Contestation;

public class ChargebackActivity extends AppCompatActivity implements ChargebackView,
        SwitchToggleListener {

    public static final double OFFSET = 0.4;
    public static final String FALSE = "false";
    public static final String TAG_DIALOG = "ContestationDialog";

    @Inject
    ChargebackPresenter presenter;

    @BindView(R.id.rl_main)
    RelativeLayout rlMain;

    @BindView(R.id.ll_header)
    LinearLayout llHeader;

    @BindView(R.id.ll_info)
    LinearLayout llInfo;

    @BindView(R.id.tv_chargeback_title)
    TextView tvTitle;

    @BindView(R.id.tv_error_message)
    TextView tvError;

    @BindView(R.id.pb_card)
    ProgressBar pbCard;

    @BindView(R.id.btn_card_lock)
    Button btnCardLock;

    @BindView(R.id.rv_reasons)
    RecyclerView rvReasons;

    @BindView(R.id.edt_comment)
    EditText edtComment;

    @BindView(R.id.btn_contest)
    Button btnContest;

    @BindView(R.id.pb_chargeback)
    ProgressBar pbChargeback;

    private Contestation contestation;
    private ReasonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargeback);

        ((NuChargebackApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        this.btnCardLock.setOnClickListener(clickCardLock);
        setupTextEdition();
        setupRecyclerView();
        this.contestation = new Contestation();

        this.presenter.setView(this);
        this.presenter.getChargeback();
    }

    @Override
    public void loadChargeback(Chargeback chargeback) {
        this.tvTitle.setText(chargeback.getTitle());
        this.tvError.setVisibility(View.GONE);
        Spanned fromHtml = HtmlCompat.fromHtml(this, chargeback.getCommentHint(), 0);
        this.edtComment.setMovementMethod(LinkMovementMethod.getInstance());
        this.edtComment.setHint(fromHtml);

        this.contestation.setReasons(new ArrayList<HashMap<String, String>>());
        int count = chargeback.getReasons().size();
        if (count > 0) {
            addReasons(chargeback);
            this.adapter.setData(contestation.getReasons());
            this.adapter.notifyDataSetChanged();
            this.rvReasons.setVisibility(View.VISIBLE);
        } else {
            this.rvReasons.setVisibility(View.GONE);
        }

        this.llHeader.setVisibility(View.VISIBLE);
        this.llInfo.setVisibility(View.VISIBLE);

        // locks the card when screen opens
        this.presenter.toggleCardLock();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(rlMain, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showChargebackError(String message) {
        this.tvTitle.setText(R.string.error_title);
        this.tvError.setText(message);
        this.tvError.setVisibility(View.VISIBLE);
        this.llHeader.setVisibility(View.VISIBLE);
        this.llInfo.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        this.pbChargeback.setVisibility(View.VISIBLE);
        this.llHeader.setVisibility(View.GONE);
        this.llInfo.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        this.pbChargeback.setVisibility(View.GONE);
    }

    @Override
    public void showCardLoading() {
        this.pbCard.setVisibility(View.VISIBLE);
        this.btnCardLock.setVisibility(View.GONE);
    }

    @Override
    public void hideCardLoading() {
        this.pbCard.setVisibility(View.GONE);
        this.btnCardLock.setVisibility(View.VISIBLE);
    }

    @Override
    public void openContestationDialog() {
        ContestationDialog dialog = new ContestationDialog();
        dialog.setTitle(getString(R.string.dialog_title));
        dialog.setMessage(getString(R.string.dialog_message));
        dialog.show(getFragmentManager(), TAG_DIALOG);
        this.btnContest.setEnabled(false);
        this.btnContest.setTextColor(ContextCompat.getColor(getApplicationContext(),
                R.color.btnDisabledGray));
        this.edtComment.setText("");
    }

    @Override
    public void changeCardLock(boolean blocked) {
        Drawable drawable;
        String message;

        if (blocked) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_chargeback_lock);
            message = getResources().getString(R.string.card_lock);
        } else {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_chargeback_unlock);
            message = getResources().getString(R.string.card_unlock);
        }

        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * OFFSET), (int)
                (drawable.getIntrinsicHeight() * OFFSET));
        this.btnCardLock.setCompoundDrawables(drawable, null, null, null);
        this.btnCardLock.setText(message);
    }

    @Override
    public void onSwitchToggle(boolean checked, int position) {
        this.contestation.getReasons().get(position).put(Key.RESPONSE, String.valueOf(checked));
    }

    private void setupTextEdition() {
        this.edtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int color;
                if (edtComment.getText().length() > 0) {
                    btnContest.setEnabled(true);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.btnEnabledPurple);
                } else {
                    btnContest.setEnabled(false);
                    color = ContextCompat.getColor(getApplicationContext(), R.color.btnDisabledGray);
                }
                btnContest.setTextColor(color);
            }
        });
    }

    private void setupRecyclerView() {
        this.adapter = new ReasonAdapter();
        final LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        this.rvReasons.setLayoutManager(llm);
        this.rvReasons.setAdapter(adapter);
        this.adapter.setListener(this);
    }

    private void addReasons(Chargeback chargeback) {
        for (int i = 0; i < chargeback.getReasons().size(); i++) {
            HashMap<String, String> reason = new HashMap<>();
            reason.put(Key.ID, chargeback.getReasons().get(i).get(Key.ID));
            reason.put(Key.TITLE, chargeback.getReasons().get(i).get(Key.TITLE));
            reason.put(Key.RESPONSE, FALSE);
            this.contestation.getReasons().add(reason);
        }
    }

    private final View.OnClickListener clickCardLock = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.toggleCardLock();
        }
    };

    public void onContestClicked(View view) {
        this.contestation.setComment(edtComment.getText().toString());
        this.presenter.submitChargeback(contestation);
    }

    public void onCancelClicked(View view) {
        finish();
    }

}
