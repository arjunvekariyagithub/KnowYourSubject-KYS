package com.project.android.app.kys.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.android.app.kys.R;
import com.project.android.app.kys.business.Course;

import java.util.Collections;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Course> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;

    public CourseListAdapter(Context context, List<Course> data) {
        Log.d("ARJUN", "DepartmentListAdapter() called with size : " + data.size());
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
        Log.d("ARJUN", "DepartmentListAdapter() - onCreateViewHolder()");
        View view = inflater.inflate(R.layout.course_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("ARJUN", "DepartmentListAdapter() - onBindViewHolder() postion : " + position);
        createDepartmentRow((ViewHolder) holder, position);
    }

    private void createDepartmentRow(ViewHolder holder, int position) {
        Course curCourse = data.get(position);
        holder.title.setText(curCourse.getCourseName());
        holder.course_code.setText(curCourse.getCourseCode());
        holder.no_feedback.setText(curCourse.getFeedbackCount()+"");
        holder.initials.setText(curCourse.getCourseInitials());
        holder.summary.setText(curCourse.getCourseSummary());
        holder.itemView.setTag(curCourse);

        String feedback;
        if (curCourse.getFeedbackCount() > 1) {
            holder.feedback.setText(mContext.getResources().getString(R.string.feedbacks));
        } else if (curCourse.getFeedbackCount() == 1) {
            holder.feedback.setText(mContext.getResources().getString(R.string.feedback));
        } else {
            holder.no_feedback.setVisibility(View.INVISIBLE);
            holder.feedback.setVisibility(View.INVISIBLE);
        }
        checkDevider(position, holder);
    }

    private void checkDevider(int position, ViewHolder holder) {
        if (position == (data.size() - 1)) {
            holder.devider.setVisibility(View.INVISIBLE);
        } else {
            holder.devider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("ARJUN", "DepartmentListAdapter() - getItemCount() count : " + data.size());
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView initials;
        TextView feedback;
        TextView no_feedback;
        TextView course_code;
        TextView summary;
        LinearLayout devider;
        RelativeLayout header;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.course_list_item_tv_title);
            initials = (TextView) itemView.findViewById(R.id.course_list_item_tv_initials);
            feedback = (TextView) itemView.findViewById(R.id.course_list_item_tv_feedback);
            no_feedback = (TextView) itemView.findViewById(R.id.course_list_item_tv_nos_feedback);
            course_code = (TextView) itemView.findViewById(R.id.course_list_item_tv_course_code);
            summary = (TextView) itemView.findViewById(R.id.course_list_item_tv_summary);
            devider = (LinearLayout) itemView.findViewById(R.id.course_list_item_devider);
        }
    }
}