import java.io.File;
import java.io.IOException;

public class DirectoryInfo {
    private File theDirectory;

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
        boolean done;

        if(renamedDirectory.exists()) {
            System.out.println("There's a file/directory with that name, do you want to overwrite it?");
            Menu overwriteMenu = new Menu("YES", "NO");
            return overwriteMenu.showMenu() == 1 && theDirectory.renameTo(new File(newname));
        }
        done = theDirectory.renameTo(renamedDirectory);
        theDirectory = renamedDirectory;
        return done;
    }

    public void listFiles() {
        try {
            for (String file : theDirectory.list()) {
                //TODO: mark the directories correctly
                System.out.println(theDirectory.getPath() + File.separatorChar + file);
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }
}
