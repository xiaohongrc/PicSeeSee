package picsee.com.hongenit.picseesee.picClassify.detail

import android.app.Activity
import android.support.v7.app.AppCompatActivity

/**
 * Created by hongenit on 18/2/2.
 */
interface IDetailPresenter {
    fun showBigImg()

    fun start(view: DetailsActivity)

    fun requestData(url: String)


}