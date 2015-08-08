package com.bo.android.nerdlauncher;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.app.ActivityManager.*;

public class NerdLauncherRunningFragment extends ListFragment {

    private static final String TAG = "NerdLauncherRunningFragment";
    private List<RunningTaskInfo> data = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new NerdLauncherTaskListAdapter(getActivity(), data));
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        RunningTaskInfo info = ((NerdLauncherTaskListAdapter) getListAdapter()).getItem(position);
        getActivityManager().moveTaskToFront(info.id, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTasks();
    }

    private void updateTasks() {
        data.clear();
        data.addAll(getActivityManager().getRunningTasks(Integer.MAX_VALUE));
        ((NerdLauncherTaskListAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private ActivityManager getActivityManager() {
        return (ActivityManager)getActivity().getSystemService(Activity.ACTIVITY_SERVICE);
    }

}
