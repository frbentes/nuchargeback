package nubank.com.br.nuchargeback.ui.chargeback;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import nubank.com.br.nuchargeback.R;

public class ReasonViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public SwitchButton swReason;

    public ReasonViewHolder(View view) {
        super(view);

        this.tvTitle = (TextView) view.findViewById(R.id.tv_reason_title);
        this.swReason = (SwitchButton) view.findViewById(R.id.sw_reason);
    }

}
