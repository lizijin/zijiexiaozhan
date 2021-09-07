//package com.peter.viewgrouptutorial;
//
//import android.content.Context;
//import android.os.Build;
//import android.os.Environment;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.startup.Initializer;
//
//import java.io.File;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
//public class FileProviderMergeInitializer implements Initializer<Object> {
//    private static final File DEVICE_ROOT = new File("/");
//    private static final String TAG_ROOT_PATH = "root-path";
//    private static final String TAG_FILES_PATH = "files-path";
//    private static final String TAG_CACHE_PATH = "cache-path";
//    private static final String TAG_EXTERNAL = "external-path";
//    private static final String TAG_EXTERNAL_FILES = "external-files-path";
//    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
//    private static final String TAG_EXTERNAL_MEDIA = "external-media-path";
//    private File filesDir = null;
//    private File cacheDir = null;
//    private File externalDir = null;
//    private File externalCacheDir = null;
//    private File externalFilesDir = null;
//    private File externalMediaDir = null;
//
//    private Method addRootMethod = null;
//    private Constructor constructor = null;
//    private HashMap sCache = null;
//
//    private static File buildPath(File base, String... segments) {
//        File cur = base;
//        for (String segment : segments) {
//            if (segment != null) {
//                cur = new File(cur, segment);
//            }
//        }
//        return cur;
//    }
//
//    public List<ProviderItem> createProviderItems() {
//        ArrayList<PathItem> pathItems = new ArrayList<>();
//        ArrayList<ProviderItem> providerItems = new ArrayList<>();
//        pathItems.add(new PathItem(TAG_FILES_PATH, "file_path", "."));
//        pathItems.add(new PathItem(TAG_FILES_PATH, "file_path2", "jiangbin"));
//        pathItems.add(new PathItem(TAG_CACHE_PATH, "cache_file_path", "img_cache_file_path"));
//        pathItems.add(new PathItem(TAG_EXTERNAL, "jb", "."));
//
//        pathItems.add(new PathItem(TAG_EXTERNAL, "external_storage_path", "img_external_storage_path"));
//        pathItems.add(new PathItem(TAG_EXTERNAL_FILES, "external_file_path", "img_external_file_path"));
//        pathItems.add(new PathItem(TAG_EXTERNAL_CACHE, "external_cache_path", "img_external_cache_path"));
//        ProviderItem providerItem = new ProviderItem("com.peter.viewgrouptutorial", pathItems);
//        providerItems.add(providerItem);
//        ProviderItem providerItem1 = new ProviderItem("com.peter.viewgrouptutorial1", pathItems);
//        providerItems.add(providerItem1);
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
//
//        ProviderItem providerItem9 = new ProviderItem("com.peter.install.fileProvider", pathItems);
//        providerItems.add(providerItem9);
//        return providerItems;
//    }
//
//    @NonNull
//    @Override
//    public Object create(@NonNull Context context) {
//        try {
//            Class fileProviderClass = Class.forName("androidx.core.content.FileProvider");
//            Field sCacheField = fileProviderClass.getDeclaredField("sCache");
//            sCacheField.setAccessible(true);
//            sCache = (HashMap) sCacheField.get(null);
//
//            Class pathStrategyClass = Class.forName("androidx.core.content.FileProvider$SimplePathStrategy");
//
//            constructor = pathStrategyClass.getDeclaredConstructor(String.class);
//            constructor.setAccessible(true);
//            filesDir = context.getFilesDir();//"files-path"
//            cacheDir = context.getCacheDir();//"cache-path"
//            externalDir = Environment.getExternalStorageDirectory();//external-path
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
//            ) {
//                File[] externalMediaDirs = context.getExternalMediaDirs();
//                if (externalMediaDirs.length > 0) {
//                    externalMediaDir = externalMediaDirs[0];
//                }
//                //external-files-path
//                File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
//                if (externalFilesDirs.length > 0) {
//                    externalFilesDir = externalFilesDirs[0];
//                }
//            }
//
//            File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context);
//            if (externalCacheDirs.length > 0) {
//                externalCacheDir = externalCacheDirs[0];
//            }
//            addRootMethod = pathStrategyClass.getDeclaredMethod("addRoot", String.class, File.class);
//            addRootMethod.setAccessible(true);
//
//            List<ProviderItem> providerItems = createProviderItems();
//            if (providerItems == null || providerItems.size() == 0) {
//                return null;
//            }
//            for (ProviderItem providerItem : providerItems) {
//                addFileProvider(providerItem);
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private void addFileProvider(ProviderItem providerItem) throws
//            IllegalAccessException, InstantiationException, InvocationTargetException {
//        Object pathStrategy = constructor.newInstance(providerItem.authorities);
//        for (PathItem pathItem : providerItem.pathItems) {
//            File target = null;
//            String tag = pathItem.tag;
//            if (TAG_ROOT_PATH.equals(tag)) {
//                target = DEVICE_ROOT;
//            } else if (TAG_FILES_PATH.equals(tag)) {
//                target = filesDir;
//            } else if (TAG_CACHE_PATH.equals(tag)) {
//                target = cacheDir;
//            } else if (TAG_EXTERNAL.equals(tag)) {
//                target = externalDir;
//            } else if (TAG_EXTERNAL_FILES.equals(tag)) {
//                target = externalFilesDir;
//            } else if (TAG_EXTERNAL_CACHE.equals(tag)) {
//                target = externalCacheDir;
//            } else if (TAG_EXTERNAL_MEDIA.equals(tag)) {
//                target = externalMediaDir;
//            }
//
//            if (target != null) {
//                addRootMethod.invoke(pathStrategy, pathItem.name, buildPath(target, pathItem.path));
//            }
//        }
//
//        sCache.put(providerItem.authorities, pathStrategy);
//    }
//
////    }
//
//    @NonNull
//    @Override
//    public List<Class<? extends Initializer<?>>> dependencies() {
//        return Collections.emptyList();
//    }
//
//
//    private static class ProviderItem {
//        public String authorities;
//        public List<PathItem> pathItems;
//
//        public ProviderItem(String authorities, List<PathItem> pathItems) {
//            this.authorities = authorities;
//            this.pathItems = pathItems;
//        }
//    }
//
//    private static class PathItem {
//        public String tag;
//        public String name;
//        public String path;
//
//        public PathItem(String tag, String name, String path) {
//            this.tag = tag;
//            this.name = name;
//            this.path = path;
//        }
//    }
//}
