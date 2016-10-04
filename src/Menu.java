import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String[] items;
    private ArrayList<Menu> subMenus = new ArrayList<>();

    public Menu(String... items) {
        this.items = items;
        for (int i = 0; i < items.length; i++) {
            subMenus.add(i, null);
        }
    }

    public int showMenu() {
        int selected = 0;

        printMenu();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                selected = Integer.parseInt(scanner.next());
            } catch (NumberFormatException ignored) {}
            if(selected > 0 && selected <= items.length)
                break;
            else
                printMenu();
        }

        return selected;
    }

    public void printMenu() {
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ".- " + items[i]);
        }
        System.out.println("-----------------------------------------------------------");
    }

    public void addSubMenu(int parent, String... subMenuItems) {
        if(parent > 0 && parent <= items.length)
            subMenus.set(parent - 1, new Menu(subMenuItems));
    }

    public Menu getSubMenu(int parent) {
        if(parent > 0 && parent <= items.length)
            return subMenus.get(parent - 1);
        return null;
    }
}
