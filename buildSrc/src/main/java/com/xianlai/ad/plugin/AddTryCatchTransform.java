package com.xianlai.ad.plugin;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.RunVariant;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;

/**
 * @author xc
 * @time 19-2-28.
 */
class AddTryCatchTransform extends HunterTransform {

    public AddTryCatchTransform(Project project) {
        super(project);
        this.bytecodeWeaver = new AddTryCatchWeaver();
    }

    @Override
    public void transform(TransformInvocation transformInvocation)
            throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        System.out.println("MyTransform-----"+transformInvocation.getInputs().toString());
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        for(TransformInput input:inputs){
            Collection<JarInput> jarInputs = input.getJarInputs();
            if(jarInputs !=null)
                for(JarInput jarInput :jarInputs){
                    System.out.println("MyTransform-----"+
                            jarInput.getFile());
//                    System.out.println("MyTransform-----"+
//                    transformInvocation.getOutputProvider().getContentLocation(jarInput.getName(),jarInput.getContentTypes(),jarInput.getScopes(), Format.JAR));
                }
            Collection<DirectoryInput> directoryInputs = input.getDirectoryInputs();
            if(directoryInputs !=null)

                for(DirectoryInput directoryInput :directoryInputs){
                    System.out.println("MyTransform-----"+
                            transformInvocation.getOutputProvider().getContentLocation(directoryInput.getName(),directoryInput.getContentTypes(),directoryInput.getScopes(), Format.DIRECTORY));
                }
        }
    }

    @Override
    protected RunVariant getRunVariant() {
        return super.getRunVariant();
    }
}
