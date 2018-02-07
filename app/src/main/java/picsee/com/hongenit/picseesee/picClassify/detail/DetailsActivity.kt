package picsee.com.hongenit.picseesee.picClassify.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent
import gallerylibrary.CardAdapterHelper
import gallerylibrary.CardScaleHelper
import gallerylibrary.util.BlurBitmapUtils
import gallerylibrary.util.ViewSwitchUtils
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_image_details.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import picsee.com.hongenit.picseesee.BaseActivity
import picsee.com.hongenit.picseesee.R
import picsee.com.hongenit.picseesee.picClassify.PicBean
import picsee.com.hongenit.picseesee.picClassify.commontab.KEY_ARGUMENTS_URL
import picsee.com.hongenit.picseesee.util.ImageLoadUtil
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


/**
 * @author Xiaohong
 * create at 2018/2/1 16:22
 * description：
 */
class DetailsActivity : BaseActivity() {
    val mPicList = arrayListOf<PicBean>()

    fun replaceData(picList: ArrayList<PicBean>) {
        mPicList.clear()
        mPicList.addAll(picList)
        speedRecyclerView.adapter.notifyDataSetChanged()

    }

    private var mUrl: String = ""
    fun initParams() {
        mUrl = intent.getStringExtra(KEY_ARGUMENTS_URL)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initParams()
        initView()
        initData()
    }

    private lateinit var mCardScaleHelper: CardScaleHelper

    private lateinit var mPresenter: IDetailPresenter

    fun initView() {
        mPresenter = DetailPresenter()

//        setToolBar("详情")
        speedRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        speedRecyclerView.adapter = DetailsListAdapter()

        mCardScaleHelper = CardScaleHelper()
        mCardScaleHelper.setCurrentItemPos(0)
        mCardScaleHelper.attachToRecyclerView(speedRecyclerView)

//        initBlurBackground()
    }

    fun initData() {

    }

    override fun onResume() {
        MobclickAgent.onResume(this)
        super.onResume()
        mPresenter.start(this)
        mPresenter.requestData(mUrl)

    }

    fun showLoadingView(show:Boolean){
        loadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun initBlurBackground() {
        speedRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange()
                }
            }
        })

        notifyBackgroundChange()
    }

    private var mBlurRunnable: Runnable? = null

    private var mLastPos: Int = 0

    private fun notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return
        mLastPos = mCardScaleHelper.getCurrentItemPos()
        val imageUrl = mPicList.get(mCardScaleHelper.getCurrentItemPos()).url
        blurView.removeCallbacks(mBlurRunnable)
        doAsync {
            mBlurRunnable = Runnable {
                val bitmap = getbitmap(imageUrl)
                println(bitmap)
                uiThread {
                    ViewSwitchUtils.startSwitchBackgroundAnim(blurView, BlurBitmapUtils.getBlurBitmap(blurView.getContext(), bitmap, 15))
                    blurView.postDelayed(mBlurRunnable, 200)
                }
            }
        }
    }


    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return
     * @throws MalformedURLException
     */
    fun getbitmap(imageUri: String): Bitmap? {
        // 显示网络上的图片
        var bitmap: Bitmap?
        try {
            val myFileUrl = URL(imageUri)
            val conn = myFileUrl
                    .openConnection() as HttpURLConnection
            conn.setDoInput(true)
            conn.connect()
            val inputStream = conn.getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

        } catch (e: Error) {
            e.printStackTrace()
            bitmap = null
        } catch (e: IOException) {
            e.printStackTrace()
            bitmap = null
        }
        return bitmap
    }


    /**
     * 设置toolbar的标题
     *
     * @param mToolbar Toolbar
     * @param title    标题
     */
    fun setToolBar(title: String) {
        //setSupportActionBar之前设置标题
        toolbar.setTitle(title)
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            //让导航按钮显示出来
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            //设置导航按钮图标
            supportActionBar.setDisplayShowHomeEnabled(true)
        }
    }


    inner class DetailsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val mCardAdapterHelper = CardAdapterHelper()

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val itemView = layoutInflater.inflate(R.layout.item_image_details, parent, false)
            mCardAdapterHelper.onCreateViewHolder(parent, itemView)
            return DetailViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            val detailViewHolder = holder as DetailViewHolder
            mCardAdapterHelper.onBindViewHolder(detailViewHolder.itemView, position, mPicList.size)
            val cornerRadius = resources.getDimension(R.dimen.detail_cardview_radius)
            ImageLoadUtil.newInstance()!!.loadRoundImage(this@DetailsActivity, detailViewHolder.itemView.ivDetailPic, mPicList[position].url,cornerRadius,object : ImageLoadUtil.ImageLoadListener{
                override fun onLoadComplete() {
                    detailViewHolder.itemView.loadingView.visibility = View.GONE
                }

                override fun onLoadFailed() {
                    detailViewHolder.itemView.tvLoadImageFail.visibility = View.VISIBLE
                    detailViewHolder.itemView.loadingView.visibility = View.GONE
                }
            })

        }


        override fun getItemCount(): Int {
            return mPicList.size
        }
    }

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }



}