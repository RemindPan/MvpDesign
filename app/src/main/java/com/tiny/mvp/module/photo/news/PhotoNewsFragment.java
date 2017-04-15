package com.tiny.mvp.module.photo.news;

import android.support.v7.widget.RecyclerView;

import com.tiny.mvp.R;
import com.tiny.mvp.api.bean.PhotoInfo;
import com.tiny.mvp.injector.components.DaggerPhotoNewsComponent;
import com.tiny.mvp.injector.modules.PhotoNewsModule;
import com.tiny.mvp.module.base.BaseFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.base.ILoadDataView;
import com.tiny.recycler.adapter.BaseQuickAdapter;
import com.tiny.recycler.helper.RecyclerViewHelper;
import com.tiny.recycler.listener.OnRequestDataListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 *
 * 图片新闻列表
 */
public class PhotoNewsFragment extends BaseFragment<IBasePresenter> implements ILoadDataView<List<PhotoInfo>> {

    @BindView(R.id.rv_photo_list)
    RecyclerView mRvPhotoList;

    @Inject
    BaseQuickAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_list;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoNewsComponent.builder()
                .applicationComponent(getAppComponent())
                .photoNewsModule(new PhotoNewsModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        RecyclerViewHelper.initRecyclerViewV(mContext, mRvPhotoList, new SlideInBottomAnimationAdapter(mAdapter));
        mAdapter.setRequestDataListener(new OnRequestDataListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getMoreData();
            }
        });
    }

    @Override
    protected void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void loadData(List<PhotoInfo> photoList) {
        mAdapter.updateItems(photoList);
    }

    @Override
    public void loadMoreData(List<PhotoInfo> photoList) {
        mAdapter.addItems(photoList);
    }

    @Override
    public void loadNoData() {
        mAdapter.noMoreData();
    }
}
