# LayoutInflater使用
layout_root.xml
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/frame.layout.root"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

</FrameLayout>
```
item_text.xml
```
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/textView"

    android:layout_width="match_parent"
    android:layout_height="match_parent">

</TextView>
```

```
LinearLayout viewGroupLayout = findViewById(R.id.)
```

//LayoutInflater.java
```
public View inflate(int resource, ViewGroup root, boolean attachToRoot) {

}
```

|       | root      |  attachToRoot |  返回值  |
| :---: | :--:      | :-----------: |:-----:|
| case1 |  null     |     false     |resource对应的View|
| case2 |  null     |     true      |resource对应的View|
| case3 |  not null |     true      |root             |
| case4 |  not null |     false     |resource对应的View|