package picsee.com.hongenit.picseesee.picClassify

/**
 * Created by hongenit on 18/1/31.
 */
interface ZResponse {

    fun onSuccess(picList: ArrayList<AlbumBean>)

    fun onError(msg: String?)
}