package com.project.android.app.kys.adapters;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.HomeActivity;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.db.DBMgr;
import com.project.android.app.kys.helper.Constants.Tag.DrawerItemTag;
import com.project.android.app.kys.helper.Type.UserType;
import com.project.android.app.kys.popup.AUDisciplineDialog;
import com.project.android.app.kys.popup.AUMajorDialog;

import java.util.Collections;
import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Discipline> data = Collections.emptyList();
    private int TYPE_ADD_PROFESSOR = -1;
    private int TYPE_SPAM = -1;
    private int TYPE_SETTINGS = -1;
    private int TYPE_LOGOUT = -1;
    private LayoutInflater inflater;
    private Context mContext;

    public DrawerAdapter(Context context, List<Discipline> data) {
        Log.d("ARJUN", "DrawerAdapter() called with size : " + data.size());
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        setIndex();

        Log.d("test", "Drawer Adapter");

    }

    private void setIndex() {
        if (UserType.isAdmin()) {
            TYPE_ADD_PROFESSOR = data.size();
            TYPE_SPAM = TYPE_ADD_PROFESSOR + 1;
            TYPE_SETTINGS = TYPE_SPAM + 1;
            TYPE_LOGOUT = TYPE_SETTINGS + 1;
        } else if (UserType.isLoginUser()) {
            TYPE_SETTINGS = data.size();
            TYPE_LOGOUT = TYPE_SETTINGS + 1;
        }
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ARJUN", "DrawerAdapter() - onCreateViewHolder()");
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position == TYPE_ADD_PROFESSOR) || (position == TYPE_SPAM) || (position == TYPE_LOGOUT) || (position == TYPE_LOGOUT)) {
            return position;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("ARJUN", "DrawerAdapter() - onBindViewHolder() postion : " + position);
        if (position == TYPE_ADD_PROFESSOR) {
            createProfessorRow((ViewHolder) holder);
        } else if (position == TYPE_SPAM) {
            createSpamRow((ViewHolder) holder);
        } else if (position == TYPE_SETTINGS) {
            createSettingsRow((ViewHolder) holder);
        } else if (position == TYPE_LOGOUT) {
            createLogoutRow((ViewHolder) holder);
        } else {
            createDiscplineRow((ViewHolder) holder, position);
        }
    }

    private void createDiscplineRow(ViewHolder holder, int position) {
        Discipline currDisp = data.get(position);
        holder.title.setText(currDisp.getDisciplineName());
        holder.initials.setText(currDisp.getDisciplineInitials());
        holder.itemView.setTag(currDisp);

        checkDevider(position, holder);

        checkHeader(position, holder);

    }

    private void checkDevider(int position, ViewHolder holder) {
        if(position == (data.size() -1)) {
            holder.devider.setVisibility(View.VISIBLE);
        } else {
            holder.devider.setVisibility(View.INVISIBLE);
        }
    }

    private void checkHeader(int position, ViewHolder holder) {
        if(position == 0) {
            holder.header.setVisibility(View.VISIBLE);
            setAddDisciplineClickListener(holder.addDiscpButton);
        } else {
            holder.header.setVisibility(View.GONE);
        }
    }

    private void createLogoutRow(ViewHolder holder) {
        holder.title.setText(mContext.getResources().getString(R.string.nav_item_logout));
        holder.initials.setText(mContext.getResources().getString(R.string.nav_item_logout_initials));
        holder.itemView.setTag(DrawerItemTag.LOGOUT);
    }

    private void createSettingsRow(ViewHolder holder) {
        holder.title.setText(mContext.getResources().getString(R.string.nav_item_settings));
        holder.initials.setText(mContext.getResources().getString(R.string.nav_item_settings_initials));
        holder.itemView.setTag(DrawerItemTag.SETTINGS);
    }

    private void createSpamRow(ViewHolder holder) {
        holder.title.setText(mContext.getResources().getString(R.string.nav_item_spam));
        holder.initials.setText(mContext.getResources().getString(R.string.nav_item_spam_initials));
        holder.itemView.setTag(DrawerItemTag.SPAM);
        int spamCnt = DBMgr.getInstance().getSpamCount();
        if (spamCnt > 0) {
            holder.notofication.setText(spamCnt+"");
            holder.root_notification.setVisibility(View.VISIBLE);
        } else {
            holder.root_notification.setVisibility(View.INVISIBLE);
        }

    }

    private void createProfessorRow(ViewHolder holder) {
        holder.title.setText(mContext.getResources().getString(R.string.nav_item_add_professor));
        holder.initials.setText(mContext.getResources().getString(R.string.nav_item_add_professor_initials));
        holder.itemView.setTag(DrawerItemTag.ADD_PROFESSOR);
    }

    @Override
    public int getItemCount() {
        Log.d("ARJUN", "DrawerAdapter() - getItemCount() count : " + (data.size() + getNoOfExtraItems()));
        return data.size() + getNoOfExtraItems();
    }

    private int getNoOfExtraItems() {
        if (UserType.isAdmin()) {
            return 4;
        } else if (UserType.isLoginUser()) {
            return 2;
        } else {
            return 0;
        }
    }

    private void setAddDisciplineClickListener(ImageView addDiscpButton) {
        addDiscpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogFragment newFragment = AUDisciplineDialog.newInstance();
                newFragment.show(((HomeActivity)mContext).getFragmentManager(), "missiles");
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView notofication;
        TextView initials;
        LinearLayout devider;
        FrameLayout root_notification;
        RelativeLayout header;
        ImageView addDiscpButton;

        public ViewHolder(View itemView) {
            super(itemView);
            header = (RelativeLayout) itemView.findViewById(R.id.drawer_list_header);
            title = (TextView) itemView.findViewById(R.id.drawer_tv_title);
            notofication = (TextView) itemView.findViewById(R.id.drawer_tv_notification);
            initials = (TextView) itemView.findViewById(R.id.drawer_tv_initials);
            devider = (LinearLayout) itemView.findViewById(R.id.drawer_list_devider);
            addDiscpButton = (ImageView) itemView.findViewById(R.id.drawer_list_add_discipline_button);
            root_notification = (FrameLayout) itemView.findViewById(R.id.drawer_fl_notification);
        }
    }
}