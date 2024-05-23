package org.plugins;

public interface FileLoader {
    void loadFile(String fileName) throws Exception;

    String getSupportedExtension();

    boolean supports(String fileName);
}
//C:\Users\Asus Tuf Gaming\IdeaProjects\Tubes2_OOP_BZG\src\main\java\org\gui\Save.java