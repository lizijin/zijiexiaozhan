package com.xianlai.ad.plugin;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;

public class AddTryCatchPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        AddTryCatchExtension extension = project.getExtensions().create("addTryCatch", AddTryCatchExtension.class);
        Config.getInstance().extension = extension;
        AppExtension appExtension = (AppExtension) project.getProperties().get("android");
        appExtension.registerTransform(new AddTryCatchTransform(project), Collections.EMPTY_LIST);
    }
}