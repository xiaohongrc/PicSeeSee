package picsee.com.hongenit.picseesee.picClassify.detail

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_common_tab.*
import picsee.com.hongenit.picseesee.picClassify.DetailResponse
import picsee.com.hongenit.picseesee.picClassify.PicBean
import picsee.com.hongenit.picseesee.picClassify.ZResponse
import picsee.com.hongenit.picseesee.util.ToastUtil

/**
 * Created by hongenit on 18/2/2.
 * 图片详情的presenter
 */
class DetailPresenter : IDetailPresenter, DetailResponse {


    override fun onSuccess(picList: ArrayList<PicBean>) {
        if (!mView.isFinishing) {
            mView.replaceData(picList)
        }
    }

    override fun onError(msg: String?) {
        ToastUtil.showToast(msg.toString())
    }


    override fun showBigImg() {

    }

    private lateinit var mView: DetailsActivity

    override fun start(view: DetailsActivity) {
        mView = view
    }

    override fun requestData(url: String) {
        DetailModel.requestDetails(url, 1, this)
    }
}