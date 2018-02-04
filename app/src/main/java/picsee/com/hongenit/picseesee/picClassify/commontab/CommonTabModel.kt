package picsee.com.hongenit.picseesee.picClassify.commontab

import android.os.SystemClock
import org.jetbrains.anko.collections.forEachReversedByIndex
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import picsee.com.hongenit.picseesee.picClassify.PicBean
import picsee.com.hongenit.picseesee.picClassify.ZResponse
import picsee.com.hongenit.picseesee.util.FileUtil
import picsee.com.hongenit.picseesee.util.LogUtil

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

//                    LogUtil.e(TAG, "buildUrl = $buildUrl")

                    //连接
                    val document: Document = FileUtil.getDocumentByUrl(buildUrl)


//                    LogUtil.e(TAG, document.toString())
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
                    LogUtil.i(TAG, outLists.toString())
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

//        // 根据url获取HTML文档
//        private fun getDocumentByUrl(buildUrl: String): Document {
//
//            // url的md5值作为缓存文件名
//            val cacheFileMd5 = EnDecryptUtil.md5(buildUrl)
//
//            // 有缓存文件则取出，没有则从服务器获取
//            if (isCacheFileExist(cacheFileMd5)) {
//
//                val htmlStr = ""
//                // 将缓存的字符串转成Document对象
//                return Jsoup.parse(htmlStr)
//            } else {
//                val document = Jsoup.connect(buildUrl).get()
//                cacheDocument(cacheFileMd5,document)
//                return document
//            }
//        }
//
//        // 缓存document
//        private fun cacheDocument(fileName:String,document: Document?) {
//            val file = File(Constants.CACHE_DOCUMENTS,fileName)
//            file.writeText(document.toString(), Charset.forName("utf-8"))
//        }
//
//
//        private fun isCacheFileExist(fileName:String):Boolean{
//            val dirCacheDocument = Constants.CACHE_DOCUMENTS
////            val file = File(dirCacheDocument, fileName)
//            if (dirCacheDocument.exists() && dirCacheDocument.isDirectory() && dirCacheDocument.list() != null) {
//                val fileList = dirCacheDocument.list()
//                if (fileList.contains(fileName)) {
//                    return true
//                }
//            }
//
//            return false
//        }

    }


}