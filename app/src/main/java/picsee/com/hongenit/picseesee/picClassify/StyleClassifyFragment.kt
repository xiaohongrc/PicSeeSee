package picsee.com.hongenit.picseesee.picClassify

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_area_classify.*
import picsee.com.hongenit.picseesee.R

/**
 * Created by hongenit on 18/1/30.
 * 风格分类fragment
 */
class StyleClassifyFragment : BaseFragment() {

    companion object {

        fun newInstance(): StyleClassifyFragment {
            val fragment = StyleClassifyFragment()
            return fragment
        }
    }

    override fun getFragmentContentId(): Int {
        return R.layout.fragment_area_classify
    }

    override fun initView() {
        vpArea.adapter = AreaVpAdapter(childFragmentManager, getFragmentData())

        tabLayout.setupWithViewPager(vpArea)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun initData() {

    }

    private fun getFragmentData(): ArrayList<ClassifyTypeBean?> {
        val classifyTypeList = ArrayList<ClassifyTypeBean?>()



        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2340_0_0", "美女"))

        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2338_0_0", "明星"))

        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2341_0_0", "风景"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2342_0_0", "汽车"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2343_0_0", "可爱"))

        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2346_0_0", "动漫"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2351_0_0", "非主流"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2355_0_0", "宠物"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2349_0_0", "卡通"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2359_0_0", "治愈系"))

//        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/gqbz/","壁纸"))
//        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/dmtp/","动漫"))
//        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/qctp/","汽车"))
//        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/bjtp/","背景"))
//        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/fzltp/","非主流"))

        return getDisOrderList(classifyTypeList)
    }

    /**
     * ji将ArrayList乱序
     */
    fun getDisOrderList(orderList: ArrayList<ClassifyTypeBean?>): ArrayList<ClassifyTypeBean?> {
        val size = orderList.size
        val tempList = ArrayList<ClassifyTypeBean?>()


        for (i in size downTo 1) {
            val ran = (Math.random() * i).toInt()
            println(ran)
            val randomValue = orderList.removeAt(ran)
            println(randomValue)
            tempList.add(randomValue)
        }

        return tempList

    }
}