
package demo.android.moneytap.com.moneytap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.android.moneytap.com.moneytap.R;
import demo.android.moneytap.com.moneytap.framework.response.GetContentData;
import demo.android.moneytap.com.moneytap.utils.GlideImageLoader;

public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.MyViewHolder>
        implements View.OnClickListener {

    private Context context;

    private ArrayList<GetContentData.Query.Page> mListData;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView lblTitleText;

        private TextView lblDescText;

        private ImageView profileImgView;

        public MyViewHolder(View view) {
            super(view);
            lblTitleText = (TextView) view.findViewById(R.id.lblTitleText);

            lblDescText = (TextView) view.findViewById(R.id.lblDescText);

            profileImgView = (ImageView) view.findViewById(R.id.profileImgView);
        }
    }

    public ListItemsAdapter(Context context, ArrayList<GetContentData.Query.Page> listData) {
        this.context = context;
        this.mListData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_list_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position ) {
        if (mListData == null || mListData.isEmpty()) {
            return;
        }
        GetContentData.Query.Page mData = mListData.get(position);

        if (mData == null) {
            return;
        }

        if (mData.getThumbnail() != null && mData.getThumbnail().getSource() != null) {
            GlideImageLoader.loadProfileImage(
                    context,
                    mData.getThumbnail().getSource(),
                    holder.profileImgView);
        } else {
            GlideImageLoader.loadProfileImage(
                    context,
                    "",
                    holder.profileImgView);
        }

        holder.lblTitleText.setText(mData.getTitle());

        if (mData.getTerms() != null && mData.getTerms().getDescription() != null
                && !mData.getTerms().getDescription().isEmpty()) {
            for (String s : mData.getTerms().getDescription()) {
                holder.lblDescText.setText(s + "\n");
            }
        } else {
            holder.lblDescText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    @Override
    public void onClick( View view ) {
        int position = (int) view.getTag();
    }
}
