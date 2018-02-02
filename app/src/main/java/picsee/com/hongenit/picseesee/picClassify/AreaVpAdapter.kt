package picsee.com.hongenit.picseesee.picClassify

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import picsee.com.hongenit.picseesee.picClassify.commontab.CommonTabFragment

/**
 * Created by hongenit on 18/1/30.
 * 地区分类适配器
 */

class AreaVpAdapter(fm: FragmentManager, var fragmentDataList: ArrayList<ClassifyTypeBean?>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return CommonTabFragment.getInstance(fragmentDataList[position]!!.classifyUrl)
    }

    override fun getCount(): Int {
        return fragmentDataList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentDataList[position]!!.title
    }


}