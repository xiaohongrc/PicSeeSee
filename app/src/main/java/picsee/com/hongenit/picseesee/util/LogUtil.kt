package picsee.com.hongenit.picseesee.util

import android.util.Log
import kotlin.math.log

/**
 * Created by hongenit on 18/1/29.
 * log util class
 */
object LogUtil {

    const val willLog = true

    fun i(tag: String, message: String) {
        if (willLog) {
            Log.i(tag, message)
        }

    }

    fun v(tag: String, message: String) {
        if (willLog) {
            Log.v(tag, message)
        }

    }

    fun d(tag: String, message: String) {
        if (willLog) {
            Log.d(tag, message)
        }

    }

    fun w(tag: String, message: String) {
        if (willLog) {
            Log.w(tag, message)
        }

    }

    fun e(tag: String, message: String) {
        if (willLog) {
            Log.e(tag, message)
        }

    }


}

