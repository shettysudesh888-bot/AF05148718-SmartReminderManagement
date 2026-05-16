package com.jdbc.main;

import java.util.Scanner;
import com.jdbc.dao.ReminderDAO;
import com.jdbc.model.Reminder;

public class MainApp {

    static final String BANNER =
        "\n  ╔══════════════════════════════════════╗" +
        "\n  ║     SMART REMINDER MANAGER  v1.1     ║" +
        "\n  ╚══════════════════════════════════════╝";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ReminderDAO dao = new ReminderDAO();

        System.out.println(BANNER);

        while (true) {

            System.out.println("\n  ─────────────────────────────────────");
            System.out.println("  1. Add Reminder       5. Search");
            System.out.println("  2. View All           6. Filter by Status");
            System.out.println("  3. Delete Reminder    7. Update Reminder");
            System.out.println("  4. Mark Completed     8. Stats");
            System.out.println("  0. Exit");
            System.out.println("  ─────────────────────────────────────");
            System.out.print("  Choose: ");

            String input = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1: {
                    System.out.print("  Title       : ");
                    String title = sc.nextLine().trim();

                    System.out.print("  Description : ");
                    String desc = sc.nextLine().trim();

                    System.out.print("  Date (YYYY-MM-DD): ");
                    String date = sc.nextLine().trim();

                    System.out.println("  Priority — 1.HIGH  2.MEDIUM  3.LOW");
                    System.out.print("  Choose: ");
                    String pInput = sc.nextLine().trim();
                    String priority = switch (pInput) {
                        case "1" -> "HIGH";
                        case "3" -> "LOW";
                        default  -> "MEDIUM";
                    };

                    System.out.print("  Category (e.g. Work/Personal/Health): ");
                    String category = sc.nextLine().trim();
                    if (category.isEmpty()) category = "General";

                    Reminder r = new Reminder(title, desc, date, "Pending", priority, category);
                    dao.addReminder(r);
                    break;
                }

                case 2:
                    dao.viewReminders();
                    break;

                case 3: {
                    System.out.print("  Enter ID to delete: ");
                    String idStr = sc.nextLine().trim();
                    try {
                        dao.deleteReminder(Integer.parseInt(idStr));
                    } catch (NumberFormatException e) {
                        System.out.println("  ⚠  Invalid ID.");
                    }
                    break;
                }

                case 4: {
                    System.out.print("  Enter ID to mark completed: ");
                    String idStr = sc.nextLine().trim();
                    try {
                        dao.markCompleted(Integer.parseInt(idStr));
                    } catch (NumberFormatException e) {
                        System.out.println("  ⚠  Invalid ID.");
                    }
                    break;
                }

                case 5: {
                    System.out.print("  Search keyword: ");
                    String keyword = sc.nextLine().trim();
                    dao.searchReminders(keyword);
                    break;
                }

                case 6: {
                    System.out.println("  1.Pending  2.Completed");
                    System.out.print("  Choose: ");
                    String s = sc.nextLine().trim();
                    String status = s.equals("2") ? "Completed" : "Pending";
                    dao.filterByStatus(status);
                    break;
                }

                case 7: {
                    System.out.print("  Enter ID to update: ");
                    String idStr = sc.nextLine().trim();
                    try {
                        int id = Integer.parseInt(idStr);
                        System.out.print("  New Title       : ");
                        String newTitle = sc.nextLine().trim();
                        System.out.print("  New Description : ");
                        String newDesc = sc.nextLine().trim();
                        System.out.print("  New Date (YYYY-MM-DD): ");
                        String newDate = sc.nextLine().trim();
                        dao.updateReminder(id, newTitle, newDesc, newDate);
                    } catch (NumberFormatException e) {
                        System.out.println("  ⚠  Invalid ID.");
                    }
                    break;
                }

                case 8:
                    dao.showStats();
                    break;

                case 0:
                    System.out.println("\n  Goodbye! Stay organised. 👋");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("  ⚠  Invalid choice. Enter 0–8.");
            }
        }
    }
}
