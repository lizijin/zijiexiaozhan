package com.xianlai.ad.plugin;

import com.quinn.hunter.transform.asm.BaseWeaver;
import com.quinn.hunter.transform.asm.ExtendClassWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xc
 * @time 19-2-28.
 */
class AddTryCatchWeaver extends BaseWeaver {
    @Override
    public boolean isWeavableClass(String fullQualifiedClassName) {
        return Config.getInstance().extension.hookPoint.containsKey(fullQualifiedClassName.replace(".class", ""));
    }

    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new AddTryCatchClassAdapter(classWriter);
    }

    @Override
    public byte[] weaveSingleClassToByteArray(InputStream inputStream) throws IOException {
        ClassReader classReader = new ClassReader(inputStream);
        ClassWriter classWriter = new ExtendClassWriter(classLoader, ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classWriterWrapper = wrapClassWriter(classWriter);
        classReader.accept(classWriterWrapper, 0);
        return classWriter.toByteArray();
    }
}
