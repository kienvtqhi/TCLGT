package com.kienvt.tclgt.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
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

        offencesViewHolder.tvDetail.setText(offences.name);
        offencesViewHolder.tvMoney.setText(Html.fromHtml(offences.description));

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_black_36dp);
        drawable = DrawableCompat.wrap(drawable);
        if (offences.bookmark == 1) {
            // Red color
            DrawableCompat.setTint(drawable, ContextCompat.getColor(mContext, R.color.red));
        } else {
            DrawableCompat.setTint(drawable, Color.BLACK);
        }

        offencesViewHolder.btnFavorite.setImageDrawable(drawable);
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
            MOffence offence = mOffences.get(getAdapterPosition());
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                    .setTitle("Thông Tin")
                    .setMessage(offence.law)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        }

        @OnClick(R.id.btn_favorite)
        public void onClickButtonFavorite() {
            MOffence offence = mOffences.get(getAdapterPosition());
            if (offence.bookmark == 1) {
                offence.bookmark = 0;
            } else {
                offence.bookmark = 1;
            }
            offence.save();
            notifyItemChanged(getAdapterPosition());
        }

        @OnClick(R.id.btn_share)
        public void onClickButtonShare() {
            MOffence offence = mOffences.get(getAdapterPosition());
            String content = offence.name + "\n\n" + Html.fromHtml(offence.description);

            Intent intent = ShareCompat.IntentBuilder.from((Activity) mContext)
                    .setType("text/plain")
                    .setChooserTitle("Chia sẻ")
                    .setText(content)
                    .createChooserIntent();
            mContext.startActivity(intent);
        }
    }
}
