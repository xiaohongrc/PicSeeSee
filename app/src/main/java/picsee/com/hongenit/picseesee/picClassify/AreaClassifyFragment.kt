package picsee.com.hongenit.picseesee.picClassify

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_area_classify.*
import picsee.com.hongenit.picseesee.R
import picsee.com.hongenit.picseesee.picClassify.commontab.CommonTabFragment


/**
 * Created by hongenit on 18/1/30.
 * 按地区分类的fragment
 */

class AreaClassifyFragment : BaseFragment() {
    companion object {
        var fragment: AreaClassifyFragment? = null
        fun newInstance(): AreaClassifyFragment? {
            if (fragment == null) {
                fragment = AreaClassifyFragment()
            }
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

        val classifyTypeSet = HashSet<ClassifyTypeBean?>()

        classifyTypeSet.add(ClassifyTypeBean("http://www.msgao.com/wmtp/gqbz/", "高清壁纸"))
        val zgndFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/zgnd/", "中国内地")
        classifyTypeSet.add(zgndFragment)

        val twFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/tw/", "台湾")
        classifyTypeSet.add(twFragment)

        val xgFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/xg/", "香港")
        classifyTypeSet.add(xgFragment)

        val rbFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/rb/", "日本")
        classifyTypeSet.add(rbFragment)

        val hgFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/hg/", "韩国")
        classifyTypeSet.add(hgFragment)

        val mlxyFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/mlxy/", "马来西亚")
        classifyTypeSet.add(mlxyFragment)

        val tgFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/tg/", "泰国")
        classifyTypeSet.add(tgFragment)

        val omFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/om/", "欧美")
        classifyTypeSet.add(omFragment)

        val hxFragment = ClassifyTypeBean("http://www.msgao.com/dqfl/hx/", "混血")
        classifyTypeSet.add(hxFragment)


//        将无序set转成有序list
        var classifyTypeList = ArrayList<ClassifyTypeBean?>()
        for (bean in classifyTypeSet) {
            classifyTypeList.add(bean)
        }
        return classifyTypeList
    }


}
