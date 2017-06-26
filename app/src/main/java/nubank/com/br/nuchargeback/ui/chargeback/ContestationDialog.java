package nubank.com.br.nuchargeback.ui.chargeback;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nubank.com.br.nuchargeback.R;

public class ContestationDialog extends DialogFragment implements View.OnClickListener {

    private static final String TITLE_KEY = "title_key";
    private static final String MESSAGE_KEY = "message_key";
    
    private String title;
    private String message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_contest, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        Button btnClose = (Button) view.findViewById(R.id.btn_close);

        tvTitle.setText(title);
        tvMessage.setText(message);

        btnClose.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, title);
        outState.putString(MESSAGE_KEY, message);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            this.title = savedInstanceState.getString(TITLE_KEY);
            this.message = savedInstanceState.getString(MESSAGE_KEY);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_close) {
            dismiss();
        }
    }

}
