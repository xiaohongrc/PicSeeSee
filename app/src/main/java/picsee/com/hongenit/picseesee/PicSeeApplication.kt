package picsee.com.hongenit.picseesee

import android.app.Application
import android.content.Context
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import picsee.com.hongenit.picseesee.util.LogUtil


/**
 * Created by hongenit on 18/2/1.
 * function:
 */
class PicSeeApplication : Application() {
    val TAG = "PicSeeApplication"
    override fun onCreate() {
        sContext = applicationContext
        val start = System.currentTimeMillis()
        println(start)
        initUmeng()
        initUmengPush()

        super.onCreate()

    }

    private fun initUmengPush() {
        val mPushAgent = PushAgent.getInstance(this)
        //注册推送服务，每次调用register方法都会回调该接口
        LogUtil.d(TAG, "initUmengPush")

        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                //注册成功会返回device token
                LogUtil.d(TAG, "deviceToken = $deviceToken")
            }

            override fun onFailure(s: String, s1: String) {
                LogUtil.d(TAG, "push error = $s  ==== $s1 ")
            }
        })
    }


    private fun initUmeng() {
        UMConfigure.init(this, "5a76fd12f43e482b7600039f", "tencent", UMConfigure.DEVICE_TYPE_PHONE, "7949ff424302b34a1fb5ffc0d2cec3b9")
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
        UMConfigure.setLogEnabled(true)
    }

    companion object {
        lateinit var sContext: Context

        fun getAppContext(): Context? {
            return sContext
        }

    }

}