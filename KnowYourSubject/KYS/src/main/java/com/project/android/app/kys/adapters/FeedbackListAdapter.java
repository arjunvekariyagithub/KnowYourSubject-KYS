package com.project.android.app.kys.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.android.app.kys.R;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.helper.Type.UserType;
import com.project.android.app.kys.helper.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FeedbackListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Feedback> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;

    public FeedbackListAdapter(Context context, List<Feedback> data) {
        Log.d("ARJUN", "FeedbackListAdapter() called with size : " + data.size());
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ARJUN", "FeedbackListAdapter() - onCreateViewHolder()");
        View view = inflater.inflate(R.layout.feedback_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("ARJUN", "FeedbackListAdapter() - onBindViewHolder() postion : " + position);
        createDepartmentRow((ViewHolder) holder, position);
    }

    private void createDepartmentRow(ViewHolder holder, int position) {
        Feedback curFeedback = data.get(position);
        holder.itemView.setTag(curFeedback);
        holder.title.setText(curFeedback.getTitle());
        holder.initials.setText(Util.getInitials(curFeedback.getCourseName()));
        holder.comment.setText(curFeedback.getComment());
        holder.userName.setText(curFeedback.getUserName());
        holder.rating.setRating(curFeedback.getRating());

        long timestamp = Long.parseLong(curFeedback.getDate());
        holder.date.setText(getDate(timestamp));
        holder.coursename.setText(curFeedback.getCourseName());
        holder.profName.setText(curFeedback.getProfessorName());

        handleUserSpecificViews(holder);
    }


    private String getDate(long timeStamp){

        try{
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private void handleUserSpecificViews(ViewHolder holder) {
        if (UserType.isAdmin()) {
            holder.adminPanel.setVisibility(View.VISIBLE);
            holder.loginUserPanel.setVisibility(View.INVISIBLE);

            holder.mIgnoreSpamFeedBackButton.setTag(holder.itemView);
            holder.mDeleteSpamFeedBackButton.setTag(holder.itemView);
            setIgnoreSpamFeedbackClickListener(holder.mIgnoreSpamFeedBackButton);
            setDeleteSpamFeedbackClickListener(holder.mDeleteSpamFeedBackButton);

        } else if (UserType.isLoginUser()) {
            holder.adminPanel.setVisibility(View.INVISIBLE);
            holder.loginUserPanel.setVisibility(View.VISIBLE);

            holder.mMarkFeedBackButton.setTag(holder.itemView);
            setMarkFeedbackClickListener(holder.mMarkFeedBackButton);
        } else {
            holder.adminPanel.setVisibility(View.INVISIBLE);
            holder.loginUserPanel.setVisibility(View.INVISIBLE);
        }
    }

    private void setIgnoreSpamFeedbackClickListener(ImageView ignoreButton) {
        ignoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (view.getTag() == null) return;

                if (view.getTag() instanceof Feedback) {
                    Feedback fb = (Feedback) view.getTag();
                    Toast.makeText(mContext, "Ignored " + fb.getUserName() + "'s feedback", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void setDeleteSpamFeedbackClickListener(ImageView deleteButton) {
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(view.getTag() == null) return;

                if(view.getTag() instanceof Feedback) {
                    Feedback fb = (Feedback)view.getTag();
                    Toast.makeText(mContext, "Deleted " + fb.getUserName() + "'s feedback" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setMarkFeedbackClickListener(ImageView markFedbackButton) {
        markFedbackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(view.getTag() == null) return;

                if(view.getTag() instanceof Feedback) {
                    Feedback fb = (Feedback)view.getTag();
                    Toast.makeText(mContext, "Marked " + fb.getUserName() + "'s feedback" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("ARJUN", "FeedbackListAdapter() - getItemCount() count : " + data.size());
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView initials;
        TextView userName;
        TextView date;
        TextView title;
        TextView comment;
        TextView profName;
        TextView coursename;
        ImageView mIgnoreSpamFeedBackButton;
        ImageView mDeleteSpamFeedBackButton;
        ImageView mMarkFeedBackButton;

        RatingBar rating;

        LinearLayout adminPanel;
        LinearLayout loginUserPanel;

        public ViewHolder(View itemView) {
            super(itemView);
            initials = (TextView) itemView.findViewById(R.id.fb_list_item_user_initials);
            userName = (TextView) itemView.findViewById(R.id.fb_list_item_user_name);
            rating = (RatingBar) itemView.findViewById(R.id.fb_list_item_ratingbar);
            date = (TextView) itemView.findViewById(R.id.fb_list_item_submit_time);
            title = (TextView) itemView.findViewById(R.id.fb_list_item_fb_title);
            comment = (TextView) itemView.findViewById(R.id.fb_list_item_fb_comment);
            profName = (TextView) itemView.findViewById(R.id.fb_list_item_prof_name);
            coursename = (TextView) itemView.findViewById(R.id.fb_list_item_course_name);
            mIgnoreSpamFeedBackButton = (ImageView) itemView.findViewById(R.id.fb_list_item_ignore_spam_feedback);
            mDeleteSpamFeedBackButton = (ImageView) itemView.findViewById(R.id.fb_list_item_delete_spam_feedback);
            mMarkFeedBackButton = (ImageView) itemView.findViewById(R.id.fb_list_item_mark_feedback);
            adminPanel = (LinearLayout) itemView.findViewById(R.id.fb_list_item_admin_panel);
            loginUserPanel = (LinearLayout) itemView.findViewById(R.id.fb_list_item_login_user_panel);
        }
    }
}