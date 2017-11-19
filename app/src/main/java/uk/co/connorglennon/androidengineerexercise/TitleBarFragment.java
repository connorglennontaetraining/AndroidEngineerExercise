package uk.co.connorglennon.androidengineerexercise;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import uk.co.connorglennon.androidengineerexercise.R;

/**
 * Created by Conno_000 on 2017-11-16.
 */

public class TitleBarFragment extends Fragment {

    private Button btnBack;
    private TextView tvTitle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_title_bar, container);
        tvTitle = (TextView) fragment.findViewById(R.id.tvTitle);
        return fragment;
    }

    public void setTitle(String title)
    {
        this.tvTitle.setText(title);
    }
}
