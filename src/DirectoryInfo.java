import java.io.File;

public class DirectoryInfo {
    private final File theDirectory;

    public DirectoryInfo(File theDirectory) throws Exception {
        if(theDirectory != null) {
            this.theDirectory = theDirectory;
        }
        else
            throw new Exception("The file is null.");
    }

    public boolean setReadOnly() {
        return theDirectory.setReadOnly();
    }

    public String getPermissions() {
        return  "R: " + theDirectory.canRead() + ", W: " + theDirectory.canWrite() + ", E: " + theDirectory.canExecute();
    }

    public boolean rename(String newname) {
        File renamedDirectory = new File(newname);

        if(renamedDirectory.exists()) {
            System.out.println("There's a file/directory with that name, do you want to overwrite it?");
            Menu overwriteMenu = new Menu("YES", "NO");
            return overwriteMenu.showMenu() == 1 && theDirectory.renameTo(new File(newname));
        }
        return theDirectory.renameTo(new File(newname));
    }

    public void listFiles() {
        try {
            for (File file : theDirectory.listFiles()) {
                System.out.println(file.getName());
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }
}
