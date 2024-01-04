package com.nkh.doctruyen.ui.introduceStory;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.FragmentReviewStoryBinding;
import com.nkh.doctruyen.models.comment.Comment;
import com.nkh.doctruyen.models.story.Story;
import java.util.List;

public class ReviewStoryFragment extends Fragment implements CommentAdapter.ItemClickListener{

    private FragmentReviewStoryBinding binding;
    private static String tenTruyen,tomTat,date,theLoai,trangThai,id;
    private static double danhGia;

    PreferenceManager preferenceManager;

    CommentAdapter adapter;
    CommentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReviewStoryBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        preferenceManager = new PreferenceManager(this.getActivity());

        Bundle bundle = getArguments();
        if (bundle != null) {
            Story story = (Story) getArguments().getSerializable("key");

            tenTruyen = story.getTentruyen();
            tomTat = story.getTomtatnd();
            date = story.getNgaytao();
            danhGia = story.getDanhgia();
            theLoai = story.getTheloai();
            trangThai = story.getTrangthai();
            id = story.getId();

        }
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textContent.setText(tomTat);
        binding.rate.setText(danhGia+"");

        String token = preferenceManager.getString(Constant.PRE.saveToken);

        viewModel.listComment.observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                showListComment(comments);
            }
        });

        viewModel.success.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(getActivity(), getString(R.string.comment_success), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.fail.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(getActivity(), getString(R.string.comment_success), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
                viewModel.showComment(token,Integer.parseInt(id));
                binding.contentComment.setText("");
            }
        });

        viewModel.showComment(token,Integer.parseInt(id));
    }

    private void showListComment(List<Comment> commentList){
        adapter = new CommentAdapter(getActivity(),commentList);
        binding.rcvComment.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private void addComment(){
        int userId = Integer.parseInt(preferenceManager.getString(Constant.PRE.saveUserId));
        String token = preferenceManager.getString(Constant.PRE.saveToken);
        String noidungComment = binding.contentComment.getText().toString();
        if (!TextUtils.isEmpty(noidungComment)){
            viewModel.addComment(token,userId,Integer.parseInt(id),noidungComment,"");
        }
    }

    @Override
    public void onClickReply(Comment comment) {
        Dialog alertDialog = new Dialog(this.requireActivity());
        View view1 = LayoutInflater.from(this.requireActivity()).inflate(R.layout.dialog_reply, null);
        TextView btn_add = view1.findViewById(R.id.btn_ok);
        TextView btnCancel = view1.findViewById(R.id.cancel);
        TextView userCmt = view1.findViewById(R.id.text_name);
        EditText edtNumber = view1.findViewById(R.id.reply);

        userCmt.setText(getString(R.string.reply_title) + comment.getUserid());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
       btn_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String contentReply = edtNumber.getText().toString();
               int userId = Integer.parseInt(preferenceManager.getString(Constant.PRE.saveUserId));
               String token = preferenceManager.getString(Constant.PRE.saveToken);
               if (!TextUtils.isEmpty(contentReply)){
                   viewModel.addComment(token,userId, Integer.parseInt(id),contentReply,comment.getId());
                   alertDialog.cancel();
                   viewModel.showComment(token,Integer.parseInt(id));
               }
           }
       });

        alertDialog.setContentView(view1);
        alertDialog.getWindow().getDecorView().setBackgroundResource(R.drawable.popup_background);
        Window window = alertDialog.getWindow();
        window.setLayout(1000, 600);
        alertDialog.show();
        alertDialog.setCancelable(true);
    }
}