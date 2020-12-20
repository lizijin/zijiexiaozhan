package com.peter.viewgrouptutorial.stickyheader;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.peter.viewgrouptutorial.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Button button = (Button) findViewById(R.id.button_update);
        final ToggleButton isReverseButton = (ToggleButton) findViewById(R.id.button_is_reverse);

        // Set adapter populated with example dummy data
        final AnimalsHeadersAdapter adapter = new AnimalsHeadersAdapter();
//        adapter.register(String.class, new StringViewBinder());
//    adapter.add("Animals below!");
        List<String> items = new ArrayList<>();
        for(String s:getDummyDataSet()){
        items.add(s);}
//    adapter.setItems(items);
        adapter.addAll(items);
        recyclerView.setAdapter(adapter);

        // Set button to update all views one after another (Test for the "Dance")
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    final int index = i;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyItemChanged(index);
                        }
                    }, 50);
                }
            }
        });

        // Set layout manager
        int orientation = getLayoutManagerOrientation(getResources().getConfiguration().orientation);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, orientation, isReverseButton.isChecked());
        recyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
//    recyclerView.addItemDecoration(new DividerDecoration(this));

        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecor);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Toast.makeText(MainActivity.this, "Header position: " + position + ", id: " + headerId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.addOnItemTouchListener(touchListener);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.remove(adapter.getItem(position));
                Toast.makeText(MainActivity.this, "onItemClick: " + position + ", id: ",
                        Toast.LENGTH_SHORT).show();
            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

        isReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = isReverseButton.isChecked();
                isReverseButton.setChecked(isChecked);
                layoutManager.setReverseLayout(isChecked);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private String[] getDummyDataSet() {
        return getResources().getStringArray(R.array.animals);
    }

    private int getLayoutManagerOrientation(int activityOrientation) {
        if (activityOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            return LinearLayoutManager.VERTICAL;
        } else {
            return LinearLayoutManager.HORIZONTAL;
        }
    }

    private class AnimalsHeadersAdapter extends AnimalsAdapter
            implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.view_item, parent, false);
      return new RecyclerView.ViewHolder(view) {
      };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      TextView textView = (TextView) holder.itemView;
      textView.setText(getItem(position));
    }

        @Override
        public long getHeaderId(int position) {
//      if (position == 0) {
//        return -1;
//      } else {
            return getItemId(position);
//      }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_header, parent, false);
//      ((ViewGroup)parent.getParent()).addView(view,1,1);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
//            TextView textView = (TextView) holder.itemView;
//            textView.setText(String.valueOf(((String) getItems().get(position)).charAt(0)));
//            holder.itemView.setBackgroundColor(getRandomColor());
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Click", Toast.LENGTH_SHORT).show();
//                }
//            });

        }

        private int getRandomColor() {
            SecureRandom rgen = new SecureRandom();
            return Color.HSVToColor(150, new float[]{
                    rgen.nextInt(359), 1, 1
            });
        }

    }

    private class StringViewBinder extends ItemViewBinder<String, MyViewHolder> {
        @Override
        protected long getItemId(@NonNull String item) {
            return item.charAt(0);
        }

        @NonNull
        @Override
        protected MyViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        protected void onBindViewHolder(@NonNull MyViewHolder holder, @NonNull String item) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(item);
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
