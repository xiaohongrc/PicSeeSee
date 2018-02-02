package picsee.com.hongenit.picseesee.picClassify.commontab

/**
 * Created by hongenit on 18/1/31.
 * common presenter interface
 */
interface ICommonTabPresenter {
    fun setView(fragment: CommonTabFragment)

    // 开启页面，开始请求数据等工作。
    fun start(url: String)

    // 请求数据
    fun requestData(isLoadMore: Boolean, url: String)



}