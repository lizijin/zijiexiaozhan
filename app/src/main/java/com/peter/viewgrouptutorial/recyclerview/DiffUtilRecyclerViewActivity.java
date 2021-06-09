package com.peter.viewgrouptutorial.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.peter.viewgrouptutorial.R;

import java.util.ArrayList;
import java.util.List;

public class DiffUtilRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PersonAdapter mPersonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_util_recycler_view);
        mRecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException exception) {

                }
            }
        };
        mRecyclerView.setLayoutManager(layoutManager);
        mPersonAdapter = new PersonAdapter();
        mRecyclerView.setAdapter(mPersonAdapter);
        mPersonAdapter.updateList(DataProvider.getOldPersonList());
        mPersonAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                System.out.println("jiangbin adapter onChanged");
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                System.out.println("jiangbin adapter onItemRangeChanged positionStart " + positionStart + " itemCount " + itemCount);

            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                System.out.println("jiangbin adapter onItemRangeChanged positionStart " + positionStart + " itemCount " + itemCount + " payload " + payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                System.out.println("jiangbin adapter onItemRangeInserted positionStart " + positionStart + " itemCount " + itemCount);

            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                System.out.println("jiangbin adapter onItemRangeRemoved positionStart " + positionStart + " itemCount " + itemCount);

            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                System.out.println("jiangbin adapter onItemRangeMoved fromPosition" + fromPosition + " toPosition " + toPosition + " itemCount " + itemCount);

            }
        });
    }

    public void exchange(View view) {
        mPersonAdapter.updateList(DataProvider.sortByAge(DataProvider.getOldPersonList()));
    }

    public static class Person {
        int id;
        int age;
        String name;

        public Person(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mAgeTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.text_name);
            mAgeTextView = itemView.findViewById(R.id.text_age);
        }
    }

    public static class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {
        private List<Person> persons = new ArrayList<Person>();

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, null);
            return new PersonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
            holder.mAgeTextView.setText("" + persons.get(position).age);
            holder.mNameTextView.setText("" + persons.get(position).name);
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }


        public void updateList(List<Person> newList) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(this.persons, newList));
            this.persons.clear();
            this.persons.addAll(newList);
            diffResult.dispatchUpdatesTo(this);
        }

    }

    public static class MyDiffCallback extends DiffUtil.Callback {

        List<Person> oldPersons;
        List<Person> newPersons;

        public MyDiffCallback(List<Person> newPersons, List<Person> oldPersons) {
            this.newPersons = newPersons;
            this.oldPersons = oldPersons;
        }

        @Override
        public int getOldListSize() {
            return oldPersons.size();
        }

        @Override
        public int getNewListSize() {
            return newPersons.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            boolean areItemsTheSame = oldPersons.get(oldItemPosition).id == newPersons.get(newItemPosition).id;
            System.out.println("oldItemPosition " + oldItemPosition + " newItemPosition " + newItemPosition + " areItemsTheSame " + areItemsTheSame);
            return areItemsTheSame;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            boolean areContentsTheSame = oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
            System.out.println("oldItemPosition " + oldItemPosition + " newItemPosition " + newItemPosition + " areContentsTheSame " + areContentsTheSame);

            return areContentsTheSame;
        }

        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

    public static class DataProvider {

        public static List<Person> getOldPersonList() {
            List<Person> persons = new ArrayList<>();
            persons.add(new Person(1, 20, "John"));
            persons.add(new Person(2, 12, "Jack"));
            persons.add(new Person(3, 8, "Michael"));
            persons.add(new Person(4, 19, "Rafael"));
            return persons;
        }

        public static List<Person> sortByAge(List<Person> oldList) {
//            Collections.sort(oldList, new Comparator<Person>() {
//                @Override
//                public int compare(Person person, Person person2) {
//                    return person.age - person2.age;
//                }
//            });
//            return oldList.subList(1,3);
            List<Person> persons = new ArrayList<>();
//            persons.add(new Person(4, 19, "Rafael"));
            persons.add(new Person(1, 20, "John"));
            persons.add(new Person(2, 12, "Jack"));
            persons.add(new Person(3, 8, "Michael"));
            return persons;
        }
    }
}