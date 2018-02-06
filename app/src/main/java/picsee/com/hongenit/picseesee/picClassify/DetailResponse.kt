package picsee.com.hongenit.picseesee.picClassify

/**
 * Created by hongenit on 18/1/31.
 */
interface DetailResponse {

    fun onSuccess(picList: ArrayList<PicBean>)

    fun onError(msg: String?)
}