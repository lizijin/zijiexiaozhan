package com.peter.viewgrouptutorial.expandtouch

import android.os.Bundle
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class ExpandTouchActivity4 : AppCompatActivity() {
    lateinit var mContentFrameLayout: FrameLayout
    lateinit var mAddSubtractLayout: LinearLayout
    lateinit var mEditText: EditText
    lateinit var mAddImageView: ImageView
    lateinit var mSubtractImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_touch4)
        mContentFrameLayout = findViewById(R.id.frame_layout_content)
        mAddSubtractLayout = findViewById(R.id.add_subtract_layout)
        mEditText = findViewById(R.id.edit_text)
        mAddImageView = findViewById(R.id.add_image)
        mSubtractImageView = findViewById(R.id.subtract_image)
        mAddSubtractLayout.post {

            val expand: Int = (resources.displayMetrics.density * 40).toInt()
            val subtractTouchDelegateHelper =
                ExpandTouchDelegateHelper(
                    mContentFrameLayout,
                    mAddSubtractLayout,
                    expand,
                    expand,
                    expand,
                    expand
                )
            subtractTouchDelegateHelper.expandTouchDelegate()
        }

        mAddImageView.setOnClickListener {
            mEditText.setText("" + (Integer.parseInt(mEditText.text.toString()) + 1))
        }
        mSubtractImageView.setOnClickListener {
            mEditText.setText("" + (Integer.parseInt(mEditText.text.toString()) - 1))

        }
    }
}