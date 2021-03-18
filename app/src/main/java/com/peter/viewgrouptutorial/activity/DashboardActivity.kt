package com.peter.viewgrouptutorial.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.ClipChildrenActivity
import com.peter.viewgrouptutorial.R
import com.peter.viewgrouptutorial.bean.HeaderItem
import com.peter.viewgrouptutorial.bean.RouteItem
import com.peter.viewgrouptutorial.coordinatorlayout.*
import com.peter.viewgrouptutorial.coordinatorlayout.jd.JdStickyHeaderAppBarLayoutActivity
import com.peter.viewgrouptutorial.dispatchevent.*
import com.peter.viewgrouptutorial.dispatchevent.CoordinatorLayoutActivity
import com.peter.viewgrouptutorial.expandtouch.ExpandTouchActivity1
import com.peter.viewgrouptutorial.expandtouch.ExpandTouchActivity2
import com.peter.viewgrouptutorial.expandtouch.ExpandTouchActivity3
import com.peter.viewgrouptutorial.expandtouch.ExpandTouchActivity4
import com.peter.viewgrouptutorial.fragments.FragmentActivity1
import com.peter.viewgrouptutorial.fragments.FragmentActivity2
import com.peter.viewgrouptutorial.jetpack.navigation.NavigationActivity
import com.peter.viewgrouptutorial.measure.*
import com.peter.viewgrouptutorial.nestedscroll.*
import com.peter.viewgrouptutorial.offsetproblem.*
import com.peter.viewgrouptutorial.popupwindow.PopupWindowActivity
import com.peter.viewgrouptutorial.recyclerview.*
import com.peter.viewgrouptutorial.stickyheader.MyRecyclerViewActivity
import com.peter.viewgrouptutorial.textview.PromiseTextViewActivity
import com.peter.viewgrouptutorial.textview.TransformationTextViewActivity
import com.peter.viewgrouptutorial.viewpager2.*
import com.xuanyu.stickyheader.BaseStickyHeaderModel
import com.xuanyu.stickyheader.StickyHeaderAdapter
import com.xuanyu.stickyheader.StickyHeaderHelper
import com.xuanyu.stickyheader.StickyHeaderRegistry
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mHeaderLayout: FrameLayout? = null
    private var mMainAdapter: MainAdapter? = null
    private val mRouteItems: MutableList<Any> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mRecyclerView = findViewById(R.id.main_recycler_view)
        mHeaderLayout = findViewById(R.id.header_layout)
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        addExpandTouch()
        addPopupWindow()
        addRecyclerView()
        addOffsetProblem()
        addCoordinatorEvent()

        addFragment()
        addViewPager2()
