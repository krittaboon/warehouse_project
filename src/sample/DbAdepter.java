package sample;

import javafx.scene.control.Alert;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DbAdepter {
    private ArrayList<Sku> SkuArraylist = new ArrayList<>();

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rset;

    public DbAdepter() {
        connectDB();
        mapDBtoSkuList();
    }

    public void connectDB() {
        String user = "orcluser";
        String password = "jdbcuser";
        String query = "select * from sku";
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:" + user + "/"
                    + password + "@localhost:1521/xepdb1");
            conn = ods.getConnection();
            pstmt = conn.prepareStatement(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mapDBtoSkuList() {
        SkuArraylist.clear();
        try {
            rset = pstmt.executeQuery();

            while (rset.next()) {
                    int sku_id = rset.getInt(1);
                    String product_name = rset.getString(2);
                    String size = rset.getString(3);
                    String color = rset.getString(4);
                    int price = rset.getInt(5);
                    int quantity = rset.getInt(6);
                    Sku sku = new Sku(sku_id, product_name,size,color, price,quantity);
                    SkuArraylist.add(sku);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(Sku sku) {
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE sku SET product_name =?,product_size =?,product_color =?,product_price =?,product_quantity =? WHERE sku_id =?");

            preparedStatement.setString(1,sku.getProduct_name());
            preparedStatement.setString(2,sku.getSize());
            preparedStatement.setString(3, sku.getColor());
            preparedStatement.setInt(4, sku.getPrice());
            preparedStatement.setInt(5,sku.getQuantity());
            preparedStatement.setInt(6, sku.getSku_id());
            preparedStatement.execute();
            preparedStatement.close();
            mapDBtoSkuList();
            printResult("UPDATE");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void create(Sku sku) {
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO sku (sku_id,product_name,product_size,product_color,product_price,product_quantity)\n" +
                    "Values(?,?,?,?,?,?)");

            preparedStatement.setInt(1, sku.getSku_id());
            preparedStatement.setString(2,sku.getProduct_name());
            preparedStatement.setString(3,sku.getSize());
            preparedStatement.setString(4, sku.getColor());
            preparedStatement.setInt(5, sku.getPrice());
            preparedStatement.setInt(6,sku.getQuantity());

            preparedStatement.execute();
            preparedStatement.close();
            mapDBtoSkuList();
            printResult("CREATE");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void delete(Sku sku) {
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM sku WHERE sku_id =?");

            preparedStatement.setInt(1, sku.getSku_id());

            preparedStatement.execute();
            preparedStatement.close();
            mapDBtoSkuList();
            printResult("DELETE");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void printResult(String result){
        System.out.println("-----------------------"+result+"------------------------");

        for (Sku skuItem : SkuArraylist) {
            System.out.println("sku_id : "+skuItem.getSku_id()+" name : "+skuItem.getProduct_name()+" size : "+skuItem.getSize()+" color : "+skuItem.getColor()+" price : "+skuItem.getPrice()+" price : "+skuItem.getQuantity());
        }

        System.out.println("------------------------------------------------------");
        System.out.println();

    }

    public ArrayList<Sku> getSkuArraylist() {
        return SkuArraylist;
    }

    public void setSkuArraylist(ArrayList<Sku> skuArraylist) {
        SkuArraylist = skuArraylist;
    }
}
