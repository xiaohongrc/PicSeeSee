package picsee.com.hongenit.picseesee.picClassify.commontab

import org.jetbrains.anko.collections.forEachReversedByIndex
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import picsee.com.hongenit.picseesee.picClassify.PicBean
import picsee.com.hongenit.picseesee.picClassify.ZResponse
import picsee.com.hongenit.picseesee.util.LogUtil
import kotlin.concurrent.thread

/**
 * Created by hongenit on 18/1/31.
 * 获取图片信息的model
 */


private val TAG: String = "CommonTabModel"
class CommonTabModel {
    companion object {
        fun reqOutList(url: String, index: Int, response: ZResponse) {
            doAsync {
                try {
                    //组装url
                    var buildUrl = "${url}index${if (index == 1) "" else "_$index"}.shtml"

                    LogUtil.e(TAG, "buildUrl = $buildUrl")

                    //连接
                    val document: Document = Jsoup.connect(buildUrl).get()

                    //筛选首页全部的链接   暂时是第一页的
                    val htmls: Elements = document.select("a[href$=.shtml]")

                    //筛选是可以点击进去详情的url
                    htmls.forEachReversedByIndex {
                        val realUrl = it.getElementsByClass("div-img")
                        //不正确的全部移除
                        if (realUrl.size == 0) {
                            htmls.remove(it)
                        }
                    }

                    //外层列表的bean集合
                    val outLists: ArrayList<PicBean> = ArrayList()
                    htmls.forEach {

                        val picBean = PicBean("", "", "")

                        val linkElements = it.getElementsByClass("link")
                        picBean.detailLink = linkElements.attr("href")
                        //获取图片地址
                        val imgSrcs = it.select("img[src\$=.jpg]")
                        imgSrcs.forEach {
                            val src = it.attr("src")
                            val alt = it.attr("alt")
                            picBean.url = src
                            picBean.title = alt
                        }
                        //添加bean到集合中
                        outLists.add(picBean)
                    }
                    LogUtil.e(TAG, outLists.toString())
                    uiThread {
                        response.onSuccess(outLists)
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