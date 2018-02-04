package picsee.com.hongenit.picseesee.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import picsee.com.hongenit.picseesee.R

/**
 * Created by hongenit on 18/1/31.
 * 图片加载的封装类
 */
class ImageLoadUtil {
    companion object {
        private var instance: ImageLoadUtil? = null
        fun newInstance(): ImageLoadUtil? {
            if (instance == null) {
                instance = ImageLoadUtil()
            }
            return instance
        }
    }

//    val requestOptions = RequestOptions().placeholder(R.drawable.default_img).error(R.drawable.default_img)

    fun loadImage(context: Context, imageView: ImageView, imgUrl: String) {
        val crossFade = DrawableTransitionOptions().crossFade(1000)
        Glide.with(context).load(imgUrl).transition(crossFade).into(imageView)
    }

    fun loadRoundImage(context: Context, imageView: ImageView, imgUrl: String,cornerRadius:Float) {
        val crossFade = DrawableTransitionOptions().crossFade(100)
        val requestOptions = RequestOptions().transform(CornersTransform(context, cornerRadius))
        Glide.with(context).load(imgUrl).transition(crossFade).apply(requestOptions).into(imageView)
    }


}