//        addTextView()
        addDispatchTouchEvent()
        addMeasure()
        addNestedScroll()
        addMaterialDesign()
        addJetpack()

        mMainAdapter = MainAdapter(mRouteItems)
        mRecyclerView!!.adapter = mMainAdapter
        mRecyclerView!!.setItemViewCacheSize(10)
        StickyHeaderHelper.init<Any>(mRecyclerView, mHeaderLayout, 0)
        StickyHeaderRegistry.registerTransfer(
            HeaderItem::class.java,
            HeaderStickyHeaderModel::class.java
        )
    }

    private fun addExpandTouch() {
        addHeaderItem("扩大事件区域")
        addRouteItem("单个按钮扩大点击区域", "单个按钮扩大点击区域", ExpandTouchActivity1::class.java)
        addRouteItem("单个按钮扩大点击区域", "扩大点击区域周边有Button", ExpandTouchActivity2::class.java)
        addRouteItem("多个按钮扩大点击区域", "多个按钮设置扩大点击区域", ExpandTouchActivity3::class.java)
        addRouteItem("突破限制", "加减购扩大点击区域", ExpandTouchActivity4::class.java)
    }

    private fun addPopupWindow() {
        addHeaderItem("对话框")
        addRouteItem("从点击出弹出对话框", "从点击出弹出对话框直到居中", PopupWindowActivity::class.java)
    }

    private fun addOffsetProblem() {
        addHeaderItem("OffsetProblem")
        addRouteItem("还原offset现场", "简单Demo还原Offset不偏移问题", OffsetProblemActivity::class.java)
        addRouteItem("定位问题", "默认开启硬件加速 offset方法会触发哪些操作", OffsetProblemConfirmActivity::class.java)
        addRouteItem("定位问题", "关闭硬件加速后 offset方法会触发哪些操作", OffsetProblemConfirmActivity2::class.java)
        addRouteItem("解决问题", "使用View.post解决问题", FixOffsetProblemActivityWithPost::class.java)
        addRouteItem("解决问题", "使用View.post和alpha解决问题", FixOffsetProblemActivityWithAlpha::class.java)
        addRouteItem("解决问题", "用View.post和preDrawListener解决问题", FixOffsetProblemActivityWithPreDraw::class.java)

    }

    private fun addFragment() {
        addHeaderItem("Fragment Test")
        addRouteItem(
            "有回退栈Fragment",
            "FragmentActivity1",
            FragmentActivity1::class.java
        )
        addRouteItem(
            "没有回退栈Fragment",
            "FragmentActivity2",
            FragmentActivity2::class.java
        )
    }

    private fun addCoordinatorEvent() {
        addHeaderItem("CoordinatorLayoutEvent")
        addRouteItem(
            "FloatingActionButton",
            "FloatingActionButton",
            FloatScrollingActivity::class.java
        )
        addRouteItem(
            "场景一",
            "所有的Behavior和View都不拦截事件不处理事件",
            CoordinatorLayoutEventOneActivity::class.java
        )
        addRouteItem(
            "场景二",
            "Vp2 Behavior onIntercept返回true onTouch返回false",
            CoordinatorLayoutEventTwoActivity::class.java
        )
        addRouteItem(
            "场景三",
            "Vp2 Behavior onIntercept返回false onTouch返回true",
            CoordinatorLayoutEventThreeActivity::class.java
        )
        addRouteItem(
            "场景四",
            "Vp2 Behavior onIntercept返回true onTouch返回true",
            CoordinatorLayoutEventFourActivity::class.java
        )
        addRouteItem(
            "场景五",
            "使用Layout_Anchor",
            CoordinatorLayoutAnchorActivity::class.java
        )
        addRouteItem(
            "场景六",
            "Dependency 案例",
            CoordinatorLayoutDependentActivity::class.java
        )
        addRouteItem(
            "场景七",
            "BottomSheetBehavior",
            BottomSheetBehaviorActivity::class.java
        )
        addRouteItem(
            "场景八",
            "仿高德地图",
            GaodeActivity::class.java
        )
        addRouteItem(
            "场景9",
            "BottomBehavior不在topmost之一",
            BottomSheetBehaviorActivity2::class.java
        )

        addRouteItem(
            "场景10",
            "BottomBehavior不在topmost之二",
            BottomSheetBehaviorActivity3::class.java
        )
        addRouteItem(
            "场景11",
            "BackgroundTint",
            BottomSheetBehaviorActivity4::class.java
        )
        addRouteItem(
            "场景12",
            "BottomSheetBehavior固定高度",
            BottomSheetBehaviorActivity5::class.java
        )
    }

    private fun addTextView() {
        addHeaderItem("TextView")
        addRouteItem(
            "TextView场景一",
            "TextView Transformation",
            TransformationTextViewActivity::class.java
        )
        addRouteItem("TextView场景二", "Promise", PromiseTextViewActivity::class.java)
    }

    private fun addMaterialDesign() {
        addHeaderItem("MaterialDesign")
        addRouteItem("MaterialDesign场景一", "AppBarLayout使用", AppbarLayoutExampleActivity::class.java)
        addRouteItem(
            "MaterialDesign场景二",
            "仿京东分类搜索功能",
            JdStickyHeaderAppBarLayoutActivity::class.java
        )
        addRouteItem(
            "MaterialDesign场景三",
            "LayoutAnchor",
            CoordinatorLayoutAnchorActivity::class.java
        )

        addRouteItem(
            "MaterialDesign场景四",
            "MD事件分发",
            CoordinatorEventActivity::class.java
        )
    }

    private fun addViewPager2() {
        addHeaderItem("ViewPager2 测试")

        addRouteItem(
            "ViewPager2 With Views",
            "ViewPager2 With Views",
            ViewPager2RecyclerViewActivity::class.java
        )
        addRouteItem(
            "ViewPager2 With Fragments",
            "ViewPager2 With Fragments",
            ViewPager2WithFragmentsActivity::class.java
        )
        addRouteItem(
            "CardViewActivity",
            "CardViewActivity",
            CardViewActivity::class.java
        )
        addRouteItem(
            "CardFragmentActivity",
            "CardFragmentActivity",
            CardFragmentActivity::class.java
        )

        addRouteItem(
            "MutableCollectionFragmentActivity",
            "MutableCollectionFragmentActivity",
            MutableCollectionFragmentActivity::class.java
        )

        addRouteItem(
            "MutableCollectionViewActivity",
            "MutableCollectionViewActivity",
            MutableCollectionViewActivity::class.java
        )


        addRouteItem(
            "CardViewTabLayoutActivity",
            "CardViewTabLayoutActivity",
            CardViewTabLayoutActivity::class.java
        )

        addRouteItem(
            "FakeDragActivity",
            "FakeDragActivity",
            FakeDragActivity::class.java
        )

        addRouteItem(
            "PageTransformerActivity",
            "PageTransformerActivity",
            PageTransformerActivity::class.java
        )

        addRouteItem(
            "PreviewPagesActivity",
            "PreviewPagesActivity",
            PreviewPagesActivity::class.java
        )

        addRouteItem(
            "ParallelNestedScrollingActivity",
            "ParallelNestedScrollingActivity",
            ParallelNestedScrollingActivity::class.java
        )

        addRouteItem(
            "BrowseActivity",
            "BrowseActivity",
            BrowseActivity::class.java
        )
    }

    private fun addRecyclerView() {
        addHeaderItem("RecyclerView测试")
        addRouteItem(
            "ClipChild",
            "ClipChild",
            ClipChildrenActivity::class.java
        )
        addRouteItem(
            "Drag Move",
            "测试Drag Move",
            DragAndSwipeRecyclerViewActivity::class.java
        )
        addRouteItem(
            "ItemDecoration",
            "测试ItemDecoration",
            ItemDecorationRecyclerViewActivity::class.java
        )
        addRouteItem(
            "删除动画",
            "每次删除两个Item",
            RemoveItemsRecyclerViewActivity::class.java
        )
        addRouteItem(
            "增加动画",
            "每次增加两个Item",
            InsertItemsRecyclerViewActivity::class.java
        )
        addRouteItem(
            "update动画",
            "每次update两个Item",
            UpdateItemsRecyclerViewActivity::class.java
        )
        addRouteItem(
            "Move动画",
            "每次move两个Item",
            MoveItemsRecyclerViewActivity::class.java
        )
        addRouteItem(
            "mix动画",
            "测试mix",
            MixsItemsRecyclerViewActivity::class.java
        )

        addRouteItem(
            "NotifyData None Stable Id",
            "NotifyData None Stable Id",
            NotifyNoneStableIdRecyclerViewActivity::class.java
        )
        addRouteItem(
            "NotifyData With Stable Id",
            "NotifyData With Stable Id",
            NotifyStableIdRecyclerViewActivity::class.java
        )
        addRouteItem(
            "ItemLongClick",
            "ItemLongClick Use ItemTouch",
            ItemLongClickRecyclerViewActivity::class.java
        )
        addRouteItem(
            "LinearSnap",
            "LinearSnap",
            LinearSnapRecyclerViewActivity::class.java
        )
        addRouteItem(
            "ViewPagerSnap",
            "ViewPagerSnap",
            ViewPagerSnapRecyclerViewActivity::class.java
        )

        addRouteItem("RecyclerView场景一", "博客文章场景一", RecyclerViewActivity1::class.java)
        addRouteItem("RecyclerView场景二", "博客文章场景二", RecyclerViewActivity2::class.java)
        addRouteItem("RecyclerView场景三", "RecyclerView动画", RecyclerViewActivityAnimate::class.java)
        addRouteItem("RecyclerView场景四", "添加数据", MyRecyclerViewActivity::class.java)
        addRouteItem("RecyclerView场景五", "嵌套RecyclerView", RecyclerViewNestedActivity::class.java)
        addRouteItem("RecyclerView场景六", "DiffUtil", RecyclerViewActivityDiffUtil::class.java)
        addRouteItem(
            "RecyclerView场景七",
            "NotifyDataSetChanged",
            RecyclerViewActivityNotify::class.java
        )
        addRouteItem(
            "RecyclerView场景八",
            "LayoutManager相关",
            RecyclerViewActivityComplexLayout::class.java
        )

    }

    private fun addNestedScroll() {
        addHeaderItem("嵌套滑动")
        addRouteItem("嵌套滑动场景一", "嵌套滑动FrameLayoutTree", ScrollOneActivity::class.java)
        addRouteItem("嵌套滑动场景二", "两个ScrollView启动嵌套滑动", ScrollTwoActivity::class.java)
        addRouteItem("嵌套滑动场景三", "自己实现了Parent和Child", ScrollThreeActivity::class.java)
        addRouteItem("嵌套滑动场景四", "ScrollingChild启动嵌套滑动", ScrollFourActivity::class.java)
        addRouteItem("嵌套滑动场景五", "嵌套ScrollView开启嵌套滑动 测量", ScrollingFiveActivity::class.java)
        addRouteItem("嵌套滑动场景六", "嵌套ScrollView不开启嵌套滑动", ScrollSixActivity::class.java)
    }


    private fun addJetpack() {
        addHeaderItem("Jetpack库研究")
        addRouteItem("Jetpack库之Navigation", "Navigation库研究", NavigationActivity::class.java)

    }

    private fun addMeasure() {
        //增加测量DEMO
        addHeaderItem("View 测量")

        addRouteItem("测量场景一", "两个ScrollView不启动嵌套滑动", MeasureFrameLayoutTreeActivity::class.java)

        addRouteItem("测量场景二", "测量LinearLayoutTree", MeasureLinearLayoutTreeActivity::class.java)

        addRouteItem("测量场景三", "测量RelativeLayoutTree", MeasureRelativeLayoutTreeActivity::class.java)

        addRouteItem("测量场景四", "View 测量", ViewMeasureActivity::class.java)

        addRouteItem("测量场景五", "TextView 测量", TextViewMeasureActivity::class.java)
    }

    private fun addDispatchTouchEvent() {
        addHeaderItem("事件分发")

        addRouteItem(
            "事件分发场景一", "所有的View都不分发事件",
            TouchOneActivity::class.java
        )
        addRouteItem("事件分发场景二", "只有View7分发事件", TouchTwoActivity::class.java)
        addRouteItem("事件分发场景三", "Vp3 dispatchTouchEvent返回true", TouchThreeActivity::class.java)
        addRouteItem("事件分发场景四", "Vp1下半部分拦截事件", TouchFourActivity::class.java)
        addRouteItem(
            "事件分发场景五",
            "两个层级myFrameLayout 和MyView。MyView分发事件，而且高度只有100px",
            TouchFiveActivity::class.java
        )
        addRouteItem("事件分发场景六", "ScrollView嵌套滑动冲突", ScrollConflictActivity::class.java)
        addRouteItem("事件分发场景七", "CoordinatorLayout事件分发", CoordinatorLayoutActivity::class.java)
    }

    private fun addRouteItem(title: String, description: String, clazz: Class<*>) {
        val routeItem = RouteItem(title, description, clazz)
        mRouteItems.add(routeItem)
    }

    private fun addHeaderItem(title: String) {
        val headerItem = HeaderItem(title)
        mRouteItems.add(headerItem)
    }

    private class MainAdapter(val items: List<Any>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderAdapter<Any> {
        val ROUTE_ITEM = 0
        val HEADER_ITEM = 1
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == ROUTE_ITEM) {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_route, parent, false)
                RouteViewHolder(view)
            } else {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is RouteViewHolder) {
                val routeItem: RouteItem = items[position] as RouteItem
                holder.mTitleTextView.text = routeItem.mainTitle
                holder.mDescriptionTextView.text = routeItem.subTitle
                holder.itemView.setOnClickListener { view ->
                    val intent = Intent(view.context, routeItem.activityClazz)
                    view.context.startActivity(intent)
                }
            } else if (holder is HeaderViewHolder) {
                val headerItem: HeaderItem = items[position] as HeaderItem
                holder.mTitleTextView.text = headerItem.title
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun getItemViewType(position: Int): Int {
            val item = items[position]
            if (item is RouteItem) {
                return ROUTE_ITEM
            } else if (item is HeaderItem) {
                return HEADER_ITEM
            }
            return -1
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun transferToStickyHeaderModel(position: Int): BaseStickyHeaderModel<Any>? {
            return StickyHeaderHelper.transferToStickyHeaderModel(this, position)
        }

    }

    private class RouteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var mTitleTextView: TextView = itemView.findViewById(R.id.main_title)
        var mDescriptionTextView: TextView = itemView.findViewById(R.id.main_description)
    }

    private class HeaderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var mTitleTextView: TextView = itemView.findViewById(R.id.header_text)

    }
}