package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Kategorie;
import modele.Podkategorie;
import modele.Producent;
import DAO.kategorieDAO;
import DAO.podkategorieDAO;
import DAO.producentDAO;
import DAO.produktyDAO;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import modele.Produkty;

public class ProjektPO2Controller implements Initializable {

    @FXML
    private Button addUnderCat;
    @FXML
    private Button delUnderCat;
    @FXML
    private Button updUnderCat;
    @FXML
    private Button addProducent;
    @FXML
    private Button delProducent;
    @FXML
    private Button updProducent;
    @FXML
    private Button addProduct;
    @FXML
    private Button delProdukt;
    @FXML
    private Button updProduct;
    @FXML
    private Button addCat;
    @FXML
    private Button delCat;
    @FXML
    private Button updCat;
    @FXML
    private TextField textUnderCat;
    @FXML
    private TextField textProducent;
    @FXML
    private TextField textParamProduct;
    @FXML
    private TextField textDescribeProduct;
    @FXML
    private TextField textPriceProduct;
    @FXML
    private TextField textNameProduct;
    @FXML
    private ComboBox<Kategorie> choiceCat;
    @FXML
    private ComboBox<Kategorie> choiceUnderCatMain;
    @FXML
    private ComboBox<Podkategorie> choiceUnderCat;
    @FXML
    private ComboBox<Producent> choiceProducent;
    @FXML
    private TextField textCat;
    @FXML
    private TableView<Podkategorie> tabUnderCat;
    @FXML
    private TableView<Producent> tabProducent;
    @FXML
    private TableView<Produkty> tabProduct;
    @FXML
    private TableView<Kategorie> tabCat;
    @FXML
    private Tab tabbedUnderCat;
    @FXML
    private Tab tabbedProducent;
    @FXML
    private Tab tabbedProduct;
    @FXML
    private Tab tabbedCat;
    @FXML
    private TableColumn<Podkategorie, Integer> idTableUnderCat;
    @FXML
    private TableColumn<Podkategorie, String> nameTableUnderCat;
    @FXML
    private TableColumn<Producent, Integer> idTableProducent;
    @FXML
    private TableColumn<Producent, String> nameTableProducent;
    @FXML
    private TableColumn<Produkty, String> nameTableProduct;
    @FXML
    private TableColumn<Produkty, String> paramTableProduct;
    @FXML
    private TableColumn<Produkty, String> descTableProduct;
    @FXML
    private TableColumn<Produkty, Float> priceTableProduct;
    @FXML
    private TableColumn<Produkty, String> catTableProduct = new TableColumn<>("kategorie");
    @FXML
    private TableColumn<Produkty, String> underCatTableProduct = new TableColumn<>("podkategorie");
    @FXML
    private TableColumn<Produkty, String> producentTableProduct = new TableColumn<>("producent");
    @FXML
    private TableColumn<Kategorie, Integer> idTableCat;
    @FXML
    private TableColumn<Kategorie, String> nameTableCat;
    @FXML
    private TableColumn<Podkategorie, String> nameCatTableUnderCat = new TableColumn<>("kategorie");
    @FXML
    private ImageView showIMGproduct;
    @FXML
    private Button buttonIMGchoser;
    @FXML
    private TextArea textChosenFile;
    @FXML
    private TextField searchingField;
    @FXML
    private ComboBox choseSearch;
    
    //obiekty DAO, ktore beda manipulować danymi w przyciskach        
    private final kategorieDAO catDAO = new kategorieDAO();
    private final podkategorieDAO underCatDAO = new podkategorieDAO();
    private final producentDAO producentDAO = new producentDAO();
    private final produktyDAO produktyDAO = new produktyDAO();
    //do wyszukiwania by nie tworzyc caaaly czas to samo
    FilteredList<Produkty> date = new FilteredList(FXCollections.observableArrayList(produktyDAO.getAll()), p->true);
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Wyswietlanie rekordów//
        wklejanieKategorii();
        wklejaniePodkategorii();
        wklejanieProducenta();
        wklejanieProduktu();
        
