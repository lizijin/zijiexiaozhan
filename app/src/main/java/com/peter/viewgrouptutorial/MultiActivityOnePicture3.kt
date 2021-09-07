
package com.peter.viewgrouptutorial


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MultiActivityOnePicture3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_one_picture3)
     println("MultiActivityOnePicture ${findViewById<View>(R.id.jump_button).background}")
    }

    fun jumpNew(view: View) {
        startActivity(Intent(this,MultiActivityOnePicture4::class.java))
    }
}