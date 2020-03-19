package com.bb.mvproomapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bb.mvproomapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    @BindView(R.id.username_frag_textview)
    TextView userNameTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //pass username to string defined in strings.xml
        String userName = ((HomeActivity) getContext()).getUsername();
        userNameTextView.setText(getContext().getString(R.string.username_welcome, userName));
    }

    @OnClick(R.id.logout_frag_button)
    public void logoutClick(View view){
        ((HomeActivity) getContext()).logout();
        getActivity().getSupportFragmentManager().popBackStack(); //close fragment
    }
}
