package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SkuViewController implements Initializable {
    @FXML private TableView skutable;
    @FXML private TableColumn sku_id;
    @FXML private TableColumn product_name;
    @FXML private TableColumn price;
    @FXML private TableColumn size;
    @FXML private TableColumn color;
    @FXML private TableColumn quantity;

    @FXML private TextField tv_id;
    @FXML private TextField tv_name;
    @FXML private TextField tv_size;
    @FXML private TextField tv_color;
    @FXML private TextField tv_price;
    @FXML private TextField tv_quantity;

    DbAdepter dbAdepter;



    public ObservableList<Sku> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbAdepter= new DbAdepter();

        sku_id.setCellValueFactory(new PropertyValueFactory<Sku, Integer>("sku_id"));
        product_name.setCellValueFactory(new PropertyValueFactory<Sku, String>("product_name"));
        price.setCellValueFactory(new PropertyValueFactory<Sku, Integer>("price"));
        size.setCellValueFactory(new PropertyValueFactory<Sku, String>("size"));
        color.setCellValueFactory(new PropertyValueFactory<Sku, String>("color"));
        quantity.setCellValueFactory(new PropertyValueFactory<Sku, Integer>("quantity"));

        list = FXCollections.observableArrayList(dbAdepter.getSkuArraylist());
        skutable.setItems(list);

        skutable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onDoubleclicked();
            }
        });

    }

    private void onDoubleclicked() {
        if (skutable.getSelectionModel().getSelectedItem() != null) {
            Sku selected = (Sku)skutable.getSelectionModel().getSelectedItem();
            tv_id.setText(Integer.toString(selected.getSku_id()));
            tv_name.setText(selected.getProduct_name());
            tv_size.setText(selected.getSize());
            tv_price.setText(Integer.toString(selected.getPrice()));
            tv_color.setText(selected.getColor());
            tv_quantity.setText(Integer.toString(selected.getQuantity()));
        }
    }


    public void update(ActionEvent actionEvent) throws IOException{
        int id = Integer.parseInt(tv_id.getText());
        String name = tv_name.getText();
        String size = tv_size.getText();
        String color = tv_color.getText();
        int price = Integer.parseInt(tv_price.getText());
        int qty = Integer.parseInt(tv_quantity.getText());

        Sku sku = new Sku(id, name, size, color, price, qty);
        dbAdepter.update(sku);

        list = FXCollections.observableArrayList(dbAdepter.getSkuArraylist());
        skutable.setItems(list);

    }

    public void create(ActionEvent actionEvent) throws IOException{
        int id = Integer.parseInt(tv_id.getText());
        String name = tv_name.getText();
        String size = tv_size.getText();
        String color = tv_color.getText();
        int price = Integer.parseInt(tv_price.getText());
        int qty = Integer.parseInt(tv_quantity.getText());

        Sku sku = new Sku(id, name, size, color, price, qty);
        dbAdepter.create(sku);

        list = FXCollections.observableArrayList(dbAdepter.getSkuArraylist());
        skutable.setItems(list);

    }

    public void delete(ActionEvent actionEvent) throws IOException{
        int id = Integer.parseInt(tv_id.getText());
        String name = tv_name.getText();
        String size = tv_size.getText();
        String color = tv_color.getText();
        int price = Integer.parseInt(tv_price.getText());
        int qty = Integer.parseInt(tv_quantity.getText());

        Sku sku = new Sku(id, name, size, color, price, qty);
        dbAdepter.delete(sku);

        list = FXCollections.observableArrayList(dbAdepter.getSkuArraylist());
        skutable.setItems(list);

    }

}
