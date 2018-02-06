package picsee.com.hongenit.picseesee.picClassify.commontab

import android.os.SystemClock
import org.jetbrains.anko.collections.forEachReversedByIndex
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import picsee.com.hongenit.picseesee.picClassify.AlbumBean
import picsee.com.hongenit.picseesee.picClassify.ZResponse
import picsee.com.hongenit.picseesee.util.FileUtil
import picsee.com.hongenit.picseesee.util.LogUtil

/**
 * Created by hongenit on 18/1/31.
 * 获取图片信息的model
 */


private val TAG: String = "CommonTabModel"


//http://www.win4000.com/wallpaper_192_0_0_1.html

class CommonTabModel {
    companion object {
        fun reqOutList(url: String, index: Int, response: ZResponse?) {
            doAsync {
                try {


                    //组装url
                    var buildUrl = buildUrl(url, index)

//                    LogUtil.e(TAG, "buildUrl = $buildUrl")

                    //连接
//                    val document: Document = Jsoup.connect(buildUrl).get()
                    val document: Document = FileUtil.getDocumentByUrl(buildUrl!!)


//                    LogUtil.e(TAG, document.toString())


                    //外层列表的bean集合
                    var outLists: ArrayList<AlbumBean> = arrayListOf()
                    LogUtil.i(TAG, "url = $url")

                    when (getUrlPrefix(url)) {
                        "http://www.msgao" -> outLists = getAlbumList(document)
                        "http://www.win4000" -> outLists = getAlbumList2(document)
                    }

                    LogUtil.i(TAG, outLists.toString())
                    uiThread {
                        response?.onSuccess(outLists)
                    }
                } catch (e: Exception) {
                    uiThread {
                        response?.onError(e.message)
                    }
                }
            }
        }

        private fun getAlbumList(document: Document): ArrayList<AlbumBean> {
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
            val outLists: ArrayList<AlbumBean> = ArrayList()
            htmls.forEach {

                val albumBean = AlbumBean("", "", "")

                val linkElements = it.getElementsByClass("link")
                albumBean.detailLink = linkElements.attr("href")
                //获取图片地址
                val imgSrcs = it.select("img[src\$=.jpg]")
                imgSrcs.forEach {
                    val src = it.attr("src")
                    val alt = it.attr("alt")
                    albumBean.url = src
                    albumBean.title = alt
                }
                //添加bean到集合中
                outLists.add(albumBean)
            }
            return outLists


            /**
            <a href="http://zgnd.msgao.com/dqfl/534977.shtml" class="link">
            <div class="div-img">
            <img src="http://img.gqcsswj.com/upload/resources/image/2016/09/05/42375.jpg" class="img" style="width:234px;height:346px;" alt="伍月yuer-&#32;[TGOD]&#32;嘟嘟" />
            <div class="div-text"><div class="text-title text2">伍月yuer-&#32;[TGOD推]&#32;嘟嘟</div></div>
            <div class="hover"><i class="icon icon-ic-pic-view"></i></div>
            </div></a>
             */
        }


        /**

        <a href="http://www.win4000.com/wallpaper_detail_140523.html" alt="?php echo $v['title']?>" title="《紫罗兰永恒花园》薇尔莉特高清宽屏壁纸" target="_blank">
        <img src="http://static.win4000.com/home/images/placeholder.jpg" data-original="http://pic1.win4000.com/wallpaper/2017-12-20/5a3a31d2d273a_270_185.jpg" alt="《紫罗兰永恒花园》薇尔莉特高清宽屏壁纸" title="《紫罗兰永恒花园》薇尔莉特高清宽屏壁纸"/>
        <p>《紫罗兰永恒花园》薇尔莉特高清宽屏壁纸</p>
        </a>

        <a href="/wallpaper_detail_142534.html" target="_blank">
        <img src="http://static.win4000.com/home/images/placeholder.jpg" data-original = "http://pic1.win4000.com/tj/2018-01-25/5a698ff85af41.jpg" alt="亮丽自然风景图片高清宽屏桌面壁纸" title="亮丽自然风景图片高清宽屏桌面壁纸"/>
        <p>亮丽自然风景图片高清宽屏桌面壁纸</p>
        </a>


        <a href="/wallpaper_detail_142530.html" target="_blank">
        <img src="http://static.win4000.com/home/images/placeholder.jpg" data-original="http://pic1.win4000.com/tj/2018-01-25/5a699062df110.jpg" alt="拙政园唯美冬日雪景高清桌面壁纸" title="拙政园唯美冬日雪景高清桌面壁纸">
        <p>拙政园唯美冬日雪景高清桌面壁纸</p>
        </a>
         */
        private fun getAlbumList2(document: Document): ArrayList<AlbumBean> {
            LogUtil.i(TAG, "getAlbumList2()")
            //筛选首页全部的链接   暂时是第一页的
            val htmls: Elements = document.select("a[href$=.html]")

            //筛选是可以点击进去详情的url
            htmls.forEachReversedByIndex {
                val realUrl = it.getElementsByAttribute("title")
                LogUtil.i(TAG, "size = ${realUrl.size}")
                //不正确的全部移除 只有正式的才有两个"title"属性
                if (realUrl.size < 2) {
                    htmls.remove(it)
                }
            }

            //外层列表的bean集合
            val outLists: ArrayList<AlbumBean> = ArrayList()
            htmls.forEach {

                val albumBean = AlbumBean("", "", "")

                albumBean.detailLink = it.attr("href")
                //获取图片地址
                val imgSrcs = it.select("img[src\$=.jpg]")
                imgSrcs.forEach {
                    val imgUrl = it.attr("data-original")
                    val alt = it.attr("alt")
                    albumBean.url = imgUrl
                    albumBean.title = alt
                }
                //添加bean到集合中
                outLists.add(albumBean)
            }
            return outLists
        }


        /**
         *
         * 根据不同网址组装不同请求url,*号表示占位符
         * eg. http://www.win4000.com/wallpaper_192_0_0_*.html
         * eg. http://www.msgao.com/wmtp/mxtp/index_*.shtml
         *
         */
        private fun buildUrl(url: String, index: Int): String? {
            var buildUrl: String? = null
            var urlPrefix = getUrlPrefix(url)
            when (urlPrefix) {
                "http://www.msgao" -> {
                    buildUrl = "${url}index${if (index == 1) "" else "_$index"}.shtml"
                }

                "http://www.win4000" -> buildUrl = "${url}_$index.html"

            }

            return buildUrl
        }

        private fun getUrlPrefix(url: String): String {
            val indexOf = url.indexOf(".com/", 0, true)
            var urlPrefix = url.substring(0, indexOf)
            return urlPrefix
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