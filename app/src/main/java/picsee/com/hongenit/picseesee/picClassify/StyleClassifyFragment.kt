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


        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2339_0_0","节日"))
        classifyTypeList.add(ClassifyTypeBean("http://www.win4000.com/mobile_2346_0_0","动漫"))
        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/mxtp/","明星"))
        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/gqbz/","壁纸"))
        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/dmtp/","动漫"))
        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/qctp/","汽车"))
        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/bjtp/","背景"))
        classifyTypeList.add(ClassifyTypeBean("http://www.msgao.com/wmtp/fzltp/","非主流"))


        return classifyTypeList
    }


}