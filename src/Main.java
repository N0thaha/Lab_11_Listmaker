import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        SafeInput.prettyHeader("Welcome to List Maker!");

        boolean done = false;

        while (!done) {
            displayList();
            String choice = SafeInput.getRegExString(in, "Choose [A]dd [D]elete [I]nsert [P]rint [Q]uit", "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?")) {
                        done = true;
                        System.out.println("Goodbye!");
                    }
                    break;
            }
        }
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to add");
        list.add(item);
        System.out.println("Item added to the list.");
    }

    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        displayList();
        int index = SafeInput.getRangedInt(in, "Enter the number of the item to delete", 1, list.size()) - 1;
        String removedItem = list.remove(index);
        System.out.println("Removed: " + removedItem);
    }

    private static void insertItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to insert");

        int position;
        if (list.isEmpty()) {
            list.add(item);
            System.out.println("List was empty. Item added at position 1.");
        } else {
            displayList();
            position = SafeInput.getRangedInt(in, "Enter the position to insert the item", 1, list.size() + 1) - 1;
            list.add(position, item);
            System.out.println("Item inserted at position " + (position + 1));
        }
    }

    private static void printList() {
        if (list.isEmpty()) {
            System.out.println("The list is currently empty.");
        } else {
            System.out.println("\nCurrent List:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ": " + list.get(i));
            }
        }
    }

    private static void displayList() {
        System.out.println("\n========== Current List ==========");
        if (list.isEmpty()) {
            System.out.println("   [The list is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + list.get(i));
            }
        }
        System.out.println("==================================");
    }
}
