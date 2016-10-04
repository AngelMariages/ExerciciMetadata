import java.io.File;
import java.io.IOException;

public class FileInfo {
    private File theFile;
    private String relativePath;
    private String absolutePath;

    public FileInfo(File theFile) throws Exception {
        if(theFile != null) {
            this.theFile = theFile;
            getInfo();
        }
        else
            throw new Exception("The file is null.");
    }

    private void getInfo() {
        relativePath = theFile.getPath();
        try {
            absolutePath = theFile.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public long getLenght() {
        return theFile.length();
    }

    public boolean rename(String newname) {
        String absolutePathWithoutFileName;
        File renamedFile;

        try {
            absolutePathWithoutFileName = theFile.getCanonicalPath().substring(0, theFile.getCanonicalPath().length() - theFile.getName().length());
            renamedFile = new File(absolutePathWithoutFileName + newname);
            if(renamedFile.exists()) {
                System.out.println("There's a file with that name, do you want to overwrite it?");
                Menu overwriteMenu = new Menu("YES", "NO");
                return overwriteMenu.showMenu() == 1 && theFile.renameTo(new File(absolutePathWithoutFileName + newname));
            }
            return theFile.renameTo(new File(absolutePathWithoutFileName + newname));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getLastModifiedDate() {
        return theFile.lastModified();
    }

    public String getPermissions() {
        return  "R: " + theFile.canRead() + ", W: " + theFile.canWrite() + ", E: " + theFile.canExecute();
    }

    public boolean setReadOnly() {
        return theFile.setReadOnly();
    }
}
