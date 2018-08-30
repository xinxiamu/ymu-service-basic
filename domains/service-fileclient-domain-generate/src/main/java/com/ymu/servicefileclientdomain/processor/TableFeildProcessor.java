package com.ymu.servicefileclientdomain.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.ymu.servicefileclientdomain.annotations.GenerateJpaDomainFeildAsTableFeild;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@AutoService(Processor.class)
public class TableFeildProcessor extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        filer = processingEnv.getFiler(); // for creating file
        System.out.println(">>>processor init:" + filer.toString());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println(">>>> process:" + annotations.size());

        for (TypeElement element : annotations) {
            System.out.println(">>>>>> process：" + element.getQualifiedName().toString());
            if (element.getQualifiedName().toString().equals(GenerateJpaDomainFeildAsTableFeild.class.getCanonicalName())) {
                try {
                    // main method
//                MethodSpec main = MethodSpec.methodBuilder("main")
//                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                        .returns(void.class)
//                        .addParameter(String[].class, "args")
//                        .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                        .build();
                    // HelloWorld class
                TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                        .addField(TypeName.BOOLEAN,"a", Modifier.PUBLIC).build();

                    // build com.example.HelloWorld.java
                    JavaFile javaFile = JavaFile.builder("com.ymu", helloWorld)
                            .addFileComment("自动生成代码，不要更改!")
                            .build();
                    // write to file
                    javaFile.writeTo(filer);

                    javaFile.writeTo(System.out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(GenerateJpaDomainFeildAsTableFeild.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
