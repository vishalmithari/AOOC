import java.sql.*;

class testdb {
    public static void main(String args[]) {
        int rn = 0;
        String name;
        float mks;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cse?useSSL=false", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            while (rs.next()) {
                rn = rs.getInt(1);
                name = rs.getString(2);
                mks = rs.getFloat(3);
                System.out.println("Rollno: " + rn + "\tName: " + name + "\tMarks: " + mks);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
