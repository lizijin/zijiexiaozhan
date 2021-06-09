package com.xianlai.ad.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

/**
 * @author xc
 * @time 19-2-28.
 */
class AddTryCatchClassAdapter extends ClassVisitor {
    private String mClassName;
    private List<String> mMethodNames;

    public AddTryCatchClassAdapter(ClassWriter classWriter) {
        super(Opcodes.ASM5, classWriter);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name.replace("/", ".");
        mMethodNames = Config.getInstance().extension.hookPoint.get(mClassName);
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("add try catch class :" + mClassName);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (mMethodNames.contains(name)) {
            return new AddTryCatchAdviceAdapter(Opcodes.ASM5,
                    super.visitMethod(access, name, desc, signature, exceptions), access, name, desc);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
