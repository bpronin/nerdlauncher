package com.bo.android.nerdlauncher;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import static android.app.ActivityManager.*;

public class NerdLauncherTaskListAdapter extends ArrayAdapter<RunningTaskInfo> {

    public NerdLauncherTaskListAdapter(FragmentActivity activity, List<RunningTaskInfo> data) {
        super(activity, 0, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, parent, false);
        }

        RunningTaskInfo info = getItem(position);

        TextView titleText = (TextView) view.findViewById(R.id.list_item_title);
        titleText.setText(info.baseActivity.getClassName());

        return view;
    }

    private Activity getActivity() {
        return (Activity) getContext();
    }

}