        //@@@@@@ Obsługa przycisków  i funckje w KATEGORIACH@@@@@@@@@@@ W sumie dla reszty jest to samo +- dodanie odpowiednieog pola itp itd :)
        //dodanie rekordu kategorii 
        addCat.setOnAction((ActionEvent event)->{
           if(textCat.getText() != null && !textCat.getText().isEmpty()){
           Kategorie cat = new Kategorie();
           cat.setNazwa_kategorii(textCat.getText());
           catDAO.create(cat);
           tabCat.setItems(FXCollections.observableList(catDAO.getAll()));
           textCat.clear();
           }
        });
        //usunięcie rekordu kategorii
        delCat.setOnAction((ActionEvent event)->{
            if(tabCat.getSelectionModel().getSelectedItem() != null){
            Kategorie cat = new Kategorie();
            cat = tabCat.getSelectionModel().getSelectedItem();
            catDAO.delete(cat);
            tabCat.setItems(FXCollections.observableList(catDAO.getAll()));
            textCat.clear();
            }
        });
        //aktualizacja rekordu kategorii
        updCat.setOnAction((ActionEvent event)->{
            if(textCat.getText() != null && !textCat.getText().isEmpty() && tabCat.getSelectionModel().getSelectedItem() != null){            
            Kategorie cat = new Kategorie();
            cat = tabCat.getSelectionModel().getSelectedItem();
            cat.setNazwa_kategorii(textCat.getText());
            catDAO.update(cat);
            tabCat.setItems(FXCollections.observableList(catDAO.getAll()));
            textCat.clear();
            }
        });
        //wyswietlanie w textfieldach nacisnietych wlasciwosci rekordu
        tabCat.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Kategorie> obs, Kategorie oldSelection, Kategorie newSelection) -> {
            if (newSelection != null) {
               textCat.setText(tabCat.getSelectionModel().getSelectedItem().getNazwa_kategorii());
        }
        });
        
        
        //@@@@@@@@@@@@@@@Obsługa przycisków i funkcje dla Podkategorii@@@@@@@@@@@@@@@@@@@@@
        addUnderCat.setOnAction((ActionEvent event)->{
           if(textUnderCat.getText() != null && !textUnderCat.getText().isEmpty() && choiceUnderCatMain.getValue() != null){
           Podkategorie undercat = new Podkategorie();
           undercat.setNazwa_podkategorii(textUnderCat.getText());
           undercat.setKategorie(choiceUnderCatMain.getValue());
           underCatDAO.create(undercat);
           tabUnderCat.setItems(FXCollections.observableList(underCatDAO.getAll()));
           textUnderCat.clear();
           }
        });
        //usunięcie rekordu podkategorii
        delUnderCat.setOnAction((ActionEvent event)->{
            if(tabUnderCat.getSelectionModel().getSelectedItem() != null){
            Podkategorie undercat = new Podkategorie();
            undercat = tabUnderCat.getSelectionModel().getSelectedItem();
            underCatDAO.delete(undercat);
            tabUnderCat.setItems(FXCollections.observableList(underCatDAO.getAll()));
            textUnderCat.clear();
            }
        });
        //aktualizacja rekordu podkategorii
        updUnderCat.setOnAction((ActionEvent event)->{
            if(textUnderCat.getText() != null && !textUnderCat.getText().isEmpty() && choiceUnderCatMain.getValue() != null && tabUnderCat.getSelectionModel().getSelectedItem() != null){
            Podkategorie undercat = new Podkategorie();
            undercat = tabUnderCat.getSelectionModel().getSelectedItem();
            undercat.setNazwa_podkategorii(textUnderCat.getText());
            undercat.setKategorie(choiceUnderCatMain.getValue());
            underCatDAO.update(undercat);
            tabUnderCat.setItems(FXCollections.observableList(underCatDAO.getAll()));
            textCat.clear();
            }
        });
        //wyswietlanie w textfieldach i choiceboxach nacisnietych wlasciwosci rekordu
        tabUnderCat.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Podkategorie> obs, Podkategorie oldSelection, Podkategorie newSelection) -> {
            if (newSelection != null) {
               textUnderCat.setText(tabUnderCat.getSelectionModel().getSelectedItem().getNazwa_podkategorii());
               choiceUnderCatMain.setValue(tabUnderCat.getSelectionModel().getSelectedItem().getKategorie());
        }
        });
        
        
        //@@@@@@@@@@@@@@@Obsługa przycisków i funkcje dla Producenta@@@@@@@@@@@@@@@@@@@@@
         addProducent.setOnAction((ActionEvent event)->{
           if(textProducent.getText() != null && !textProducent.getText().isEmpty()){
           Producent prod = new Producent();
           prod.setNazwa_producenta(textProducent.getText());
           producentDAO.create(prod);
           tabProducent.setItems(FXCollections.observableList(producentDAO.getAll()));
           textProducent.clear();
           }
        });
        //usunięcie rekordu producenta
        delProducent.setOnAction((ActionEvent event)->{
            if(tabProducent.getSelectionModel().getSelectedItem() != null){
            Producent prod = new Producent();
            prod = tabProducent.getSelectionModel().getSelectedItem();
            producentDAO.delete(prod);
            tabProducent.setItems(FXCollections.observableList(producentDAO.getAll()));
            textCat.clear();
            }
        });
        //aktualizacja rekordu producenta
        updProducent.setOnAction((ActionEvent event)->{
            if(textProducent.getText() != null && !textProducent.getText().isEmpty() && tabProducent.getSelectionModel().getSelectedItem() != null ){
            Producent prod = new Producent();
            prod = tabProducent.getSelectionModel().getSelectedItem();
            prod.setNazwa_producenta(textProducent.getText());
            producentDAO.update(prod);
            tabProducent.setItems(FXCollections.observableList(producentDAO.getAll()));
            textProducent.clear();
            }
        });
        //wyswietlanie w textfieldach nacisnietych wlasciwosci rekordu
        tabProducent.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Producent> obs, Producent oldSelection, Producent newSelection) -> {
            if (newSelection != null) {
               textProducent.setText(tabProducent.getSelectionModel().getSelectedItem().getNazwa_producenta());
        }
        });
        
        
        //@@@@@@@@@@@@@@@Obsługa przycisków i funkcje dla Produktu@@@@@@@@@@@@@@@@@@@@@
        addProduct.setOnAction((ActionEvent event)->{
           if(textNameProduct.getText() != null && !textNameProduct.getText().isEmpty() &&
              textDescribeProduct.getText() != null && !textDescribeProduct.getText().isEmpty() &&
              textParamProduct.getText() != null && !textParamProduct.getText().isEmpty() &&
              choiceCat.getValue() != null && choiceUnderCat.getValue() !=null && choiceProducent.getValue() != null)
           {
           Produkty product = new Produkty(); 
           product.setNazwa_produktu(textNameProduct.getText());
           product.setOpis_produktu(textDescribeProduct.getText());
           product.setParametry_produktu(textParamProduct.getText());
           product.setCena_produktu(Float.parseFloat(textPriceProduct.getText()));
           product.setKategorie(choiceCat.getValue());
           product.setPodkategorie(choiceUnderCat.getValue());
           product.setProducent(choiceProducent.getValue());
           product.setZdjecie_produktu(produktyDAO.insertPhoto(textChosenFile));
           produktyDAO.create(product);
           tabProduct.setItems(FXCollections.observableList(produktyDAO.getAll()));
           textNameProduct.clear();
           textDescribeProduct.clear();
           textParamProduct.clear();
           textPriceProduct.clear();
           choiceCat.valueProperty().set(null);
           choiceUnderCat.valueProperty().set(null);
           choiceProducent.valueProperty().set(null);
           textChosenFile.clear();
        }
        });
        //usunięcie rekordu produktu
        delProdukt.setOnAction((ActionEvent event)->{
            if(tabProduct.getSelectionModel().getSelectedItem() != null){
            Produkty produkt = new Produkty();
            produkt = tabProduct.getSelectionModel().getSelectedItem();
            produktyDAO.delete(produkt);
            tabProduct.setItems(FXCollections.observableList(produktyDAO.getAll()));
            textNameProduct.clear();
            textDescribeProduct.clear();
            textParamProduct.clear();
            textPriceProduct.clear();
            choiceCat.valueProperty().set(null);
            choiceUnderCat.valueProperty().set(null);
            choiceProducent.valueProperty().set(null);
            textChosenFile.clear();
            }
        });
        //aktualizacja rekordu produktu
        updProduct.setOnAction((ActionEvent event)->{
           if(textNameProduct.getText() != null && !textNameProduct.getText().isEmpty() &&
              textDescribeProduct.getText() != null && !textDescribeProduct.getText().isEmpty() &&
              textParamProduct.getText() != null && !textParamProduct.getText().isEmpty() &&
              choiceCat.getValue() != null && choiceUnderCat.getValue() !=null && choiceProducent.getValue() != null &&
              tabProduct.getSelectionModel().getSelectedItem() != null)
           {
            Produkty product = new Produkty();
            product = tabProduct.getSelectionModel().getSelectedItem();
            product.setNazwa_produktu(textNameProduct.getText());
            product.setOpis_produktu(textDescribeProduct.getText());
            product.setParametry_produktu(textParamProduct.getText());
            product.setCena_produktu(Float.parseFloat(textPriceProduct.getText()));
            product.setKategorie(choiceCat.getValue());
            product.setPodkategorie(choiceUnderCat.getValue());
            product.setProducent(choiceProducent.getValue());
            if(textChosenFile.getText() != null && !textChosenFile.getText().isEmpty()){ // by nie usuwalo zdjecia jak sie daje update.
               product.setZdjecie_produktu(produktyDAO.insertPhoto(textChosenFile));
            }
            produktyDAO.update(product);
            tabProduct.setItems(FXCollections.observableList(produktyDAO.getAll()));
            textNameProduct.clear();
            textDescribeProduct.clear();
            textParamProduct.clear();
            textPriceProduct.clear();
            choiceCat.valueProperty().set(null);
            choiceUnderCat.valueProperty().set(null);
            choiceProducent.valueProperty().set(null);
            textChosenFile.clear();
            tabProduct.getSelectionModel().clearSelection();
           }
        });
        
        //wyswietlanie w textfieldach i choiceboxach itp itd nacisnietych wlasciwosci rekordu
        tabProduct.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Produkty> obs, Produkty oldSelection, Produkty newSelection) -> {
            if (newSelection != null) {
               textNameProduct.setText(tabProduct.getSelectionModel().getSelectedItem().getNazwa_produktu());
               textDescribeProduct.setText(tabProduct.getSelectionModel().getSelectedItem().getOpis_produktu());
               textParamProduct.setText(tabProduct.getSelectionModel().getSelectedItem().getParametry_produktu());
               textPriceProduct.setText(tabProduct.getSelectionModel().getSelectedItem().getCena_produktu().toString());
               choiceCat.setValue(tabProduct.getSelectionModel().getSelectedItem().getKategorie());
               choiceUnderCat.setValue(tabProduct.getSelectionModel().getSelectedItem().getPodkategorie());
               choiceProducent.setValue(tabProduct.getSelectionModel().getSelectedItem().getProducent());
               showIMGproduct.setImage(new Image(new ByteArrayInputStream(tabProduct.getSelectionModel().getSelectedItem().getZdjecie_produktu())));            
        }
        });
        ///@@@@@@@@@@@@@@@@@@@@@ REFRESH ZAZNACZONYHC TABEL np dodamy nowa kategorie i chcemy ja wybrać w tabeli produkty@@@@@@@@@@@@@@@@@@
        tabbedUnderCat.setOnSelectionChanged((Event t) -> {
            if (tabbedUnderCat.isSelected()) {
                refreshUnderCat();
            }
        });
        tabbedProduct.setOnSelectionChanged((Event t) -> {
            if (tabbedProduct.isSelected()) {
                refreshProduct();
            }
        });
        //przechwyt ścieżki pliku
        buttonIMGchoser.setOnAction(e -> {
            FileChooser filechooser = new FileChooser();
            File selected = filechooser.showOpenDialog(null);
            if(selected != null){
                textChosenFile.setText(selected.getPath());
            }});  
        //szukanie produktu
        searchingField.setOnKeyReleased(keyEvent ->{
            FilteredList<Produkty> date = new FilteredList(FXCollections.observableArrayList(produktyDAO.getAll()), p->true);
             switch(choseSearch.getSelectionModel().getSelectedItem().toString())
            {
                case "Kategorie":
                    date.setPredicate(p -> p.getKategorie().getNazwa_kategorii().toLowerCase().contains(searchingField.getText().toLowerCase()));
                    break;
                case "Podkategorie":
                    date.setPredicate(p -> p.getPodkategorie().getNazwa_podkategorii().toLowerCase().contains(searchingField.getText().toLowerCase()));
                    break;
                case "Producent":
                    date.setPredicate(p -> p.getProducent().getNazwa_producenta().toLowerCase().contains(searchingField.getText().toLowerCase()));
                    break;
                case "Nazwa produktu":
                    date.setPredicate(p -> p.getNazwa_produktu().toLowerCase().contains(searchingField.getText().toLowerCase()));
                    break;    
            }
            tabProduct.setItems(date);
        });
    }
        
   
        //@@@@@@@@@@@@@@@@@@ Przydatne funckje do użytku@@@@@@@@@@@@@@@@@@2
        //funkcje uzupelniajace TableView
       private void wklejanieKategorii(){
            idTableCat.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tabCat.getItems().indexOf(column.getValue())+1));
            nameTableCat.setCellValueFactory(new PropertyValueFactory<>("nazwa_kategorii"));
            tabCat.setItems(FXCollections.observableList(catDAO.getAll()));   
        }
       
       private void wklejaniePodkategorii(){
            idTableUnderCat.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tabUnderCat.getItems().indexOf(column.getValue())+1));
            nameTableUnderCat.setCellValueFactory(new PropertyValueFactory<>("nazwa_podkategorii"));
            nameCatTableUnderCat.setCellValueFactory(pomoc -> new SimpleStringProperty(pomoc.getValue().getKategorie().getNazwa_kategorii()));
            choiceUnderCatMain.setConverter(new StringConverter<Kategorie>(){
                @Override
                public String toString(Kategorie object) {
                    return object.getNazwa_kategorii();
                }
                @Override
                public Kategorie fromString(String string) {
                    return null;
                } 
            });
            choiceUnderCatMain.setItems(FXCollections.observableArrayList(catDAO.getAll()));
            tabUnderCat.setItems(FXCollections.observableArrayList(underCatDAO.getAll()));
        } 
       
        private void wklejanieProducenta(){
            idTableProducent.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tabProducent.getItems().indexOf(column.getValue())+1));
            nameTableProducent.setCellValueFactory(new PropertyValueFactory<>("nazwa_producenta"));
            tabProducent.setItems(FXCollections.observableList(producentDAO.getAll()));
            }
        
         private void wklejanieProduktu(){
             // wyswietlanie w odpowiednich kolumnac tableview odpowiednie wartosci
            nameTableProduct.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
            paramTableProduct.setCellValueFactory(new PropertyValueFactory<>("parametry_produktu"));
            descTableProduct.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));
            priceTableProduct.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
            //wyswietlanie w kolumnach tableview odpwoiednich wartości, czyli nazwy danych rekordów zamiast referencji do klucza
            catTableProduct.setCellValueFactory(pomoc -> new SimpleStringProperty(pomoc.getValue().getKategorie().getNazwa_kategorii()));
            underCatTableProduct.setCellValueFactory(pomoc -> new SimpleStringProperty(pomoc.getValue().getPodkategorie().getNazwa_podkategorii()));
            producentTableProduct.setCellValueFactory(pomoc -> new SimpleStringProperty(pomoc.getValue().getProducent().getNazwa_producenta()));
            //wyswietlanie w comboboxach odpowiednich wartosci z kluczy
            choiceCat.setConverter(new StringConverter<Kategorie>(){
                @Override
                public String toString(Kategorie object) {
                    return object.getNazwa_kategorii();
                }
                @Override
                public Kategorie fromString(String string) {
                    return null;
                } 
            });
            choiceUnderCat.setConverter(new StringConverter<Podkategorie>(){
                @Override
                public String toString(Podkategorie object) {
                    return object.getNazwa_podkategorii();
                }
                @Override
                public Podkategorie fromString(String string) {
                    return null;
                } 
            });
            choiceProducent.setConverter(new StringConverter<Producent>(){
                @Override
                public String toString(Producent object) {
                    return object.getNazwa_producenta();
                }
                @Override
                public Producent fromString(String string) {
                    return null;
                } 
            });
            choiceCat.setItems(FXCollections.observableArrayList(catDAO.getAll()));
            choiceUnderCat.setItems(FXCollections.observableArrayList(underCatDAO.getAll()));
            choiceProducent.setItems(FXCollections.observableArrayList(producentDAO.getAll()));
            choseSearch.getItems().addAll("Kategorie", "Podkategorie","Nazwa produktu","Producent");
            tabProduct.setItems(FXCollections.observableList(produktyDAO.getAll()));
            }  
         //refreshe comboboxow jak dodamy cos nowego
         private void refreshUnderCat(){
            choiceUnderCatMain.setItems(FXCollections.observableArrayList(catDAO.getAll()));
         }
         private void refreshProduct(){
            choiceCat.setItems(FXCollections.observableArrayList(catDAO.getAll()));
            choiceUnderCat.setItems(FXCollections.observableArrayList(underCatDAO.getAll()));
            choiceProducent.setItems(FXCollections.observableArrayList(producentDAO.getAll()));
         }       
}