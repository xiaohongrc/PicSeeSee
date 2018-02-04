package picsee.com.hongenit.picseesee.picClassify.commontab

import android.graphics.ColorSpace
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by hongenit on 2018/2/3.
 * 网格布局的间隙
 */
class GridItemDecoration(var space: Int) :RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
//        outRect?.left = space
        outRect?.right = space
        outRect?.bottom = space
//        设置第一个item的top
//        if (parent?.getChildAdapterPosition(view) == 0) {
//            outRect?.top = 120
//        }
    }


}