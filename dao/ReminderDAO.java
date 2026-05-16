package com.jdbc.dao;

import java.sql.*;
import com.jdbc.model.Reminder;
import com.jdbc.util.DBConnection;

public class ReminderDAO {

    // ── Add Reminder ─────────────────────────────────────────────────────────
    public void addReminder(Reminder r) {
        String sql = "INSERT INTO reminders(title, description, remind_date, status, priority, category) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getTitle());
            ps.setString(2, r.getDescription());
            ps.setDate(3, Date.valueOf(r.getDate()));
            ps.setString(4, r.getStatus());
            ps.setString(5, r.getPriority());
            ps.setString(6, r.getCategory());
            ps.executeUpdate();

            System.out.println("✔  Reminder added successfully!");

        } catch (Exception e) {
            System.out.println("✘  Failed to add reminder: " + e.getMessage());
        }
    }

    // ── View All Reminders ───────────────────────────────────────────────────
    public void viewReminders() {
        String sql = "SELECT * FROM reminders ORDER BY remind_date ASC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n" + "─".repeat(90));
            System.out.printf("  %-4s  %-20s  %-22s  %-11s  %-9s  %-8s  %-10s%n",
                    "ID", "TITLE", "DESCRIPTION", "DATE", "STATUS", "PRIORITY", "CATEGORY");
            System.out.println("─".repeat(90));

            int count = 0;
            while (rs.next()) {
                String status = rs.getString("status");

                // Auto-flag overdue reminders visually
                java.sql.Date d = rs.getDate("remind_date");
                if ("Pending".equals(status) && d != null
                        && d.toLocalDate().isBefore(java.time.LocalDate.now())) {
                    status = "OVERDUE ⚠";
                }

                System.out.printf("  %-4d  %-20s  %-22s  %-11s  %-9s  %-8s  %-10s%n",
                        rs.getInt("id"),
                        truncate(rs.getString("title"), 20),
                        truncate(rs.getString("description"), 22),
                        d,
                        status,
                        nullSafe(rs.getString("priority"), "MEDIUM"),
                        nullSafe(rs.getString("category"), "General"));
                count++;
            }

            System.out.println("─".repeat(90));
            System.out.println("  " + count + " reminder(s) found.");

        } catch (Exception e) {
            System.out.println("✘  Failed to fetch reminders: " + e.getMessage());
        }
    }

    // ── Search by Title keyword ──────────────────────────────────────────────
    public void searchReminders(String keyword) {
        String sql = "SELECT * FROM reminders WHERE LOWER(title) LIKE LOWER(?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            System.out.println("\n  Search results for \"" + keyword + "\":");
            System.out.println("─".repeat(60));

            int count = 0;
            while (rs.next()) {
                System.out.printf("  [%d] %s — %s | %s | %s%n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDate("remind_date"),
                        rs.getString("status"),
                        nullSafe(rs.getString("priority"), "MEDIUM"));
                count++;
            }
            if (count == 0) System.out.println("  No reminders match \"" + keyword + "\".");
            System.out.println("─".repeat(60));

        } catch (Exception e) {
            System.out.println("✘  Search failed: " + e.getMessage());
        }
    }

    // ── Filter by Status ────────────────────────────────────────────────────
    public void filterByStatus(String status) {
        String sql = "SELECT * FROM reminders WHERE status = ? ORDER BY remind_date ASC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n  Reminders with status: " + status);
            System.out.println("─".repeat(60));
            int count = 0;
            while (rs.next()) {
                System.out.printf("  [%d] %s — %s%n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDate("remind_date"));
                count++;
            }
            if (count == 0) System.out.println("  None found.");
            System.out.println("─".repeat(60));

        } catch (Exception e) {
            System.out.println("✘  Filter failed: " + e.getMessage());
        }
    }

    // ── Update Reminder ─────────────────────────────────────────────────────
    public void updateReminder(int id, String newTitle, String newDesc, String newDate) {
        String sql = "UPDATE reminders SET title=?, description=?, remind_date=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newTitle);
            ps.setString(2, newDesc);
            ps.setDate(3, Date.valueOf(newDate));
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✔  Reminder #" + id + " updated!");
            else          System.out.println("✘  No reminder found with ID: " + id);

        } catch (Exception e) {
            System.out.println("✘  Update failed: " + e.getMessage());
        }
    }

    // ── Delete Reminder ─────────────────────────────────────────────────────
    public void deleteReminder(int id) {
        String sql = "DELETE FROM reminders WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✔  Reminder #" + id + " deleted!");
            else          System.out.println("✘  No reminder found with ID: " + id);

        } catch (Exception e) {
            System.out.println("✘  Delete failed: " + e.getMessage());
        }
    }

    // ── Mark Completed ──────────────────────────────────────────────────────
    public void markCompleted(int id) {
        String sql = "UPDATE reminders SET status='Completed' WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✔  Reminder #" + id + " marked as Completed!");
            else          System.out.println("✘  No reminder found with ID: " + id);

        } catch (Exception e) {
            System.out.println("✘  Could not mark completed: " + e.getMessage());
        }
    }

    // ── Stats Summary ───────────────────────────────────────────────────────
    public void showStats() {
        String sql = "SELECT "
                   + "COUNT(*) AS total, "
                   + "SUM(status='Pending') AS pending, "
                   + "SUM(status='Completed') AS completed, "
                   + "SUM(status='Pending' AND remind_date < CURDATE()) AS overdue "
                   + "FROM reminders";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("\n  ── STATS ──────────────────────────");
                System.out.println("  Total     : " + rs.getInt("total"));
                System.out.println("  Pending   : " + rs.getInt("pending"));
                System.out.println("  Completed : " + rs.getInt("completed"));
                System.out.println("  Overdue   : " + rs.getInt("overdue") + "  ⚠");
                System.out.println("  ────────────────────────────────────");
            }

        } catch (Exception e) {
            System.out.println("✘  Could not load stats: " + e.getMessage());
        }
    }

    // ── Helpers ─────────────────────────────────────────────────────────────
    private String truncate(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max - 1) + "…";
    }

    private String nullSafe(String s, String def) {
        return (s == null || s.isBlank()) ? def : s;
    }
}
