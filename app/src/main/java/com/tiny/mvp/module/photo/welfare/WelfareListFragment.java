package com.tiny.mvp.module.photo.welfare;

import android.support.v7.widget.RecyclerView;

import com.tiny.mvp.R;
import com.tiny.mvp.adapter.SlideInBottomAdapter;
import com.tiny.mvp.api.bean.WelfarePhotoInfo;
import com.tiny.mvp.injector.components.DaggerWelfarePhotoComponent;
import com.tiny.mvp.injector.modules.WelfarePhotoModule;
import com.tiny.mvp.module.base.BaseFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.base.ILoadDataView;
import com.tiny.recycler.adapter.BaseQuickAdapter;
import com.tiny.recycler.helper.RecyclerViewHelper;
import com.tiny.recycler.listener.OnRequestDataListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *
 * 福利图片界面
 */
public class WelfareListFragment extends BaseFragment<IBasePresenter> implements ILoadDataView<List<WelfarePhotoInfo>> {

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
        DaggerWelfarePhotoComponent.builder()
                .applicationComponent(getAppComponent())
                .welfarePhotoModule(new WelfarePhotoModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        SlideInBottomAdapter slideAdapter = new SlideInBottomAdapter(mAdapter);
        RecyclerViewHelper.initRecyclerViewSV(mContext, mRvPhotoList, slideAdapter, 2);
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
    public void loadData(List<WelfarePhotoInfo> photoList) {
        mAdapter.updateItems(photoList);
    }

    @Override
    public void loadMoreData(List<WelfarePhotoInfo> photoList) {
        mAdapter.loadComplete();
        mAdapter.addItems(photoList);
    }

    @Override
    public void loadNoData() {
        mAdapter.loadAbnormal();
    }
}