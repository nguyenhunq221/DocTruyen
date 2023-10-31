package com.nkh.doctruyen.ui.info;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.FragmentInfoBinding;
import com.nkh.doctruyen.ui.help.HelpActivity;
import com.nkh.doctruyen.ui.help.ReviewAppActivity;
import com.nkh.doctruyen.ui.login.LoginActivity;

public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;

    PreferenceManager preferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferenceManager = new PreferenceManager(this.getActivity());
        binding = FragmentInfoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
    }

    private void setUpView() {
        binding.userName.setText(preferenceManager.getString(Constant.PRE.saveUserName));
        binding.cardviewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle(getString(R.string.logout_notice))

                        .setMessage(getString(R.string.logout_message))

                        .setPositiveButton(getString(R.string.no_logout), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton(getString(R.string.yes_logout), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                preferenceManager.clear();
                                startActivity(new Intent(requireActivity(), LoginActivity.class));
                            }
                        })
                        .show();
            }
        });

        binding.cardViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
            }
        });
        binding.cardViewIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReviewAppActivity.class));
            }
        });
    }
}