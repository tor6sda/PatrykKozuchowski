import util.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Przyklad3 {
    public static void main(String[] args) {
        String sqlUpdate = "UPDATE address "+
                "SET ADD_BUILDING_NO = ? "+
                "WHERE ADD_POSTAL_CODE = ?";
        String selectQuery = "select * from address";
        try (PreparedStatement preparedStatement = DataBaseConnection.getInstance().getConnection().preparedStatement(sqlUpdate)){
preparedStatement.setString(1,"10");
preparedStatement.setString(2,"38600");
int countUpdatedRecords = preparedStatement.executeUpdate();
            System.out.println("Zaktualizowano "+countUpdatedRecords+" wierszy");
        }catch (SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement statement= DataBaseConnection.getInstance().getConnection().preparedStatement(selectQuery)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ADD_ID");
                String street = resultSet.getString("ADD_STREET");
                String buldingNo = resultSet.getString("ADD_BULDING_NO");
                String postalCode = resultSet.getString("ADD_POSTAL_CODE");
                System.out.println("Address Id: "+id+"Streed id: "+street+" "+buldingNo+" Postal code: "+postalCode);
            }
            System.out.println();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
