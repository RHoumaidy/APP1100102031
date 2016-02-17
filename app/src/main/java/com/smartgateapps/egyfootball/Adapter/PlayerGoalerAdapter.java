package com.smartgateapps.egyfootball.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartgateapps.egyfootball.R;
import com.smartgateapps.egyfootball.egy.MyApplication;
import com.smartgateapps.egyfootball.model.Player;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;

/**
 * Created by Raafat on 10/12/2015.
 */
public class PlayerGoalerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter {

    private List<Player> data;
    private Context ctx;
    private int res;
    private int hRes;
    private LayoutInflater inflater;
    private int lastPosition = -1;

    public PlayerGoalerAdapter(Context context, int resource,int hRes, List<Player> objects) {
        this.ctx = context;
        this.res = resource;
        this.data = objects;
        this.hRes = hRes;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public long getHeaderId(int position) {
        return data.get(position).getPos();
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = this.inflater.inflate(hRes,parent,false);

        return new  RecyclerView.ViewHolder(view){};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(data.get(position).getPos()>0){
            TextView playerPos = (TextView) holder.itemView.findViewById(R.id.matchDateTxtV);
            playerPos.setText(MyApplication.PLAYERS_POS[data.get(position).getPos()]);
            playerPos.setBackgroundColor(ctx.getResources().getColor(R.color.colorPrimary2));
        }
//
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(res,parent,false);

        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Player currPlayer = this.data.get(position);
//
        View view = holder.itemView;

        TextView goalsTxtV = (TextView) view.findViewById(R.id.playerGoalerGoalsTxtV);
        TextView teamTxtV = (TextView) view.findViewById(R.id.playerGoalerTeamTxtV);
        TextView nameTxtV = (TextView) view.findViewById(R.id.playerGoalerNameTxtV);
        LinearLayout relativeLayout  = (LinearLayout)view.findViewById(R.id.playerGoalerRelLyOut);


        if(position%2 == 0) {
            relativeLayout.setBackground(new ColorDrawable(ctx.getResources().getColor(R.color.listItemSelected)));
            relativeLayout.refreshDrawableState();
        }else{
            relativeLayout.setBackground(new ColorDrawable(ctx.getResources().getColor(android.R.color.white)));
            relativeLayout.refreshDrawableState();
        }

        if(currPlayer.getPos() ==0){

            goalsTxtV.setText(currPlayer.getGoals()+"");
            teamTxtV.setText(currPlayer.getTeamName());
            nameTxtV.setText(currPlayer.getPlayerName());

        }else{

            goalsTxtV.setText(Html.fromHtml(currPlayer.getNumber()));
            teamTxtV.setText(currPlayer.getMontakhab());
            nameTxtV.setText(Html.fromHtml(currPlayer.getPlayerName()));
        }
        setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(MyApplication.APP_CTX, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
