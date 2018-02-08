package picsee.com.hongenit.picseesee.util

import com.umeng.analytics.MobclickAgent
import picsee.com.hongenit.picseesee.PicSeeApplication

/**
 * Created by hongenit on 18/2/8.
 * umeng事件
 */
object EventUtil {
    fun onEvent(action: String, lable: String? = null) {
        if (lable == null) {
            MobclickAgent.onEvent(PicSeeApplication.getAppContext(), action)
        } else {
            MobclickAgent.onEvent(PicSeeApplication.getAppContext(), action, lable)
        }
    }

//    tab_url_click 各个tab下图片的点击总次数
    fun tab_url_click(lable: String?) {
        onEvent("tab_url_click", lable)
    }


    // detail_url_click 图集detail点击次数
    fun detail_url_click(lable: String?) {
        onEvent("detail_url_click", lable)
    }


}