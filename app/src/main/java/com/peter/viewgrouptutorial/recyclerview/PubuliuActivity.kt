package com.peter.viewgrouptutorial.recyclerview

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.peter.viewgrouptutorial.R
import java.lang.reflect.Method
import java.util.*

class PubuliuActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    val list: MutableList<String> = ArrayList()

    private lateinit var mCheckForGapMethod: Method//StaggerGridLayoutManager
    private lateinit var mMarkItemDecorInsetsDirtyMethod: Method//RecyclerView

    init {
        list.add("长恨歌")

        list.add("1汉皇重色思倾国，御宇多年求不得")
        list.add("2杨家有女初长成，养在深闺人未识。 天生丽质难自弃，一朝选在君王侧")
        list.add("3回眸一笑百媚生，六宫粉黛无颜色。 春寒赐浴华清池")
        list.add("4温泉水滑洗凝脂。 侍儿扶起娇无力，始是新承恩泽时。 云鬓花颜金步摇，芙蓉帐暖度春宵")
        list.add("5云鬓花颜金步摇，芙蓉帐暖度春宵。 春宵苦短日高起，从此君王不早朝。 承欢侍宴无闲暇，春从春游夜专夜。 后宫佳丽三千人，三千宠爱在一身。 金屋妆成娇侍夜")
        list.add("6金屋妆成娇侍夜")
        list.add("7玉楼宴罢醉和春。 姊妹弟兄皆列土")
        list.add("8可怜光彩生门户")
        list.add("9遂令天下父母心，不重生男重生女")
        list.add("10骊宫高处入青云，仙乐风飘处处闻。 缓歌慢舞凝丝竹")
        list.add("11尽日君王看不足。 渔阳鼙鼓动地来，惊破《霓裳羽衣曲》。 九重城阙烟尘生")
        list.add("12千乘万骑西南行。 翠华摇摇行复止")
        list.add("13西出都门百余里。 六军不发无奈何，宛转娥眉马前死")
        list.add("14花钿委地无人收")
        list.add("15翠翘金雀玉搔头。 君王掩面救不得，回看血泪相和流")
        list.add("16黄埃散漫风萧索，云栈萦纡登剑阁")
        list.add("17峨嵋山下少人行")
        list.add("18旌旗无光日色薄。 蜀江水碧蜀山青")
        list.add("19圣主朝朝暮暮情。 行宫见月伤心色，夜雨闻铃肠断声")
        list.add("20天旋日转回龙驭，到此踌躇不能去")
        list.add("21马嵬坡下泥土中")
        list.add("22不见玉颜空死处。 君臣相顾尽沾衣，东望都门信马归")
        list.add("23归来池苑皆依旧，太液芙蓉未央柳。 ")
        list.add("24对此如何不泪垂")
        list.add("25春风桃李花开夜，秋雨梧桐叶落时。(花开夜 一作：花开日) 西宫南苑多秋草，落叶满阶红不扫。(南苑 一作：南内) 梨园弟子白发新，椒房阿监青娥老")
        list.add("26夕殿萤飞思悄然，孤灯挑尽未成眠。 迟迟钟鼓初长夜，耿耿星河欲曙天")
        list.add("27鸳鸯瓦冷霜华重")
        list.add("28翡翠衾寒谁与共？ 悠悠生死别经年，魂魄不曾来入梦")
        list.add("29临邛道士鸿都客，能以精诚致魂魄。 为感君王辗转思，遂教方士殷勤觅。 排空驭气奔如电，升天入地求之遍")
        list.add("30排空驭气奔如电，升天入地求之遍")
        list.add("31上穷碧落下黄泉，两处茫茫皆不见。 忽闻海上有仙山")
        list.add("32山在虚无缥缈间。 楼阁玲珑五云起，其中绰约多仙子")
        list.add("33中有一人字太真，雪肤花貌参差是。 金阙西厢叩玉扃，转教小玉报双成")
        list.add("34闻道汉家天子使，九华帐里梦魂惊。 揽衣推枕起徘徊，珠箔银屏迤逦开")
        list.add("35云鬓半偏新睡觉，花冠不整下堂来。  风吹仙袂飘飖举，犹似霓裳羽衣舞")
        list.add("36玉容寂寞泪阑干，梨花一枝春带雨")
        list.add("37阑含情凝睇谢君王，一别音容两渺茫。 昭阳殿里恩爱绝，蓬莱宫中日月长")
        list.add("38回头下望人寰处，不见长安见尘雾。 惟将旧物表深情，钿合金钗寄将去。 钗留一股合一扇，钗擘黄金合分钿。 但令心似金钿坚，天上人间会相见")
        list.add("39但令临别殷勤重寄词，词中有誓两心知。 七月七日长生殿，夜半无人私语时。 在天愿作比翼鸟")
        list.add("40在地愿为连理枝。 天长地久有时尽，此恨绵绵无绝期。 汉皇重色思倾国，御宇多年求不得。 杨家有女初长成，养在深闺人未识")
        list.add("41天生丽质难自弃，一朝选在君王侧。 回眸一笑百媚生，六宫粉黛无颜色")
        list.add("42春寒赐浴华清池，温泉水滑洗凝脂。 侍儿扶起娇无力，始是新承恩泽时。 云鬓花颜金步摇，芙蓉帐暖度春宵")
        list.add("43春宵苦短日高起，从此君王不早朝。 承欢侍宴无闲暇，春从春游夜专夜。 后宫佳丽三千人，三千宠爱在一身")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pubuliu)
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setItemViewCacheSize(0)
        mRecyclerView.layoutManager =
            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)

        mCheckForGapMethod =
            StaggeredGridLayoutManager::class.java.getDeclaredMethod("checkForGaps")
        mCheckForGapMethod.isAccessible = true
        mMarkItemDecorInsetsDirtyMethod =
            RecyclerView::class.java.getDeclaredMethod("markItemDecorInsetsDirty")
        mMarkItemDecorInsetsDirtyMethod.isAccessible = true

