package picsee.com.hongenit.picseesee.picClassify.commontab

import android.content.Context
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext
import picsee.com.hongenit.picseesee.R
import picsee.com.hongenit.picseesee.picClassify.AlbumBean
import picsee.com.hongenit.picseesee.picClassify.PicBean
import picsee.com.hongenit.picseesee.picClassify.ZResponse
import picsee.com.hongenit.picseesee.util.LogUtil
import picsee.com.hongenit.picseesee.util.ToastUtil

/**
 * Created by hongenit on 18/1/31.
 * 展示图片的presenter
 */
private val TAG: String = "CommonTabModel"

class CommonTabPresenter(context: Context) : ICommonTabPresenter, ZResponse {
    var isLoadMore: Boolean = false
    lateinit var mContext: Context

    init {
        mContext = context
    }

    override fun onSuccess(picList: ArrayList<AlbumBean>) {
        if (mView.isVisible) {
            if (isLoadMore) {
                mView.addData(picList)
            } else {
                mView.replaceData(picList)
            }
        }

    }

    override fun onError(msg: String?) {
        LogUtil.e(TAG, "error = $msg")
        ToastUtil.showToast(mContext.getString(R.string.msg_get_data_error))
    }

    override fun start(url: String) {
        CommonTabModel.reqOutList(url, 1, this)
    }

    private lateinit var mView: CommonTabFragment

    override fun setView(fragment: CommonTabFragment) {
        mView = fragment
    }

    private var mPageNum: Int = 1

    override fun requestData(isLoadMore: Boolean, url: String) {
        this.isLoadMore = isLoadMore
        if (isLoadMore) mPageNum++
        CommonTabModel.reqOutList(url, mPageNum, this)

    }


}
