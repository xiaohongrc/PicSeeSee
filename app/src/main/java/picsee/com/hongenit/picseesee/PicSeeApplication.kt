package picsee.com.hongenit.picseesee

import android.app.Application
import android.content.Context

/**
 * Created by hongenit on 18/2/1.
 *
 */
class PicSeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext
    }

    companion object {
        var sContext: Context? = null

        fun getAppContext(): Context? {
            return sContext
        }

    }

}