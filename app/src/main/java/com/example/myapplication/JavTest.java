package com.example.myapplication;

import com.tencent.matrix.apk.model.result.TaskResultFactory;
import com.tencent.matrix.apk.model.result.TaskResultRegistry;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 *
 * @author Alan
 * @date 2019/4/30
 * 功能：
 */
public class JavTest {
    String name;
    int age;

    public JavTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {

        File file = new File("C:/Users/tongpin.li/Desktop/perform/outer.jar");
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
            java.util.jar.Manifest manifest = jarFile.getManifest();
            Attributes registry = manifest.getAttributes("TaskResult-Registry");
            System.out.println("信息： 1111： registry: "+registry +"  manifest: "+"  ");

            for(Map.Entry<Object, Object> attrEntry : manifest.getMainAttributes().entrySet()){
                System.out.println("main\t"+attrEntry.getKey()+":"+attrEntry.getValue());
            }
            Map<String, Attributes> entries = manifest.getEntries();
            System.out.println("信息： 1111： entries: "+entries.keySet() );
            for (String s : entries.keySet()) {
                System.out.println("信息： key： s: "+s +"======   "+manifest.getAttributes(s));
            }
            for(Map.Entry<String, Attributes> entry : entries.entrySet()) {
                Attributes values = entry.getValue();
                for (Map.Entry<Object, Object> attrEntry : values.entrySet()) {
                    System.out.println("次要： "+attrEntry.getKey() + ":" + attrEntry.getValue());
                }
            }

            if (registry != null) {
                System.out.println("信息： 222222： ");
                String registryClassPath = registry.getValue("TaskResult-Registry-Class");
                ClassLoader classLoader = new URLClassLoader(new URL[]{file.toURL()});
                Class registryClass = classLoader.loadClass(registryClassPath);
                System.out.println("信息： 222222： registryClass: "+registryClass);
                TaskResultRegistry resultRegistry = (TaskResultRegistry)registryClass.newInstance();
                System.out.println("信息： 222222： resultRegistry: "+resultRegistry);
                TaskResultFactory.addCustomTaskJsonResult(resultRegistry.getJsonResult());
                TaskResultFactory.addCustomTaskHtmlResult(resultRegistry.getHtmlResult());
                System.out.println("信息： 333： ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("信息： error： "+e.getMessage());
        }

    }

    @Override
    public String toString() {
        return "JavTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

   /* class TestBean{
        String name;
        int age;

        @Override
        public String toString() {
            return "TestBean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }*/
}
