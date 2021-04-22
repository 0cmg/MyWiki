package com.cmg.javabase.file;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @CLassName LoadFile
 * @Description TODO
 * @Author cmg
 * @Date 2021/4/16 13:46
 * @Version 1.0
 **/
public class LoadFile {
    private static final String line = "-----------------------------------------";

    private void loadResource (String resource) throws IOException {
        URL u = this.getClass().getResource(resource);
        loadResourceByUrl(u, resource);
    }

    private void loadResourceWithContextLoader (String resource) throws IOException {
        URL u = Thread.currentThread().getContextClassLoader().getResource(resource);
        loadResourceByUrl(u, resource);
    }

    private void loadResourceWithSystemClassLoader (String resource) throws IOException {
        URL u = ClassLoader.getSystemClassLoader().getResource(resource);
        loadResourceByUrl(u, resource);
    }

    private void loadResourceByUrl (URL u, String resource) throws IOException {
        System.out.println("-> attempting input resource: "+resource);
        if (u != null) {
            String path = u.getPath();
            path = path.replaceFirst("^/(.:/)", "$1");
            System.out.println("    absolute resource path found :\n    " + path);
            String s = new String(Files.readAllBytes(Paths.get(path)));
            System.out.println("    file content: "+s);
        } else {
            System.out.println("    no resource found: " + resource);
        }
    }

    public static void main (String[] args) throws IOException {
        LoadFile a = new LoadFile();
        System.out.println(line+"\n using ClassLoader.getSystemClassLoader()\n"+line);
        a.loadResourceWithSystemClassLoader("cpack_gold.dat");
        a.loadResourceWithSystemClassLoader("/cpack_gold.dat");

    }
}
