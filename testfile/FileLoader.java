public interface FileLoader {
    void loadFile(String fileName) throws Exception;

    String getSupportedExtension();

    boolean supports(String fileName);
}