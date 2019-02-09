package com.example.business.friedrich.kuzan.business.ui.business;

import android.os.Bundle;
import android.view.Menu;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatActivity;
import com.example.business.friedrich.kuzan.business.ui.business.design_fragment.DesignFragment;
import com.example.business.friedrich.kuzan.business.ui.business.disposable_fragments.NotDesignFragment;
import com.example.business.friedrich.kuzan.business.ui.business.main_fragment.MainBusinessFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BusinessActivity extends MvpAppCompatActivity implements IBusinessActivityView {

    private BottomAppBar mBottomAppBar;
    private FloatingActionButton mButtonDesign;

    @InjectPresenter
    BusinessActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        mBottomAppBar = findViewById(R.id.bottom_app_bar_business);
        mButtonDesign = findViewById(R.id.button_floating);

        setSupportActionBar(mBottomAppBar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mPresenter.isDesign()) {
            transaction.replace(R.id.linear_body, new MainBusinessFragment());
        } else {
            transaction.replace(R.id.linear_body, new NotDesignFragment());
        }

        transaction.commit();

        mButtonDesign.setOnClickListener(v -> mPresenter.changeFlag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_app_bar_business, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void changePositionButtonDesign(int flag) {
        mBottomAppBar.setFabAlignmentMode(flag);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (flag == 0) {
            mPresenter.setmChangeDesign(false);
            mButtonDesign.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_design));
            Fragment fragment;
            if (!mPresenter.isDesign()) {
                fragment = new NotDesignFragment();
            } else {
                fragment = new MainBusinessFragment();
            }
            transaction.setCustomAnimations(R.anim.move_left_new, R.anim.move_left_old);
            transaction.replace(R.id.linear_body, fragment);
        } else {
            mButtonDesign.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_process));
            transaction.setCustomAnimations(R.anim.move_right_new, R.anim.move_right_old);
            transaction.replace(R.id.linear_body, new DesignFragment(mPresenter));
        }
        transaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
