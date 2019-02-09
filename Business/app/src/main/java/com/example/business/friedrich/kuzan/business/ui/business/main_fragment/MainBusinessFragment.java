package com.example.business.friedrich.kuzan.business.ui.business.main_fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.enumeration.Position;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatFragment;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainBusinessFragment extends MvpAppCompatFragment
    implements IMainBusinessFragmentView {

    public final static String TAG = "MainBusinessFragment";

    private TextView mTextName;
    private ImageView mImageBanner;
    private FrameLayout mFrameBanner;
    private RelativeLayout mRelativeLayout;
    private CircleImageView mImageLogotype;
    private ScrollView mScrollView;

    private LinearLayout mLinearMainBodyDesign;

    @InjectPresenter
    public MainBusinessFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_design, container, false);

        mScrollView = view.findViewById(R.id.scroll_view_main_design);

        mLinearMainBodyDesign = view.findViewById(R.id.linear_main_body_design);

        mTextName = view.findViewById(R.id.text_name_business);
        mTextName.setText(mPresenter.getmBusiness().getmName());

        mRelativeLayout = view.findViewById(R.id.relative_body_main_design);
        mFrameBanner = view.findViewById(R.id.frame_banner);

        mImageBanner = view.findViewById(R.id.image_banner);
        mImageLogotype = view.findViewById(R.id.image_circle_logotype);

        LayoutParams paramsLogo = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsLogo.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        paramsLogo.addRule(RelativeLayout.BELOW, R.id.image_banner);
        paramsLogo.width = (int) getResources().getDimension(R.dimen.size_120dp);
        paramsLogo.height = (int) getResources().getDimension(R.dimen.size_120dp);
        paramsLogo.topMargin = (int) getResources().getDimension(R.dimen.size_n_60dp);

        mImageLogotype.setLayoutParams(paramsLogo);

        mTextName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        mPresenter.buildDesign(mPresenter.downloadFirebase());
        return view;
    }

    @Override
    public void changeColorBackground(int color) {
        mRelativeLayout.setBackgroundColor(color);
    }

    @Override
    public void changeColorText(int color) {
        mTextName.setTextColor(color);
    }

    @Override
    public void changeColorBanner(int color) {
        mFrameBanner.setBackgroundColor(color);
    }

    @Override
    public void changeColorBorderLogotype(int color) {
        mImageLogotype.setBorderColor(color);
    }

    @Override
    public void changeLogotype(Uri uri) {
        mImageLogotype.setBackground(new ColorDrawable(Color.TRANSPARENT));
        Glide.with(getContext())
            .asDrawable()
            .load(uri)
            .into(mImageLogotype);
    }

    @Override
    public void changeBanner(Uri uri) {
        mImageBanner.setBackground(new ColorDrawable(Color.TRANSPARENT));
        Glide.with(getContext())
            .asDrawable()
            .load(uri)
            .into(mImageBanner);
    }

    @Override
    public void changePositionBanner(Position pos) {
        LayoutParams paramsLogo, paramsText;

        switch (pos) {
            case Left: {
                paramsLogo = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

                paramsLogo.leftMargin = (int) getResources().getDimension(R.dimen.size_10dp);
                paramsLogo.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                paramsLogo.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);

                paramsLogo.width = (int) getResources().getDimension(R.dimen.size_120dp);
                paramsLogo.height = (int) getResources().getDimension(R.dimen.size_120dp);
                paramsLogo.topMargin = (int) getResources().getDimension(R.dimen.size_n_60dp);

                paramsLogo.addRule(RelativeLayout.BELOW, R.id.image_banner);
                mImageLogotype.setLayoutParams(paramsLogo);


                paramsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

                paramsText.topMargin = (int) getResources().getDimension(R.dimen.size_25dp);
                paramsText.leftMargin = (int) getResources().getDimension(R.dimen.size_15dp);

                paramsLogo.setMarginStart((int) getResources().getDimension(R.dimen.size_15dp));

                paramsText.addRule(RelativeLayout.RIGHT_OF, R.id.image_circle_logotype);
                mTextName.setLayoutParams(paramsText);
                break;
            }
            case Center: {
                paramsLogo = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

                paramsLogo.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                paramsLogo.addRule(RelativeLayout.BELOW, R.id.image_banner);
                paramsLogo.width = (int) getResources().getDimension(R.dimen.size_120dp);
                paramsLogo.height = (int) getResources().getDimension(R.dimen.size_120dp);
                paramsLogo.topMargin = (int) getResources().getDimension(R.dimen.size_n_60dp);

                mImageLogotype.setLayoutParams(paramsLogo);


                paramsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

                paramsText.topMargin = (int) getResources().getDimension(R.dimen.size_10dp);

                paramsText.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                paramsText.addRule(RelativeLayout.ABOVE, R.id.image_circle_logotype);
                mTextName.setLayoutParams(paramsText);
                break;
            }
            case Right: {
                paramsLogo = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

                paramsLogo.rightMargin = (int) getResources().getDimension(R.dimen.size_10dp);

                paramsLogo.width = (int) getResources().getDimension(R.dimen.size_120dp);
                paramsLogo.height = (int) getResources().getDimension(R.dimen.size_120dp);
                paramsLogo.topMargin = (int) getResources().getDimension(R.dimen.size_n_60dp);

                paramsLogo.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                paramsLogo.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
                paramsLogo.addRule(RelativeLayout.BELOW, R.id.image_banner);
                mImageLogotype.setLayoutParams(paramsLogo);

                paramsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

                paramsText.topMargin = (int) getResources().getDimension(R.dimen.size_25dp);
                paramsText.rightMargin = (int) getResources().getDimension(R.dimen.size_15dp);
                paramsText.setMarginEnd((int) getResources().getDimension(R.dimen.size_15dp));
                paramsText.addRule(RelativeLayout.LEFT_OF, R.id.image_circle_logotype);
                mTextName.setLayoutParams(paramsText);
                break;
            }
        }
    }

    @Override
    public void changeName(String name, int size) {
        mTextName.setText(name);
        mTextName.setTextSize(size);
    }

    @Override
    public void changeSizeText(int size) {
        mTextName.setTextSize(getSizeLetter(size));
    }

    private int getSizeLetter(int size) {
        Resources resource = getResources();
        switch (size) {
            case 4: {
                return (int) resource.getDimension(R.dimen.size_4);
            }
            case 7: {
                return (int) resource.getDimension(R.dimen.size_7);
            }
            case 9: {
                return (int) resource.getDimension(R.dimen.size_9);
            }
            case 11: {
                return (int) resource.getDimension(R.dimen.size_11);
            }
            case 13: {
                return (int) resource.getDimension(R.dimen.size_13);
            }
            case 15: {
                return (int) resource.getDimension(R.dimen.size_15);
            }
            case 17: {
                return (int) resource.getDimension(R.dimen.size_17);
            }
            case 19: {
                return (int) resource.getDimension(R.dimen.size_19);
            }
            case 21: {
                return (int) resource.getDimension(R.dimen.size_21);
            }
            case 23: {
                return (int) resource.getDimension(R.dimen.size_23);
            }
            case 25: {
                return (int) resource.getDimension(R.dimen.size_25);
            }
            case 27: {
                return (int) resource.getDimension(R.dimen.size_27);
            }
            default: {
                return (int) resource.getDimension(R.dimen.size_30);
            }
        }
    }

    @Override
    public void addOnlyTextItem(OnlyText onlyText, int counter) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_body_text, null);

        TextView textView = view.findViewById(R.id.text_only);
        textView.setText(onlyText.getmText());
        textView.setTextColor(mTextName.getTextColors());

        mLinearMainBodyDesign.addView(view);
        mScrollView.fullScroll(ScrollView.FOCUS_UP);

        mPresenter.closeDialog(counter);
        mScrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void addImageTextItem(ImageText imageText, int layout, int counter) {
        View view = LayoutInflater.from(getContext()).inflate(layout, null);

        TextView textView = view.findViewById(R.id.text_body);
        textView.setText(imageText.getmText());
        textView.setTextColor(mTextName.getTextColors());

        ImageView imageView = view.findViewById(R.id.image_body);
        Glide.with(getContext())
            .asDrawable()
            .load(imageText.getmUri())
            .addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    Uri uri = Uri.fromFile(new File(imageText.getmUri().getLastPathSegment()));
                    imageView.setImageURI(uri);
                    return true;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    mPresenter.closeDialog(counter);
                    return false;
                }
            })
            .into(imageView);

        mLinearMainBodyDesign.addView(view);
        mScrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void addGalleryItem(Gallery gallery, int counter) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_body_gallery, null);

        final RecyclerView recyclerGallery = view.findViewById(R.id.recycler_view_gallery);
        recyclerGallery.setAdapter(new GalleryRecyclerAdapter(gallery.getmUris()));
        recyclerGallery.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        mLinearMainBodyDesign.addView(view);

        mScrollView.fullScroll(ScrollView.FOCUS_UP);

        mPresenter.closeDialog(counter);
        mScrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addPaddingBottomScrollView() {
        mScrollView.setPadding(mScrollView.getPaddingLeft(),
            mScrollView.getPaddingTop(),
            mScrollView.getPaddingRight(),
            (int) getResources().getDimension(R.dimen.size_64dp));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showDialogProgress(boolean work) {
        mLinearMainBodyDesign.removeAllViews();
        if (work) {
            ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
            dialogFragment.show(getFragmentManager(), null);
            mPresenter.setmICloseDialog(dialogFragment);
        } else {
            Toast.makeText(getContext(), getString(R.string.you_must_create_body_design), Toast.LENGTH_LONG).show();
        }
    }
}