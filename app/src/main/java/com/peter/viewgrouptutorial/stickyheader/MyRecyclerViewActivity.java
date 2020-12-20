package com.peter.viewgrouptutorial.stickyheader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.peter.viewgrouptutorial.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

import static androidx.recyclerview.widget.GridLayoutManager.*;

public class MyRecyclerViewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AnimalsAdapter animalsAdapter;
//    MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,10);
//        linearLayoutManager.setSpanSizeLookup(new SpanSizeLookup(){
//
//            @Override
//            public int getSpanSize(int position) {
//                if(position==0)return 10;
//                return 5;
//            }
//        });
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager.setItemPrefetchEnabled(false);
        linearLayoutManager.setMeasurementCacheEnabled(false);

        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setItemAnimator(null);
        List<String> list = new ArrayList<>();
        String[] animals = getResources().getStringArray(R.array.animals);
        list.addAll(Arrays.asList(animals));
//        multiTypeAdapter.setItems(list);
//        multiTypeAdapter.register(String.class, new StringViewBinder());
        animalsAdapter = new AnimalsAdapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                System.out.println("MyRecyclerViewActivity onCreateViewHolder --");
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_item, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                System.out.println("MyRecyclerViewActivity onBindViewHolder -- position " + position + " " + getItem(position));
                TextView textView = (TextView) holder.itemView;
                textView.setText(getItem(position));
            }
        };
        animalsAdapter.addAll(getResources().getStringArray(R.array.animals));
        mRecyclerView.setAdapter(animalsAdapter);
//        mRecyclerView.suppressLayout(true);

//        animalsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                System.out.println("adapter onChanged " + ((TextView) findChildViewUnder(0 ,0)).getText());
//
//                System.out.println("adapter onChanged 11 " + ((TextView) mRecyclerView.getChildAt(0)).getText());
//            }onBindViewHolder
//        });

        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("onGlobalLayout");

                RecyclerView.ItemAnimator itemAnimator = mRecyclerView.getItemAnimator();
                if (itemAnimator != null) {
                    if (!itemAnimator.isRunning()) return;
                    itemAnimator.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() {
                        @Override
                        public void onAnimationsFinished() {
                            System.out.println("layout onChanged 11 " + ((TextView) findChildViewUnder(0, 0)).getText());
                            System.out.println("layout onChanged 22 " + ((TextView) mRecyclerView.getChildAt(0)).getText());
                        }
                    });
                } else {
                    System.out.println("layout onChanged 33 " + ((TextView) findChildViewUnder(0, 0)).getText());
                    System.out.println("layout onChanged 44 " + ((TextView) mRecyclerView.getChildAt(0)).getText());
                }

            }
        });
    }

    public View findChildViewUnder(float x, float y) {
        final int count = mRecyclerView.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            final View child = mRecyclerView.getChildAt(i);
            final float translationX = child.getTranslationX();
            final float translationY = child.getTranslationY();
            if (x >= child.getLeft() + translationX
                    && x <= child.getRight() + translationX
                    && y >= child.getTop() + translationY
                    && y <= child.getBottom() + translationY) {
                return child;
            }
        }
        return null;
    }

    int topCount;
    int bottomCount;
    int inScreenCount;

    public void addTop(View view) {
        System.out.println("MyRecyclerViewActivity addTop " + mRecyclerView.getChildCount());

//        mRecyclerView.getLayoutManager().offsetChildrenVertical(100);

        int count = mRecyclerView.getChildCount();
        int childPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));
        animalsAdapter.add(Math.max(0,childPosition - 2), "New addTop " + topCount++);
        System.out.println("addTop scrollX " + mRecyclerView.getScrollX() + " translateX " + mRecyclerView.getTranslationX());
    }

    public void addBottom(View view) {

        int count = mRecyclerView.getChildCount();
        int childPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(count - 1));
        animalsAdapter.add(childPosition + 1, "New addBottom " + bottomCount++);
    }

    public void addInScreen(View view) {
        int count = mRecyclerView.getChildCount();
        int childPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(count / 2));
        animalsAdapter.add(childPosition, "New addInScreen " + inScreenCount++);
    }

    public void deleteTop(View view) {
        int count = mRecyclerView.getChildCount();
        int childPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));
        animalsAdapter.remove(childPosition);
        System.out.println("addTop scrollX " + mRecyclerView.getScrollX() + " translateX " + mRecyclerView.getTranslationX());
    }

    public void deleteBottom(View view) {
        int count = mRecyclerView.getChildCount();
        int childPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(count - 1));
        animalsAdapter.remove(childPosition + 1);
    }

    public void deleteInScreen(View view) {
//        int count = mRecyclerView.getChildCount();
//        int childPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(count / 2));
//        animalsAdapter.remove(childPosition);
        animalsAdapter.notifyDataSetChanged();
    }

    public void all(View view) {
//        animalsAdapter.remove(animalsAdapter.getItemCount()-1);
//        animalsAdapter.remove(animalsAdapter.getItemCount()-1);
//        animalsAdapter.remove(animalsAdapter.getItemCount()-1);
//        animalsAdapter.remove(animalsAdapter.getItemCount()-1);
            mRecyclerView.requestLayout();
//        animalsAdapter.notifyDataSetChanged();
//        animalsAdapter.remove(1);
//        animalsAdapter.notifyItemRangeChanged(1,3);
//        animalsAdapter.clear();
//        animalsAdapter.addAll(getResources().getStringArray(R.array.animals));
//        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(30, 400);
    }

    public static class StringViewBinder extends ItemViewBinder<String, RecyclerView.ViewHolder> {
        @NonNull
        @Override
        protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            System.out.println("onCreateViewHolder fff--");
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull String item) {
            System.out.println("onBindViewHolder fff--");

            TextView textView = (TextView) holder.itemView;
            textView.setClickable(false);
            textView.setText(item);
        }

    }
}