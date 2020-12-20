package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.view.View
import com.peter.viewgrouptutorial.bean.HeaderItem
import com.xuanyu.stickyheader.BaseStickyHeaderModel

class HeaderStickyHeaderModel : BaseStickyHeaderModel<HeaderItem>() {
    override fun getStickyView(context: Context): View {
        println("zijiexiaozhan getView")
        return HeaderView(context)
    }

    override fun onBindView(view: View, headerItem: HeaderItem) {
        (view as HeaderView).mTextView.text = headerItem.title
    }
}
