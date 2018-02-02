package picsee.com.hongenit.picseesee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import picsee.com.hongenit.picseesee.picClassify.AreaClassifyFragment
import picsee.com.hongenit.picseesee.picClassify.StyleClassifyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()

    }

    private fun initView() {
        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main_content, AreaClassifyFragment.newInstance())

        beginTransaction.commit()
    }

    private var mAreaFragment: AreaClassifyFragment? = null

    // 展示默认的地区分类
    private fun showAreaClassify(transaction: FragmentTransaction) {
        if (mAreaFragment == null) {
            mAreaFragment = AreaClassifyFragment.newInstance()
            transaction.add(R.id.fl_main_content, mAreaFragment)
        } else {
            transaction.show(mAreaFragment)
        }

    }

    private fun initData() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        val beginTransaction = supportFragmentManager.beginTransaction()
//        hideAllFragments(beginTransaction)
//        when (item?.itemId) {
//            R.id.area -> {
//                mAreaFragment?.let {
//                    beginTransaction.show(it)
//                } ?: AreaClassifyFragment.newInstance().let {
//                    mAreaFragment = it
//                    beginTransaction.add(mAreaFragment, "area")
//                    beginTransaction.show(it)
//                }
//            }
//            R.id.style -> showStyleClassify(beginTransaction)
//            R.id.mGray -> showAreaClassify(beginTransaction)
//        }

//        beginTransaction.commit()


        return super.onOptionsItemSelected(item)
    }


    private var styleClassifyFragment: StyleClassifyFragment? = null

    // 展示风格分类
    private fun showStyleClassify(transaction: FragmentTransaction) {
        styleClassifyFragment?.let {
            transaction.show(it)
        } ?: StyleClassifyFragment.getInstance().let {
            styleClassifyFragment = it
            transaction.add(R.id.fl_main_content, styleClassifyFragment, getString(R.string.girl_style))
            transaction.show(styleClassifyFragment)
        }
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideAllFragments(transaction: FragmentTransaction) {
        //默认当前这个对象作为闭包的it参数   这里避免了mHomeFragment为null时调用transaction.hide()的问题
        styleClassifyFragment?.let { transaction.hide(it) }
        mAreaFragment?.let { transaction.hide(it) }

    }


}
