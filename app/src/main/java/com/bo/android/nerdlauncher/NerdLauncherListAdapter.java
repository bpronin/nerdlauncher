package com.bo.android.nerdlauncher;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class NerdLauncherListAdapter extends ArrayAdapter<ResolveInfo> {


    public NerdLauncherListAdapter(Activity activity, List<ResolveInfo> data) {
        super(activity, 0, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, parent, false);
        }

//            Crime item = getItem(position);
//
//            TextView titleText = (TextView) view.findViewById(R.id.list_item_title);
//            titleText.setText(item.getTitle());
//
//            TextView dateText = (TextView) view.findViewById(R.id.list_item_date);
//            dateText.setText(DateFormat.format(DATE_PATTERN, item.getDate()));
//
//            CheckBox solvedCheckBox = (CheckBox) view.findViewById(R.id.list_item_solved_check_box);
//            solvedCheckBox.setChecked(item.isSolved());
//
//            return view;

        PackageManager pm = getActivity().getPackageManager();
        ResolveInfo info = getItem(position);

        TextView titleText = (TextView) view.findViewById(R.id.list_item_title);
        titleText.setText(info.loadLabel(pm));

        ImageView icon = (ImageView) view.findViewById(R.id.list_item_icon);
        icon.setImageDrawable(info.loadIcon(pm));

        return view;
    }

    private Activity getActivity() {
        return (Activity) getContext();
    }

}
