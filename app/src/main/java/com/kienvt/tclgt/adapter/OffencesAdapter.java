package com.kienvt.tclgt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.kienvt.tclgt.R;
import com.kienvt.tclgt.models.MOffence;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by johnvi on 1/24/16.
 */
public class OffencesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MOffence> mOffences;

    public OffencesAdapter(Context context, List<MOffence> offences) {
        super();
        this.mContext = context;
        this.mOffences = offences;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);

        OffencesViewHolder offencesViewHolder = new OffencesViewHolder(view);
        return offencesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OffencesViewHolder offencesViewHolder = (OffencesViewHolder) holder;

        MOffence offences = mOffences.get(position);

        offencesViewHolder.tvDetail.setText(offences.detail);
        offencesViewHolder.tvMoney.setText(Html.fromHtml(offences.money));
    }

    @Override
    public int getItemCount() {
        return mOffences.size();
    }

    public class OffencesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_detail) TextView tvDetail;
        @Bind(R.id.tv_money) TextView tvMoney;
        @Bind(R.id.btn_info) ImageButton btnInfo;
        @Bind(R.id.btn_favorite) ImageButton btnFavorite;
        @Bind(R.id.btn_share) ImageButton btnShare;

        public OffencesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn_info)
        public void onClickButtonInfo() {
            int position = getAdapterPosition();
            MOffence offence = mOffences.get(position);
            new Delete().from(MOffence.class).where("detail = ?", offence.detail).execute();
            notifyItemRemoved(position);
        }

        @OnClick(R.id.btn_favorite)
        public void onClickButtonFavorite() {
            int position = getAdapterPosition();
            MOffence offence = mOffences.get(position);
            offence.detail = offence.detail + " Favorite";
            offence.save();

            notifyItemChanged(position);
        }
    }
}
