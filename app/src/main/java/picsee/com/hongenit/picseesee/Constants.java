package picsee.com.hongenit.picseesee;

import android.os.Environment;

import java.io.File;

/**
 * Created by hongenit on 2018/2/4.
 * constant values
 */

public interface Constants {

    File APP_DIR = new File(Environment.getExternalStorageDirectory(), ".PicSeeSee");

    // 缓存文档的目录
    File CACHE_DOCUMENTS = new File(APP_DIR, "cache_documents");

}
