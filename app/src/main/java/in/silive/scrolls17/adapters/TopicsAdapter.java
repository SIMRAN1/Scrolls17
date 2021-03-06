package in.silive.scrolls17.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import in.silive.scrolls17.R;

/**
 * Created by AKG002 on 03-09-2016.
 */
public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder>{
    String[] topics;
    Context context;
    Integer[]   images;
    private int lastPosition;

    public TopicsAdapter(Context context, String[] topics,Integer[] images) {
        this.context = context;
        this.topics = topics;
        this.images=images;
    }

    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tv,parent,false));
    }

    @Override
    public void onBindViewHolder(final TopicsAdapter.ViewHolder holder, final int position) {
        holder.tvTopic.setText(topics[position]);
        holder.itemView.setVisibility(View.INVISIBLE);
        holder.itemView.setVisibility(View.VISIBLE);
        holder.tvNum.setImageResource(images[position]);
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                holder.itemView.setVisibility(View.VISIBLE);
                setAnimation(holder.itemView,position);
            }
        }, (int)(10+50*position));*/

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return topics.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTopic;
        ImageView tvNum;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTopic = (TextView)itemView.findViewById(R.id.tv);
            tvNum = (ImageView)itemView.findViewById(R.id.tvNu);
        }
    }
}
