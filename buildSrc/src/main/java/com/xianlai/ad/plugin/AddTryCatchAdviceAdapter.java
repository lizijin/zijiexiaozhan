package com.xianlai.ad.plugin;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author xc
 * @time 19-2-28.
 */
public class AddTryCatchAdviceAdapter extends AdviceAdapter {

    private Label L0 = new Label();
    private Label L1 = new Label();
    private Label L2 = new Label();
    private Label L3 = new Label();
    private String exceptionHandleClass;
    private String exceptionHandleMethod;
String name;
    protected AddTryCatchAdviceAdapter(int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
        this.name = name;
//        Map<String, String> exceptionHandler = Config.getInstance().extension.exceptionHandler;
//        if (exceptionHandler != null && !exceptionHandler.isEmpty()) {
//            exceptionHandler.entrySet().forEach(entry -> {
//                exceptionHandleClass = entry.getKey().replace(".", "/");
//                exceptionHandleMethod = entry.getValue();
//            });
//        }
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        visitLdcInsn("This is jiangbin ----"+name);
        visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V");
        mv.visitTryCatchBlock(L0, L1, L2, "java/lang/Exception");
        mv.visitLabel(L0);
    }

    @Override
    protected void onMethodExit(int opcode) {
        if(opcode==RETURN){
            visitLabel(L1);
            visitJumpInsn(GOTO,L3);
            visitLabel(L2);
            visitVarInsn(ASTORE,1);
            visitLabel(L3);
        }else if(opcode==IRETURN){
            visitLabel(L1);
            mv.visitInsn(opcode);

            visitLabel(L2);
            visitVarInsn(ASTORE,1);

            visitLabel(L3);
            visitInsn(ICONST_0);
            mv.visitInsn(opcode);
        }else if(opcode==FRETURN){
            visitLabel(L1);
            mv.visitInsn(opcode);

            visitLabel(L2);
            visitVarInsn(ASTORE,1);

            visitLabel(L3);
            visitInsn(FCONST_0);
            mv.visitInsn(opcode);
        }else if(opcode==LRETURN){
            visitLabel(L1);
            mv.visitInsn(opcode);

            visitLabel(L2);
            visitVarInsn(ASTORE,1);

            visitLabel(L3);
            visitInsn(LCONST_0);
            mv.visitInsn(opcode);
        }else if(opcode == DRETURN){
            visitLabel(L1);
            mv.visitInsn(opcode);

            visitLabel(L2);
            visitVarInsn(ASTORE,1);

            visitLabel(L3);
            visitInsn(DCONST_0);
            mv.visitInsn(opcode);
        }else if(opcode == ARETURN){
            visitLabel(L1);
            mv.visitInsn(opcode);

            visitLabel(L2);
            visitVarInsn(ASTORE,1);

            visitLabel(L3);
            visitInsn(ACONST_NULL);
            mv.visitInsn(opcode);
        }
        mv.visitInsn(opcode);
        super.onMethodExit(opcode);

    }
}
