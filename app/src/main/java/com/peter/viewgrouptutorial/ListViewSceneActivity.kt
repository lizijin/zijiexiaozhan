//package com.peter.viewgrouptutorial
//
//import android.app.ActivityOptions
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.transition.*
//import android.util.Pair
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.annotation.RequiresApi
//import com.squareup.picasso.Picasso
//
//class ListViewSceneActivity : AppCompatActivity() {
//    private lateinit var mSceneRoot: FrameLayout
//    private lateinit var mScene1: Scene
//    private lateinit var mScene2: Scene
//companion object{
//    var position:Int=0
//}
//    internal class PhotoAdapter(val context: Context, val photos: Array<Int>) : BaseAdapter() {
//        private val mLayoutInflater: LayoutInflater
//
//        init {
//            mLayoutInflater = LayoutInflater.from(context)
//        }
//
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//            var resultView: View? = convertView
//            val viewHolder: ViewHolder
//            if (resultView == null) {
//                resultView = mLayoutInflater.inflate(R.layout.item_photo, parent, false)
//                viewHolder = ViewHolder()
//                viewHolder.mImageView = resultView.findViewById<ImageView>(R.id.photo_view)
//                resultView.tag = viewHolder
//            } else {
//                viewHolder = resultView.tag as ViewHolder
//            }
//            viewHolder.mImageView.transitionName = "xuanyu$position"
////            viewHolder.mImageView.setImageResource(photos[position])
//            Picasso.get().load(photos[position]).into(viewHolder.mImageView);
//            return resultView
//        }
//
//        override fun getItem(position: Int): Any {
//            return photos[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return photos[position].toLong()
//
//        }
//
//        override fun hasStableIds(): Boolean {
////            return super.hasStableIds()
//            return true
//        }
//
//
//        override fun getCount(): Int {
//            return photos.size
//        }
//    }
//
//    internal class ViewHolder {
//        public lateinit var mImageView: ImageView
//    }
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_list_view_scene)
//        val listView1 = LayoutInflater.from(this).inflate(R.layout.listview_scene1, null)
//        val listView2 = LayoutInflater.from(this).inflate(R.layout.listview_scene1, null)
//        mSceneRoot = findViewById(R.id.scene_root)
//        mScene1 = Scene(mSceneRoot, listView1)
//        mScene2 = Scene(mSceneRoot, listView2)
//        (listView1 as ListView).adapter = PhotoAdapter(
//            this,
//            arrayOf(
//                R.drawable.xuanyu1,
//                R.drawable.xuanyu2,
//                R.drawable.xuanyu3,
//                R.drawable.xuanyu4,
//                R.drawable.xuanyu5,
//                R.drawable.xuanyu6,
//                R.drawable.xuanyu7,
//                R.drawable.xuanyu8,
//                R.drawable.xuanyu9
//            )
//        )
//
//        (listView2 as ListView).adapter = PhotoAdapter(
//            this,
//            arrayOf(
//                R.drawable.xuanyu2,
//                R.drawable.xuanyu1,
//                R.drawable.xuanyu4,
//                R.drawable.xuanyu3,
//                R.drawable.xuanyu6,
//                R.drawable.xuanyu5,
//                R.drawable.xuanyu8,
//                R.drawable.xuanyu7,
//                R.drawable.xuanyu9
//            )
//        )
//        (listView2 as ListView).setOnItemClickListener(object : AdapterView.OnItemClickListener {
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val intent = Intent(baseContext, ViewPageActivity::class.java)
//                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(
//                    this@ListViewSceneActivity,
//                    Pair(view, "xuanyu$position")
//                )
//                ListViewSceneActivity.position = position
//                startActivity(intent,activityOptions.toBundle())
//            }
//
//        })
//    }
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    fun goScene1(view: View) {
//        val transition = AutoTransition().apply {
//            setMatchOrder(
//                Transition.MATCH_ITEM_ID,
//                Transition.MATCH_NAME,
//                Transition.MATCH_ID,
//                Transition.MATCH_INSTANCE
//            )
//        }
//        TransitionManager.go(mScene1, transition)
//
//    }
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    fun goScene2(view: View) {
//        val transition = AutoTransition().apply {
////            setMatchOrder(
////                Transition.MATCH_ITEM_ID,
////                Transition.MATCH_NAME,
////                Transition.MATCH_ID,
////                Transition.MATCH_INSTANCE
////            )
//        }
//        TransitionManager.go(mScene2, transition)
//
//    }
//}