//        mRecyclerView.itemAnimator = null

        //打开这个注释 需要把mRecyclerView.addOnScrollListener注释掉
        mRecyclerView.itemAnimator = object : DefaultItemAnimator() {
            override fun animateMove(
                holder: RecyclerView.ViewHolder?,
                fromX: Int,
                fromY: Int,
                toX: Int,
                toY: Int
            ): Boolean {
                val text = holder?.itemView?.findViewById<TextView>(R.id.pubuliu_text)?.text
                val spanIndex =
                    (holder?.itemView?.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
                println("zijiexiaozhan animate $text index $spanIndex")
                return super.animateMove(holder, fromX, fromY, toX, toY)
            }
        }

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val result = mCheckForGapMethod.invoke(mRecyclerView.layoutManager) as Boolean
                if (result) {
                    println("zijiexiaozhan invoke mMarkItemDecorInsetsDirtyMethod")
                    mMarkItemDecorInsetsDirtyMethod.invoke(mRecyclerView)
                }
            }
        })
        mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                var layoutParams = view.layoutParams
                if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                    val text = view.findViewById<TextView>(R.id.pubuliu_text).text
                    val spanIndex = layoutParams.spanIndex
                    println("zijiexiaozhan getItemOffsets $text index $spanIndex")
                    when (layoutParams.spanIndex) {
                        0 -> {
                            outRect.left = 10.dp(this@PubuliuActivity)
                            outRect.right = 5.dp(this@PubuliuActivity)
                        }
                        3 -> {
                            outRect.right = 10.dp(this@PubuliuActivity)
                            outRect.left = 5.dp(this@PubuliuActivity)
                        }
                        else -> {
                            outRect.left = 5.dp(this@PubuliuActivity)
                            outRect.right = 5.dp(this@PubuliuActivity)
                        }
                    }
                }
            }
        })
        mRecyclerView.adapter = MyAdapter(list)
    }

    inner class MyAdapter(val mStrings: MutableList<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pubuliu_view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView 场景一 onBindViewHolder $position ")
            val textView = holder.itemView.findViewById(R.id.pubuliu_text) as TextView
            textView.text = mStrings[position]
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).also {
                it.isFullSpan = (position == 0)
            }

        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            super.onViewRecycled(holder)
        }

    }

    fun refresh(view: View) {
        mRecyclerView.adapter?.notifyDataSetChanged()
    }

    fun Int.dp(context: Context): Int {
        return (context.resources.displayMetrics.density * this).toInt()
    }

}