import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Menu fileMenu = new Menu("Display length", "Display its relative path",
            "Display its absolute path", "Display its last modified date", "Rename",
            "Display permissions", "Set readonly", "Exit");
    private static Menu directoryMenu = new Menu("List the elements inside", "Rename",
            "Display permissions", "Set readonly", "Exit");

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        File currentFile;

        System.out.println("Enter the name or path of the file/directory:");
        fileName = scanner.nextLine();
        currentFile = new File(fileName);

        if(fileName.length() == 0) {
            throw new Exception("Empty name.");
        }

        if (currentFile.exists()) {
            if (currentFile.isDirectory()) {
                parseDirectory(currentFile);
            } else if (currentFile.isFile()) {
                parseFile(currentFile);
            }
        } else {
            Menu createMenu = new Menu("YES", "NO");
            if (fileName.charAt(fileName.length() - 1) == File.separatorChar) {
                System.out.println("Do you want to create the directory?");
                if (createMenu.showMenu() == 1) {
                    boolean done = currentFile.mkdirs();
                    System.out.println("Directory created: " + done);
                    if(done)
                        parseDirectory(currentFile);
                }
            } else {
                System.out.println("Do you want to create the file?");
                if (createMenu.showMenu() == 1) {
                    try {
                        System.out.println("File created: " + currentFile.createNewFile());
                        parseFile(currentFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void parseDirectory(File currentFile) {
        try {
            DirectoryInfo directoryInfo = new DirectoryInfo(currentFile);
            int response = directoryMenu.showMenu();

            while (response != 5) {
                switch (response) {
                    case 1: {
                        System.out.println("Files: ");
                        directoryInfo.listFiles();
                    } break;
                    case 2: {
                        Scanner scanner = new Scanner(System.in);
                        boolean done;
                        System.out.println("Enter the new name of the directory: ");
                        done = directoryInfo.rename(scanner.nextLine());
                        if(!done) {
                            System.out.println("Can't rename directory!");
                            System.out.println("Probably the directory you want to overwrite contains files");
                        }
                        System.out.println("New name set: " + done);
                    } break;
                    case 3: {
                        System.out.println("Permissions: " + directoryInfo.getPermissions());
                    } break;
                    case 4: {
                        System.out.println("Set readonly: " + directoryInfo.setReadOnly());
                    } break;
                }
                response = directoryMenu.showMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseFile(File theFile) {
        try {
            FileInfo fileInfo = new FileInfo(theFile);
            int response = fileMenu.showMenu();

            while (response != 8) {
                switch (response) {
                    case 1: {
                        System.out.println("The lenght of the file is: " + fileInfo.getLenght());
                    }
                    break;
                    case 2: {
                        System.out.println("Relative path: " + fileInfo.getRelativePath());
                    }
                    break;
                    case 3: {
                        System.out.println("Absolute path: " + fileInfo.getAbsolutePath());
                    }
                    break;
                    case 4: {
                        System.out.println("Last modified date: " + fileInfo.getLastModifiedDate());
                    }
                    break;
                    case 5: {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the new name of the file: ");
                        //TODO: check invalid names
                        System.out.println("New name set: " + fileInfo.rename(scanner.nextLine()));
                    }
                    break;
                    case 6: {
                        System.out.println("Permissions: " + fileInfo.getPermissions());
                    }
                    break;
                    case 7: {
                        System.out.println("Read only set: " + fileInfo.setReadOnly());
                    }
                    break;
                }
                response = fileMenu.showMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
