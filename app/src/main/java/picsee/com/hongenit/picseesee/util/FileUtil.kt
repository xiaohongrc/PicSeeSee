package picsee.com.hongenit.picseesee.util

import android.os.SystemClock
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import picsee.com.hongenit.picseesee.Constants
import java.io.File
import java.nio.charset.Charset

/**
 * Created by hongenit on 2018/2/4.
 *
 */
object FileUtil {

    // 根据url获取HTML文档
    fun getDocumentByUrl(buildUrl: String): Document {
        println("timeee 2000 = "+ SystemClock.currentThreadTimeMillis())

        // url的md5值作为缓存文件名
        val cacheFileMd5 = EnDecryptUtil.md5(buildUrl)
        println("timeee 2001 = "+ SystemClock.currentThreadTimeMillis())

        // 有缓存文件则取出，没有则从服务器获取
        if (isCacheFileExist(cacheFileMd5)) {
            val htmlStr = getDocumentText(cacheFileMd5)
            // 将缓存的字符串转成Document对象
            val document = Jsoup.parse(htmlStr)
            println("timeee 2111 = "+ SystemClock.currentThreadTimeMillis())

            return document
        } else {
            val document = Jsoup.connect(buildUrl).get()
            cacheDocument(cacheFileMd5, document)
            println("timeee 2222 = "+ SystemClock.currentThreadTimeMillis())
            return document
        }
    }

    // 获取缓存的文档文本
    private fun getDocumentText(fileName: String):String {
        if (!isCacheFileExist(fileName)){
            return ""
        }
        val dirCacheDocument = Constants.CACHE_DOCUMENTS
        val file = File(dirCacheDocument, fileName)
        val text = file.readText(Charset.forName("utf-8"))
        return text
    }

    // 缓存document
    private fun cacheDocument(fileName: String, document: Document?) {
        val dirCacheDocument = Constants.CACHE_DOCUMENTS

        if (!dirCacheDocument.exists()) {
            dirCacheDocument.mkdirs()
        }
        if (dirCacheDocument.exists() && dirCacheDocument.isDirectory) {
            println("timeee 55555555555555555 "+ SystemClock.currentThreadTimeMillis())

            val file = File(dirCacheDocument, fileName)
            file.writeText(document.toString(), Charset.forName("utf-8"))
        }
    }


    private fun isCacheFileExist(fileName: String): Boolean {
        val dirCacheDocument = Constants.CACHE_DOCUMENTS
//            val file = File(dirCacheDocument, fileName)
        if (dirCacheDocument.exists() && dirCacheDocument.isDirectory() && dirCacheDocument.list() != null) {
            val fileList = dirCacheDocument.list()
            if (fileList.contains(fileName)) {
                return true
            }
        }
        return false
    }


}