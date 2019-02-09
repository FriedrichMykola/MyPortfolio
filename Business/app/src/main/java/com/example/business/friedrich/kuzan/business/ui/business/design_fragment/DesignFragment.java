package com.example.business.friedrich.kuzan.business.ui.business.design_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.enumeration.ImportantItem;
import com.example.business.friedrich.kuzan.business.model.enumeration.Position;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.IApplyNewDesign;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.IBuildBodyDesign;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBBanner;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBLogotype;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBPaint;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBPosition;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatFragment;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.BodyDesignActivity;
import com.example.business.friedrich.kuzan.business.ui.business.design_fragment.dialog_fragment.UpdateTextDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.business.main_fragment.MainBusinessFragment;
import com.example.business.friedrich.kuzan.business.ui.business.main_fragment.ProgressDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.flag.FlagMode;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class DesignFragment extends MvpAppCompatFragment implements IDesignFragmentView {

    private static final int REQUEST_CODE_BANNER = 1801;
    private static final int REQUEST_CODE_LOGOTYPE = 1802;
    private static final int REQUEST_CODE_FRAGMENT = 1803;

    private ColorPickerView mColorPickerView;

    private RadioGroup mRadioGroupColor;

    private MaterialButton mButtonApplyDesign,
        mButtonLogotype,
        mButtonBanner,
        mButtonPosBanner,
        mButtonWrite,
        mButtonBody;

    private MaterialButton mBanButtonLeft, mBanButtonCenter, mBanButtonRight;

    private LinearLayout mLinearPositionBanner;

    @InjectPresenter
    public DesignFragmentPresenter mPresenter;

    @ProvidePresenter
    public DesignFragmentPresenter getPresenter() {
        return new DesignFragmentPresenter(mIApplyNewDesign);
    }

    public DesignFragment() {
    }

    private IApplyNewDesign mIApplyNewDesign;

    @SuppressLint("ValidFragment")
    public DesignFragment(IApplyNewDesign iApplyNewDesign) {
        this.mIApplyNewDesign = iApplyNewDesign;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_design_business, container, false);

        mButtonApplyDesign = view.findViewById(R.id.button_apply_design);
        mButtonApplyDesign.setOnClickListener(v -> mPresenter.deleteOldImage());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_design_main_page, new MainBusinessFragment(), MainBusinessFragment.TAG);
        transaction.commit();

        mLinearPositionBanner = view.findViewById(R.id.linear_variants_position);

        mButtonLogotype = view.findViewById(R.id.button_add_logotype);
        mButtonLogotype.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_LOGOTYPE);
            goneLinearPos();
        });

        mButtonBanner = view.findViewById(R.id.button_add_banner);
        mButtonBanner.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_BANNER);
            goneLinearPos();
        });

        mButtonPosBanner = view.findViewById(R.id.button_add_position_logotype);
        mButtonPosBanner.setOnClickListener(v -> {

            mBanButtonLeft = view.findViewById(R.id.button_pos_left);
            mBanButtonLeft.setOnClickListener(vv -> EventBus.getDefault().post(new EBPosition(Position.Left)));

            mBanButtonCenter = view.findViewById(R.id.button_pos_center);
            mBanButtonCenter.setOnClickListener(vv -> EventBus.getDefault().post(new EBPosition(Position.Center)));

            mBanButtonRight = view.findViewById(R.id.button_pos_right);
            mBanButtonRight.setOnClickListener(vv -> EventBus.getDefault().post(new EBPosition(Position.Right)));

            if (mLinearPositionBanner.getVisibility() != View.VISIBLE) {
                mLinearPositionBanner.setVisibility(View.VISIBLE);
            } else {
                goneLinearPos();
            }
        });

        mButtonWrite = view.findViewById(R.id.button_add_text);
        mButtonWrite.setOnClickListener(v -> {
            UpdateTextDialogFragment dialogFragment = new UpdateTextDialogFragment();
            dialogFragment.show(getFragmentManager(), null);
        });

        mButtonBody = view.findViewById(R.id.button_add_style);
        mButtonBody.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), BodyDesignActivity.class), REQUEST_CODE_FRAGMENT));

        mRadioGroupColor = view.findViewById(R.id.radio_group_color);

        mColorPickerView = view.findViewById(R.id.color_picker_view_design);
        mColorPickerView.attachAlphaSlider(view.findViewById(R.id.alpha_slide_bar));
        mColorPickerView.attachBrightnessSlider(view.findViewById(R.id.brightness_slide_bar));
        mColorPickerView.setFlagMode(FlagMode.LAST);
        mColorPickerView.setColorListener((ColorEnvelopeListener) (envelope, fromUser) -> {
            mPresenter.getmEBPaint().setmColor(envelope.getColor());
            EventBus.getDefault().post(mPresenter.getmEBPaint());
        });

        mRadioGroupColor.setOnCheckedChangeListener((group, checkedId) -> {
            mPresenter.setmEBPaint(new EBPaint());
            mPresenter.getmEBPaint().setmId(checkedId);
        });

        return view;
    }

    private void goneLinearPos() {
        if (mLinearPositionBanner.getVisibility() == View.VISIBLE) {
            mLinearPositionBanner.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_LOGOTYPE: {
                    EventBus.getDefault().post(new EBLogotype(data.getData()));
                    break;
                }
                case REQUEST_CODE_BANNER: {
                    EventBus.getDefault().post(new EBBanner(data.getData()));
                    break;
                }
                case REQUEST_CODE_FRAGMENT: {
                    if (resultCode == BodyDesignActivity.RESULT_CODE) {
                        IBuildBodyDesign iBuildBodyDesign = ((MainBusinessFragment) getFragmentManager().findFragmentByTag(MainBusinessFragment.TAG)).mPresenter;
                        mPresenter.setmIBuildBodyDesign(iBuildBodyDesign);
                        mPresenter.startBuild();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void showDialogProgress() {
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(getFragmentManager(), null);
        mPresenter.setmICloseDialog(dialogFragment);
    }

    @Override
    public void showError(ImportantItem item) {
        switch (item) {
            case ItemLogotype: {
                showMessage(getString(R.string.you_do_not_add_logotype));
                break;
            }
            case ItemBanner: {
                showMessage(getString(R.string.you_do_not_add_banner));
                break;
            }
            case ItemBodyDesign: {
                showMessage(getString(R.string.you_do_not_add_design));
                break;
            }
        }

    }

    @Override
    public void onDestroyView() {
        mPresenter.setmIBuildBodyDesign(null);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
