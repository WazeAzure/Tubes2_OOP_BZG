public class LoaderXML implements FileLoader {
    @Override
    public void loadFile(String fileName) throws Exception {
        if(!supports(fileName)){
            throw new Exception("File not supported");
        }
    }

    @Override
    public String getSupportedExtension(){
        return "xml";
    }

    @Override
    public boolean supports(String fileName) {
        if(fileName.endsWith(".xml")){
            return true;
        }
        return false;
    }
}
