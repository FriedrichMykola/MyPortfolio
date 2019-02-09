package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.enumeration.TypeBody;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatActivity;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_gallery.BEditorGallery;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_image_text.BEditorImageTextFragment;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_text.BEditorOnlyTextFragment;
import com.google.android.material.internal.NavigationMenu;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.github.yavski.fabspeeddial.FabSpeedDial;

public class BodyDesignActivity extends MvpAppCompatActivity implements IBodyDesignActivityView {

    public static final int RESULT_CODE = 1805;
    private FabSpeedDial mFabTools;

    @InjectPresenter
    BodyDesignActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_design);

        mFabTools = findViewById(R.id.fab_tools_design);
        mFabTools.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (mPresenter.getmIndex() < 5) {
                    switch (menuItem.getItemId()) {
                        case R.id.m_item_text_design: {
                            mPresenter.addItemDesign(new OnlyText(TypeBody.BOnlyText));
                            addItemFragment(new BEditorOnlyTextFragment(mPresenter.getLastItemDesign()));
                            break;
                        }
                        case R.id.m_item_image_text_design_left: {
                            mPresenter.addItemDesign(new ImageText(TypeBody.BImageTextLeft));
                            addItemFragment(new BEditorImageTextFragment(mPresenter.getLastItemDesign(), R.layout.fragment_editor_image_text_body_left));
                            break;
                        }
                        case R.id.m_item_image_text_design_right: {
                            mPresenter.addItemDesign(new ImageText(TypeBody.BImageTextRight));
                            addItemFragment(new BEditorImageTextFragment(mPresenter.getLastItemDesign(), R.layout.fragment_editor_image_text_body_right));
                            break;
                        }
                        case R.id.m_item_gallery_design: {
                            if (!mPresenter.ismGallery()) {
                                mPresenter.setmGallery(true);
                                mPresenter.addItemDesign(new Gallery(TypeBody.BGallery));
                                addItemFragment(new BEditorGallery(mPresenter.getLastItemDesign()));
                            } else {
                                showMessage(getString(R.string.you_have_gallery));
                            }
                            break;
                        }
                        case R.id.save_design: {
                            mPresenter.saveDesign();
                            break;
                        }
                    }
                } else {
                    showMessage(getString(R.string.max_five));
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });

        if (mPresenter.getmBodyDesigns().size() != 0) {
            buildBody();
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addItemFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(mPresenter.getFrameID(), fragment);
        transaction.commit();
        mPresenter.incrementIndex();
    }

    @Override
    public void deleteFragment(Fragment fragment) {
        /*code is hidden*/
    }

    private void buildBody() {
        mPresenter.setmIndex(0);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (BodyDesign elemBody : mPresenter.getmBodyDesigns()) {
            switch (elemBody.getmTypeBody()) {
                case BOnlyText: {
                    transaction.replace(mPresenter.getFrameID(), new BEditorOnlyTextFragment(mPresenter.nextItem()));
                    break;
                }
                case BImageTextRight: {
                    transaction.replace(mPresenter.getFrameID(), new BEditorImageTextFragment(mPresenter.nextItem(), R.layout.fragment_editor_image_text_body_right));
                    break;
                }
                case BImageTextLeft: {
                    transaction.replace(mPresenter.getFrameID(), new BEditorImageTextFragment(mPresenter.nextItem(), R.layout.fragment_editor_image_text_body_left));
                    break;
                }
                case BGallery: {
                    mPresenter.setmGallery(true);
                    transaction.replace(mPresenter.getFrameID(), new BEditorGallery(mPresenter.nextItem()));
                    break;
                }
            }
        }
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void saveData() {
        /*code is hidden*/
    }
}