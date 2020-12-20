//package com.peter.viewgrouptutorial
//
//import android.app.ActivityOptions
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Trace
//import android.transition.ChangeBounds
//import android.transition.ChangeImageTransform
//import android.transition.TransitionSet
//import android.util.Pair
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.annotation.RequiresApi
//import com.squareup.picasso.Picasso
//
//class PhotoListActivity : AppCompatActivity() {
//    private lateinit var mListView: ListView
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val transitionSet = TransitionSet()
//        transitionSet.addTransition(ChangeBounds().apply { interpolator = BetterBounceInterpolator(ActivityOptionsActivity.count,ActivityOptionsActivity.factor) })
//        transitionSet.addTransition(ChangeImageTransform().apply { interpolator = BetterBounceInterpolator(ActivityOptionsActivity.count,ActivityOptionsActivity.factor) })
//        transitionSet.setMatchOrder(TransitionSet.MATCH_ITEM_ID)
//        window.sharedElementEnterTransition = transitionSet
//        setContentView(R.layout.activity_list_view)
//        mListView = findViewById(R.id.list_view)
//        if (intent.getIntExtra("imageResource", 0) != 0) {
//            mListView.adapter = PhotoAdapter(
//                this,
//                arrayOf(
//                    R.drawable.xuanyu2,
//                    R.drawable.xuanyu1,
//                    R.drawable.xuanyu3,
//                    R.drawable.xuanyu4,
//                    R.drawable.xuanyu5,
//                    R.drawable.xuanyu6,
//                    R.drawable.xuanyu7,
//                    R.drawable.xuanyu8,
//                    R.drawable.xuanyu9
//                )
//            )
//        } else {
//            mListView.adapter = PhotoAdapter(
//                this,
//                arrayOf(
//                    R.drawable.xuanyu1,
//                    R.drawable.xuanyu2,
//                    R.drawable.xuanyu3,
//                    R.drawable.xuanyu4,
//                    R.drawable.xuanyu5,
//                    R.drawable.xuanyu6,
//                    R.drawable.xuanyu7,
//                    R.drawable.xuanyu8,
//                    R.drawable.xuanyu9
//                )
//            )
//        }
//        mListView.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                val activityOptions =
//                    ActivityOptions.makeSceneTransitionAnimation(this, Pair(view, "xuanyu"))
//                val intent = Intent(this, PhotoDetailActivity::class.java)
////                val intent = Intent(this, PhotoListActivity::class.java)
//                intent.putExtra("imageResource", mListView.adapter.getItem(position) as Int)
//                startActivity(intent, activityOptions.toBundle())
//            }
//    }
//
//    internal class PhotoAdapter(val context: Context, val photos: Array<Int>) : BaseAdapter() {
//        private val mLayoutInflater: LayoutInflater
//
//        init {
//            mLayoutInflater = LayoutInflater.from(context)
//        }
//
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//            Trace.beginSection("getView")
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
//            Trace.endSection()
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
//            return  true
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
//}