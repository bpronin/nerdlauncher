package com.bo.android.nerdlauncher;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NerdLauncherFragment extends ListFragment {

    private static final String TAG = "NerdLauncherFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final PackageManager pm = getActivity().getPackageManager();
        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> activities = pm.queryIntentActivities(startupIntent, 0);

        Log.i(TAG, "I've found " + activities.size() + " activities.");

        Collections.sort(activities, new Comparator<ResolveInfo>() {

            @Override
            public int compare(ResolveInfo a, ResolveInfo b) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        a.loadLabel(pm).toString(),
                        b.loadLabel(pm).toString());
            }
        });

        setListAdapter(new NerdLauncherListAdapter(getActivity(), activities));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ResolveInfo resolveInfo = (ResolveInfo) l.getAdapter().getItem(position);
        ActivityInfo activityInfo = resolveInfo.activityInfo;

        if (activityInfo == null) return;

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}
