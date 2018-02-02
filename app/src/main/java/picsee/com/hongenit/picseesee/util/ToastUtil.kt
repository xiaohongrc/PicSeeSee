package picsee.com.hongenit.picseesee.util

import android.widget.Toast
import picsee.com.hongenit.picseesee.PicSeeApplication

/**
 * Created by hongenit on 18/2/1.
 *
 */
object ToastUtil {
    fun showToast(msg: String) {
        Toast.makeText(PicSeeApplication.getAppContext(), msg, Toast.LENGTH_LONG)
    }


}