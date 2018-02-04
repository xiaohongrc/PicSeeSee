package picsee.com.hongenit.picseesee

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import anet.channel.util.Utils.context
import com.umeng.message.PushAgent

/**
 * Created by hongenit on 2018/2/4.
 * BaseActivity
 */
abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        PushAgent.getInstance(context).onAppStart()
        super.onCreate(savedInstanceState)
    }


}