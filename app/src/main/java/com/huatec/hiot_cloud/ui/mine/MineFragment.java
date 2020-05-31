package com.huatec.hiot_cloud.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.base.BaseFragment;
import com.huatec.hiot_cloud.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {

    @Inject
    MinePresenter presenter;

    @BindView(R.id.iv_user_head_image)
    ImageView ivUserHeadImage;
    @BindView(R.id.tv_user_center_nickname)
    TextView tvUserCenterNickname;
    @BindView(R.id.tv_user_center_email)
    TextView tvUserCenterEmail;
    @BindView(R.id.tv_user_center_update_password)
    TextView tvUserCenterUpdatePassword;
    @BindView(R.id.tv_user_center_update_email)
    TextView tvUserCenterUpdateEmail;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    /**
     * 创建fragment实例
     *
     * @return fragment
     */
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadUserInfo();
    }

    @Override
    public MinePresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectDependencies() {
        //判断获取到的活动是否属于BaseActivity类
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).getActivityComponent().inject(this);
        }
    }

    @OnClick({R.id.iv_user_head_image, R.id.tv_user_center_update_password, R.id.tv_user_center_update_email, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_head_image:
                break;
            case R.id.tv_user_center_update_password:
                break;
            case R.id.tv_user_center_update_email:
                break;
            case R.id.btn_logout:
                break;
        }
    }

    @Override
    public void refreshUserInfo(UserBean userBean) {
        ImageUtils.showCircle(getActivity(), ivUserHeadImage, ImageUtils.getFullUrl(userBean.getImg()));
        tvUserCenterEmail.setText(userBean.getEmail());
        tvUserCenterNickname.setText(userBean.getUsername());

    }
}
