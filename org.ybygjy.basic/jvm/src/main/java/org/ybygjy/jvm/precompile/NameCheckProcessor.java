package org.ybygjy.jvm.precompile;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 早期编译器优化
 * 1.继承实现AbstractProcessor提供代码编译阶段执行入口
 * 示例
 * <pre>
 javac -cp ./jvm-1.0.0.jar -processor org.ybygjy.jvm.precompile.NameCheckProcessor /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:7: 警告: 名称colors应当以大写字母开头！
 enum colors {
 ^
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:8: 警告: 常量red应当全部以大写字母或下划线命名，并且以字母开头!
 red, blue, green, gray;
 ^
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:8: 警告: 常量blue应当全部以大写字母或下划线命名，并且以字母开头!
 red, blue, green, gray;
 ^
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:8: 警告: 常量green应当全部以大写字母或下划线命名，并且以字母开头!
 red, blue, green, gray;
 ^
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:8: 警告: 常量gray应当全部以大写字母或下划线命名，并且以字母开头!
 red, blue, green, gray;
 ^
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:10: 警告: 常量_FORTY_TWO应当全部以大写字母或下划线命名，并且以字母开头!
 static final int _FORTY_TWO = 42;
 ^
 /Users/leye/1006_gitwork/org.ybygjy/org.ybygjy.basic/jvm/src/main/java/org/ybygjy/jvm/precompile/PreCompileTest.java:11: 警告: 名称NOT_A_CONSTANTS应当以小写字母开头
 public static int NOT_A_CONSTANTS = _FORTY_TWO;
 ^
 7 个警告
 * </pre>
 * Created by leye on 2017/7/14.
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {
    private ProcessingEnvironment processingEnvironment;
    private NameChecker nameChecker;
    public void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.processingEnvironment = processingEnvironment;
        this.nameChecker = new NameChecker(processingEnv);
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.checkName(element);
            }
        }
        return false;
    }
}
