package com.jdbc.main;

import java.util.Scanner;
import com.jdbc.dao.ReminderDAO;
import com.jdbc.model.Reminder;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ReminderDAO dao = new ReminderDAO();

        while (true) {
            System.out.println("\n1. Add Reminder");
            System.out.println("2. View Reminders");
            System.out.println("3. Delete Reminder");
            System.out.println("4. Mark Completed");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.next();

                    System.out.print("Description: ");
                    String desc = sc.next();

                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.next();

                    String status = "Pending";

                    Reminder r = new Reminder(title, desc, date, status);
                    dao.addReminder(r);
                    break;

                case 2:
                    dao.viewReminders();
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    dao.deleteReminder(id);
                    break;

                case 4:
                    System.out.print("Enter ID to mark completed: ");
                    int id2 = sc.nextInt();
                    dao.markCompleted(id2);
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}