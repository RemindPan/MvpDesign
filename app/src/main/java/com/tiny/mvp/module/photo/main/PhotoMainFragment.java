package com.tiny.mvp.module.photo.main;

import android.animation.Animator;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.tiny.mvp.R;
import com.tiny.mvp.adapter.ViewPagerAdapter;
import com.tiny.mvp.injector.components.DaggerPhotoMainComponent;
import com.tiny.mvp.injector.modules.PhotoMainModule;
import com.tiny.mvp.module.base.BaseFragment;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.manage.love.LoveActivity;
import com.tiny.mvp.module.photo.beauty.BeautyListFragment;
import com.tiny.mvp.module.photo.news.PhotoNewsFragment;
import com.tiny.mvp.module.photo.welfare.WelfareListFragment;
import com.tiny.mvp.rxbus.event.LoveEvent;
import com.tiny.mvp.utils.AnimateHelper;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 *
 * 图片主界面
 */
public class PhotoMainFragment extends BaseFragment<IRxBusPresenter> implements IPhotoMainView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_count)
    TextView mIvCount;

    @Inject
    ViewPagerAdapter mPagerAdapter;
    private Animator mLovedAnimator;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_main;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoMainComponent.builder()
                .applicationComponent(getAppComponent())
                .photoMainModule(new PhotoMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, "图片");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BeautyListFragment());
        fragments.add(new WelfareListFragment());
        fragments.add(new PhotoNewsFragment());
        mPagerAdapter.setItems(fragments, new String[] {"美女", "福利", "生活"});
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
        mPresenter.registerRxBus(LoveEvent.class, new Action1<LoveEvent>() {
            @Override
            public void call(LoveEvent loveEvent) {
                mPresenter.getData();
            }
        });
    }

    @Override
    protected void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void updateCount(int lovedCount) {
        mIvCount.setText(lovedCount+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLovedAnimator == null) {
            mIvCount.post(new Runnable() {
                @Override
                public void run() {
                    mLovedAnimator = AnimateHelper.doHappyJump(mIvCount, mIvCount.getHeight() * 2/3, 3000);
                }
            });
        } else {
            AnimateHelper.startAnimator(mLovedAnimator);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        AnimateHelper.stopAnimator(mLovedAnimator);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }

    @OnClick(R.id.fl_layout)
    public void onClick() {
        LoveActivity.launch(mContext, 0);
    }
}
