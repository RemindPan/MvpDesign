package com.tiny.mvp.module.manage.download.cache;

import android.view.View;
import android.widget.TextView;

import com.tiny.downloaderlib.entity.FileInfo;
import com.tiny.mvp.R;
import com.tiny.mvp.injector.components.DaggerVideoCacheComponent;
import com.tiny.mvp.injector.modules.VideoCacheModule;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.base.BaseVideoDLFragment;
import com.tiny.mvp.module.base.ILocalView;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.manage.download.DownloadActivity;
import com.tiny.recycler.helper.RecyclerViewHelper;
import com.tiny.recycler.listener.OnRemoveDataListener;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import rx.functions.Action1;

/**
 *
 * video缓存列表
 */
public class VideoCacheFragment extends BaseVideoDLFragment<IRxBusPresenter> implements ILocalView<VideoInfo> {

    @BindView(R.id.default_bg)
    TextView mDefaultBg;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_download;
    }

    @Override
    protected void initInjector() {
        DaggerVideoCacheComponent.builder()
                .applicationComponent(getAppComponent())
                .videoCacheModule(new VideoCacheModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        RecyclerViewHelper.initRecyclerViewV(mContext, mRvVideoList, mAdapter);
        mRvVideoList.setItemAnimator(new SlideInLeftAnimator());
        mAdapter.setRemoveDataListener(new OnRemoveDataListener() {
            @Override
            public void onRemove(int position) {
                if (mAdapter.getItemCount() <= 1 && mDefaultBg.getVisibility() == View.GONE) {
                    mDefaultBg.setVisibility(View.VISIBLE);
                    ((DownloadActivity)getActivity()).enableEditMode(false);
                }
            }
        });
        initItemLongClick();
        mPresenter.registerRxBus(FileInfo.class, new Action1<FileInfo>() {
            @Override
            public void call(FileInfo fileInfo) {
                mAdapter.updateDownload(fileInfo);
            }
        });
    }

    @Override
    protected void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void loadData(List<VideoInfo> dataList) {
        if (mDefaultBg.getVisibility() == View.VISIBLE) {
            mDefaultBg.setVisibility(View.GONE);
        }
        mAdapter.updateItems(dataList);
    }

    @Override
    public void noData() {
        mDefaultBg.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }
}
