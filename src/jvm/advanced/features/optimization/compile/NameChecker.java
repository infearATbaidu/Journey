package jvm.advanced.features.optimization.compile;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;

/**
 * Created by zhanggang02 on 2016/7/31.
 */
public class NameChecker {
    private final Messager messager;
    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    public NameChecker(ProcessingEnvironment processingEnvironment) {
        messager = processingEnvironment.getMessager();
    }

    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    private class NameCheckScanner extends ElementScanner6<Void, Void> {
        @Override
        public Void visitType(TypeElement e, Void p) {
            scan(e.getTypeParameters(), p);
            checkCamelCase(e, true);
            super.visitType(e, p);
            return null;
        }

        // 为了测试只是简单实现了下，只检测首字母是不是大写
        private void checkCamelCase(TypeElement e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            int firstCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firstCodePoint)) {
                if (!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "name\"" + name + "\"should start with low case", e);
                    return;
                }
            }

        }
    }
}
