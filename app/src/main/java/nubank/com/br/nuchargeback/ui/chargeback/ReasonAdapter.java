package nubank.com.br.nuchargeback.ui.chargeback;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nubank.com.br.nuchargeback.R;

public class ReasonAdapter extends RecyclerView.Adapter<ReasonViewHolder> {

    private List<HashMap<String,String>> reasonsList = new ArrayList<>();
    private Context context;
    private SwitchToggleListener listener;

    @Override
    public ReasonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_reason, parent, false);
        return new ReasonViewHolder(view);
    }

    public void setListener(SwitchToggleListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final ReasonViewHolder holder, final int position) {
        HashMap<String, String> reason = reasonsList.get(position);

        holder.tvTitle.setText(reason.get("title"));
        holder.swReason.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.textGreen));
                    holder.swReason.setBackColorRes(R.color.textGreen);
                } else {
                    holder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.textBlack));
                    holder.swReason.setBackColorRes(R.color.btnDisabledGray);
                }
                listener.onSwitchToggle(isChecked, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.reasonsList.size();
    }

    @Override
    public void onViewRecycled(ReasonViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public void setData(List<HashMap<String,String>> items) {
        this.reasonsList = items;
    }

}
