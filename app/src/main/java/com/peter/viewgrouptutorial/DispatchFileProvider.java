package com.peter.viewgrouptutorial;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DispatchFileProvider extends ContentProvider {
    private static final File DEVICE_ROOT = new File("/");
    private static final String TAG_ROOT_PATH = "root-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_EXTERNAL_FILES = "external-files-path";
    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    private static final String TAG_EXTERNAL_MEDIA = "external-media-path";

    public String authority = "com.peter.dispatch";
    Class pathStrategyClass;
    private File filesDir = null;
    private File cacheDir = null;
    private File externalDir = null;
    private File externalCacheDir = null;
    private File externalFilesDir = null;
    private File externalMediaDir = null;
    private Method addRootMethod = null;
    private Constructor constructor = null;
    private HashMap sCache = null;

    private static File buildPath(File base, String... segments) {
        File cur = base;
        for (String segment : segments) {
            if (segment != null) {
                cur = new File(cur, segment);
            }
        }
        return cur;
    }

    private static int modeToMode(String mode) {
        int modeBits;
        if ("r".equals(mode)) {
            modeBits = ParcelFileDescriptor.MODE_READ_ONLY;
        } else if ("w".equals(mode) || "wt".equals(mode)) {
            modeBits = ParcelFileDescriptor.MODE_WRITE_ONLY
                    | ParcelFileDescriptor.MODE_CREATE
                    | ParcelFileDescriptor.MODE_TRUNCATE;
        } else if ("wa".equals(mode)) {
            modeBits = ParcelFileDescriptor.MODE_WRITE_ONLY
                    | ParcelFileDescriptor.MODE_CREATE
                    | ParcelFileDescriptor.MODE_APPEND;
        } else if ("rw".equals(mode)) {
            modeBits = ParcelFileDescriptor.MODE_READ_WRITE
                    | ParcelFileDescriptor.MODE_CREATE;
        } else if ("rwt".equals(mode)) {
            modeBits = ParcelFileDescriptor.MODE_READ_WRITE
                    | ParcelFileDescriptor.MODE_CREATE
                    | ParcelFileDescriptor.MODE_TRUNCATE;
        } else {
            throw new IllegalArgumentException("Invalid mode: " + mode);
        }
        return modeBits;
    }

    public List<ProviderItem> createProviderItems() {
        ArrayList<PathItem> pathItems = new ArrayList<>();
        ArrayList<ProviderItem> providerItems = new ArrayList<>();
        pathItems.add(new PathItem(TAG_FILES_PATH, "file_path", "."));
        pathItems.add(new PathItem(TAG_FILES_PATH, "file_path2", "jiangbin"));
        pathItems.add(new PathItem(TAG_CACHE_PATH, "cache_file_path", "img_cache_file_path"));
        pathItems.add(new PathItem(TAG_EXTERNAL, "bytedance", "toutiao"));

        pathItems.add(new PathItem(TAG_EXTERNAL, "sankuai", "meituan"));
        pathItems.add(new PathItem(TAG_EXTERNAL_FILES, "external_file_path", "img_external_file_path"));
        pathItems.add(new PathItem(TAG_EXTERNAL_CACHE, "external_cache_path", "."));
        ProviderItem providerItem = new ProviderItem("com.peter.viewgrouptutorial", pathItems);
        providerItems.add(providerItem);

        for(int i=1;i<20;i++){
            ProviderItem providerItem1 = new ProviderItem("com.peter.viewgrouptutorial"+i, pathItems);
            providerItems.add(providerItem1);
        }

//        ProviderItem providerItem2 = new ProviderItem("com.peter.viewgrouptutorial2", pathItems);
//        providerItems.add(providerItem2);
//        ProviderItem providerItem3 = new ProviderItem("com.peter.viewgrouptutorial3", pathItems);
//        providerItems.add(providerItem3);
//        ProviderItem providerItem4 = new ProviderItem("com.peter.viewgrouptutorial4", pathItems);
//        providerItems.add(providerItem4);
//        ProviderItem providerItem5 = new ProviderItem("com.peter.viewgrouptutorial5", pathItems);
//        providerItems.add(providerItem5);
//        ProviderItem providerItem6 = new ProviderItem("com.peter.viewgrouptutorial6", pathItems);
//        providerItems.add(providerItem6);
//        ProviderItem providerItem7 = new ProviderItem("com.peter.viewgrouptutorial7", pathItems);
//        providerItems.add(providerItem7);
//        ProviderItem providerItem8 = new ProviderItem("com.peter.viewgrouptutorial8", pathItems);
//        providerItems.add(providerItem8);

        ProviderItem providerItem9 = new ProviderItem("com.peter.install.fileProvider", pathItems);
        providerItems.add(providerItem9);
        ProviderItem providerItem10 = new ProviderItem("com.meituan.install", pathItems);
        providerItems.add(providerItem10);

        ProviderItem providerItem11 = new ProviderItem("com.toutiao.install", pathItems);
        providerItems.add(providerItem11);
        return providerItems;
    }

    @Override
    public boolean onCreate() {
        try {
            Context context = getContext();
            Class fileProviderClass = Class.forName("androidx.core.content.FileProvider");
            Field sCacheField = fileProviderClass.getDeclaredField("sCache");
            sCacheField.setAccessible(true);
            sCache = (HashMap) sCacheField.get(null);

            pathStrategyClass = Class.forName("androidx.core.content.FileProvider$SimplePathStrategy");

            constructor = pathStrategyClass.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            filesDir = context.getFilesDir();//"files-path"
            cacheDir = context.getCacheDir();//"cache-path"
            externalDir = Environment.getExternalStorageDirectory();//external-path
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
            ) {
                File[] externalMediaDirs = context.getExternalMediaDirs();
                if (externalMediaDirs.length > 0) {
                    externalMediaDir = externalMediaDirs[0];
                }
                //external-files-path
                File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
                if (externalFilesDirs.length > 0) {
                    externalFilesDir = externalFilesDirs[0];
                }
            }

            File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context);
            if (externalCacheDirs.length > 0) {
                externalCacheDir = externalCacheDirs[0];
            }
            addRootMethod = pathStrategyClass.getDeclaredMethod("addRoot", String.class, File.class);
            addRootMethod.setAccessible(true);

            List<ProviderItem> providerItems = createProviderItems();
            if (providerItems == null || providerItems.size() == 0) {
                return true;
            }
            for (ProviderItem providerItem : providerItems) {
                addFileProvider(providerItem);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addFileProvider(ProviderItem providerItem) throws
            IllegalAccessException, InstantiationException, InvocationTargetException {
        String s = null;
        Object pathStrategy = constructor.newInstance(providerItem.authorities);
        for (PathItem pathItem : providerItem.pathItems) {
            File target = null;
            String tag = pathItem.tag;
            if (TAG_ROOT_PATH.equals(tag)) {
                target = DEVICE_ROOT;
            } else if (TAG_FILES_PATH.equals(tag)) {
                target = filesDir;
            } else if (TAG_CACHE_PATH.equals(tag)) {
                target = cacheDir;
            } else if (TAG_EXTERNAL.equals(tag)) {
                target = externalDir;
            } else if (TAG_EXTERNAL_FILES.equals(tag)) {
                target = externalFilesDir;
            } else if (TAG_EXTERNAL_CACHE.equals(tag)) {
                target = externalCacheDir;
            } else if (TAG_EXTERNAL_MEDIA.equals(tag)) {
                target = externalMediaDir;
            }

            if (target != null) {
                addRootMethod.invoke(pathStrategy, pathItem.name, buildPath(target, pathItem.path));
            }
        }
        sCache.put(providerItem.authorities, pathStrategy);
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        String encodedPath = uri.getEncodedPath();
        final int splitIndex = encodedPath.indexOf('/', 1);
        String authority = encodedPath.substring(1, splitIndex);
        String path = encodedPath.substring(splitIndex);
        Uri newUri = new Uri.Builder().scheme("content")
                .authority(authority).encodedPath(path).build();

        Object pathStrategy = sCache.get(authority);

        try {
            Method method = pathStrategyClass.getDeclaredMethod("getFileForUri", Uri.class);
            method.setAccessible(true);
            File file = (File) method.invoke(pathStrategy, newUri);
            final int fileMode = modeToMode(mode);
            return ParcelFileDescriptor.open(file, fileMode);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return super.openFile(newUri, mode);
    }

//    public File getFileForUri(Uri uri) {
//        String path = uri.getEncodedPath();
//
//        final int splitIndex = path.indexOf('/', 1);
//        final String tag = Uri.decode(path.substring(1, splitIndex));
//        path = Uri.decode(path.substring(splitIndex + 1));
//
//        final File root = mRoots.get(tag);
//        if (root == null) {
//            throw new IllegalArgumentException("Unable to find configured root for " + uri);
//        }
//
//        File file = new File(root, path);
//        try {
//            file = file.getCanonicalFile();
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
//        }
//
//        if (!file.getPath().startsWith(root.getPath())) {
//            throw new SecurityException("Resolved path jumped beyond configured root");
//        }
//
//        return file;
//    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private static class ProviderItem {
        public String authorities;
        public List<PathItem> pathItems;

        public ProviderItem(String authorities, List<PathItem> pathItems) {
            this.authorities = authorities;
            this.pathItems = pathItems;
        }
    }

    private static class PathItem {
        public String tag;
        public String name;
        public String path;

        public PathItem(String tag, String name, String path) {
            this.tag = tag;
            this.name = name;
            this.path = path;
        }
    }
}
