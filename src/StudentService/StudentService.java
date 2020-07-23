package StudentService;

import com.mysql.cj.conf.PropertyDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Connection con;

    public StudentService (Connection con){
        this.con=con;
    }

    public void updateNoOfStudents (){
        try {
            String sql = "SELECT COUNT(*), DeptID FROM students GROUP BY DeptID";
            Statement stmt =con.createStatement();

            ResultSet deptIDs = stmt.executeQuery(sql);

            while (deptIDs.next()){
                int NoOfStudent = deptIDs.getInt(1);
                String dept = deptIDs.getString(2);
                // Update number
                String update ="UPDATE departments SET NoOfStudents = " + NoOfStudent + " WHERE DeptID = '" + dept +"'";
                stmt.execute(update);
                // cas No = null
                String zero ="UPDATE departments SET NoOfStudents = 0 WHERE NoOfStudents IS NULL";
                stmt.execute(zero);
            }

        } catch (SQLException e) {
            System.out.println("Update fail"+ e);
        }
    }
    public void updateAverageNote() {
        try {
            String StudentId = "SELECT distinct StudentID FROM manage_student.results";
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(StudentId);
            List<String> studentIds = new ArrayList<>();
            while (result.next()) {
                studentIds.add(result.getString(1));

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);

        }

    }
    }
