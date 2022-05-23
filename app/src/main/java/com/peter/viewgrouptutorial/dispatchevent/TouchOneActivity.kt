package com.peter.viewgrouptutorial.dispatchevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.peter.viewgrouptutorial.nestedscroll.Log
// 彻底搞懂Android事件分发
// 1. 将ViewGroup转换成树
// 2. 树的深度遍历(先序->根左右、中序->左根右(多叉树没有)、后序->左右根)
// 先序遍历结果 vp1-> vp2-> v1 -> v2 -> v3 ->vp3 -> v4 -> v5 ->v6
// -> vp4 -> v7 -> v8 ->v9

// 后序遍历结果  v1—> v2 -> v3-> vp2 -> v4 -> v5 -> v6 -> vp3 -> v7
// -> v8 —> v9 -> vp4 -> vp1

// 事件分发遍历顺序 根右左
//vp1 -> vp4 -> view9 -> view8 -> view7 -> vp3 -> view6 ->
// view5 -> view4 -> vp2 -> view3 -> view2 -> view1

// 3. 聊一聊dispatchTouchEvent、onInterceptTouchEvent、onTouchEvent dispatchTransformedTouchEvent 方法的区别

// 4. DOWN事件的分发流程

// 5. MOVE事件的分发流程

// 6. UP事件的分发流程

// 7. CANCEL 事件的产生

// 8 .看源码
class TouchOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_touch_one)
        //所有的view都不分发事件
        val view1 = MyView(this)
        val view2 = MyView(this)
        val view3 = MyView(this)
        val vp2 = MyFrameLayout(this)
        vp2.addView(view1)
        vp2.addView(view2)
        vp2.addView(view3)


        val view4 = MyView(this)
        val view5 = MyView(this)
        val view6 = MyView(this)

        val vp3 = MyFrameLayout(this)
        vp3.addView(view4)
        vp3.addView(view5)
        vp3.addView(view6)

        val view7 = MyView(this)
        val view8 = MyView(this)
        val view9 = MyView(this)

        val vp4 = MyFrameLayout(this)
        vp4.addView(view7)
        vp4.addView(view8)
        vp4.addView(view9)

        val vp1 = MyFrameLayout(this)
        vp1.addView(vp2)
        vp1.addView(vp3)
        vp1.addView(vp4)

        setContentView(vp1)
        view1.name = "view1"
        view2.name = "view2"
        view3.name = "view3"
        view4.name = "view4"
        view5.name = "view5"
        view6.name = "view6"
        view7.name = "view7"
        view8.name = "view8"
        view9.name = "view9"
        vp1.name = "vp1"
        vp2.name = "vp2"
        vp3.name = "vp3"
        vp4.name = "vp4"


        preOrder(vp1)
        println("先序遍历")

        postOrder(vp1)
        println("后序遍历")

        touchEventOrder(vp1)
        println("事件分发遍历")


    }

    //先序 根左右
    private fun preOrder(view: View) {
        print("${(view as? Log)?.getLogName()} -> ")
        if (view is ViewGroup) {
            for (index in 0 until view.childCount) {
                preOrder(view.getChildAt(index))
            }
        }
    }

    //后序 左右根
    private fun postOrder(view: View) {
        if (view is ViewGroup) {
            for (index in 0 until view.childCount) {
                postOrder(view.getChildAt(index))
            }
        }
        print("${(view as? Log)?.getLogName()} -> ")
    }

    private fun touchEventOrder(view: View) {
        print("${(view as? Log)?.getLogName()} -> ")

        if (view is ViewGroup) {
            for (index in view.childCount - 1 downTo 0) {
                touchEventOrder(view.getChildAt(index))
            }
        }
    }


}

