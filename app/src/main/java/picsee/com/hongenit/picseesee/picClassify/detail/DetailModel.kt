package picsee.com.hongenit.picseesee.picClassify.detail

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import picsee.com.hongenit.picseesee.picClassify.PicBean
import picsee.com.hongenit.picseesee.picClassify.ZResponse
import picsee.com.hongenit.picseesee.util.LogUtil

/**
 * Created by hongenit on 18/2/2.
 * 请求该图片详情的数据
 */
private val TAG: String = "DetailModel"

class DetailModel {

    companion object {
        fun requestDetails(url: String, index: Int, response: ZResponse) {

            doAsync {
                try {
                    //连接
                    val document: Document = Jsoup.connect(url).ignoreContentType(true).get()
                    val girlPictureList = document.select("div.div-num")
                    val imageList: ArrayList<PicBean> = ArrayList()
                    girlPictureList.forEach {
                        if (it.hasAttr("data-src")) {
                            var imgUrl = it.attr("data-src")
                            imgUrl = imgUrl.substring(0, imgUrl.indexOf("?"))
                            val picBean = PicBean(imgUrl, "", "")
                            LogUtil.e(TAG, picBean.toString())
                            imageList.add(picBean)
                        }
                    }
                    uiThread {
                        response.onSuccess(imageList)
                    }
                } catch (e: Exception) {
                    uiThread {
                        response.onError(e.message)
                    }

                }
            }

        }

    }


}