package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<User> petri;

    @FXML
    private TableColumn<User, Integer> number;

    @FXML
    private TableColumn<User, String> marker;

    @FXML
    private TableColumn<User, String> inline;

    @FXML
    private TableColumn<User, String> outline;
    @FXML
    private Label state;
    @FXML
    private Label state1;


    @FXML
    private void initialize() {
        petri.setEditable(true);
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
      // number.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        number.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        marker.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        marker.setCellFactory(TextFieldTableCell.<User>forTableColumn());
        marker.setOnEditCommit(
                (TableColumn.CellEditEvent<User, String> t) -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLogin(t.getNewValue());
                });
        inline.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        inline.setCellFactory(TextFieldTableCell.<User>forTableColumn());
        inline.setOnEditCommit(
                (TableColumn.CellEditEvent<User, String> t) -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPassword(t.getNewValue());
                });
        outline.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        outline.setCellFactory(TextFieldTableCell.<User>forTableColumn());
        outline.setOnEditCommit(
                (TableColumn.CellEditEvent<User, String> t) -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setEmail(t.getNewValue());
                });




        petri.setItems(usersData);
    }


    private void initData() {
        usersData.add(new User(1, "2", "1", "0"));
        usersData.add(new User(2, "", "", "1"));
        usersData.add(new User(3, "", "", ""));
        usersData.add(new User(4, "", "", ""));
        usersData.add(new User(5, "", "", ""));
        usersData.add(new User(6, "", "", ""));
        usersData.add(new User(7, "", "", ""));
        usersData.add(new User(8, "", "", ""));
    }
    public void click()
    {
        List<String> columnDatamarker = new ArrayList<>();
        List<String> columnDatainline = new ArrayList<>();
        List<String> columnDataoutline = new ArrayList<>();
        for (User item : petri.getItems()) {
            if(marker.getCellObservableValue(item).getValue().isEmpty()) columnDatamarker.add("0"); else
            columnDatamarker.add(String.valueOf(marker.getCellObservableValue(item).getValue()));
            if(inline.getCellObservableValue(item).getValue().isEmpty()) columnDatainline.add("0"); else
            columnDatainline.add(String.valueOf(inline.getCellObservableValue(item).getValue()));
            if(outline.getCellObservableValue(item).getValue().isEmpty()) columnDataoutline.add("0"); else
            columnDataoutline.add(String.valueOf(outline.getCellObservableValue(item).getValue()));

        }// зчитали в 3 ліста дані з таблиць, там де немає значень занулили поля
for (int i=0;i<8;i++)
{int j,k; j=Integer.parseInt(columnDatamarker.get(i)); k=Integer.parseInt(columnDatainline.get(i));//баг
if (j<k){
    state.setText("Виконання зупинено, перехід не активний,");
    int it=i+1;
    state1.setText("рядок:"+it);
    return ;}
}
        for (int i=0;i<8;i++)
        {
            columnDatamarker.set(i, String.valueOf((Integer.parseInt(columnDatamarker.get(i))-Integer.parseInt(columnDatainline.get(i)))));
            columnDatamarker.set(i, String.valueOf((Integer.parseInt(columnDatamarker.get(i))+Integer.parseInt(columnDataoutline.get(i)))));
        }
        usersData.clear();
        for (int i=0;i<8;i++)
        { String a,b,c; a=columnDatamarker.get(i).toString(); b=columnDatainline.get(i).toString();c=columnDataoutline.get(i).toString();
          if(Integer.parseInt(a)==0)
              a="";
            if(Integer.parseInt(b)==0)
                b="";
                if(Integer.parseInt(c)==0)
                    c="";

            usersData.add(new User(i+1,a,b,c));
        }

        petri.setItems(usersData);
        state.setText("Перехід виконано");
        state1.setText(null);
    }
/*public void update()
{

}*/

}