package com.jdbc.dao;

import java.sql.*;
import com.jdbc.model.Reminder;
import com.jdbc.util.DBConnection;

public class ReminderDAO {

    public void addReminder(Reminder r) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO reminders(title, description, remind_date, status) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, r.getTitle());
            ps.setString(2, r.getDescription());
            ps.setDate(3, Date.valueOf(r.getDate()));
            ps.setString(4, r.getStatus());

            ps.executeUpdate();
            System.out.println("Reminder Added!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewReminders() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reminders");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("description") + " | " +
                        rs.getDate("remind_date") + " | " +
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteReminder(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM reminders WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Reminder Deleted!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void markCompleted(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE reminders SET status='Completed' WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Marked as Completed!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}