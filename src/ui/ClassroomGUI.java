package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnLogIn;
    

    
    @FXML
    private BorderPane registerPane;
    
    @FXML
    private Button btnSignIn1;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField txtUsername1;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private TextField txtProfilePhoto;

    @FXML
    private Button btnBrowse;

    @FXML
    private CheckBox cbSoftware;

    @FXML
    private CheckBox cbTelematic;

    @FXML
    private CheckBox cbIndustrial;

    @FXML
    private ChoiceBox<String> chbFBrowser;

    @FXML
    private DatePicker dpBirthday;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbOther;
    
    private ToggleGroup radioGroup;
    
    
    @FXML
    private BorderPane accountPane;
    
    @FXML
    private Button btnLogOut;

    @FXML
    private ImageView imvImage;

    @FXML
    private Label lblUsername;

    @FXML
    private TableView<UserAccount> tvUsers;

    @FXML
    private TableColumn<UserAccount, String> tcUsername;

    @FXML
    private TableColumn<UserAccount, String> tcGender;

    @FXML
    private TableColumn<UserAccount, String> tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, String> tcBrowser;
    
    private Classroom classroom;
    
    public ClassroomGUI(Classroom cr) {
    	classroom = cr;
    }
    
    public void initialize() {
    	
    }
    
    private void initializeTableView() {
    	ObservableList<UserAccount> observableList = FXCollections.observableArrayList(classroom.getAccounts());
    	
    	tvUsers.setItems(observableList);
    	tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Username"));
    	tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Gender"));
    	tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Career"));
    	tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Birthday"));
    	tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Favorite Browser"));
    }

    @FXML
    void loadAccountList(ActionEvent event) throws IOException {
    	if(!classroom.getAccounts().isEmpty()) {
    		for(int i=0; i<classroom.getAccounts().size(); i++) {
	    		if(classroom.getAccounts().get(i).getUsername().equals(txtUsername.getText()) && classroom.getAccounts().get(i).getPassword().equals(txtPassword.getText())) {
	    			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
	    	    	fxmlLoader.setController(this);    	
	    	    	
	    	    	Parent accountListPane = fxmlLoader.load();
	    	    	mainPane.getChildren().clear();
	    	    	mainPane.setCenter(accountListPane);
	    			
	    			initializeTableView();
	    			lblUsername.setText(txtUsername.getText());
	    			File file = new File(txtProfilePhoto.getText());
	    	        Image image = new Image(file.toURI().toString());
	    			imvImage.setImage(image);
	    		}
	    		else {
	    			Alert alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Log in incorrect");
	    			alert.setHeaderText("The username and password given are incorrect");
	    			alert.setContentText(null);
	    			alert.showAndWait();
	    		}
	    	}
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Log in incorrect");
			alert.setHeaderText("The username and password given are incorrect");
			alert.setContentText(null);
			alert.showAndWait();
    	}
    }

    @FXML
    void loadSignUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlLoader.setController(this);    	
    	
    	Parent signUpPane = fxmlLoader.load();
    	mainPane.getChildren().clear();
    	mainPane.setCenter(signUpPane);
    	
    	radioGroup = new ToggleGroup();
    	rbMale.setToggleGroup(radioGroup);
    	rbFemale.setToggleGroup(radioGroup);
    	rbOther.setToggleGroup(radioGroup);
    	
    	
    	chbFBrowser.getItems().add("Chrome");
    	chbFBrowser.getItems().add("FireFox");
    	chbFBrowser.getItems().add("Edge");
    	chbFBrowser.getItems().add("Opera");
    	chbFBrowser.getItems().add("Brave");
    	
    }
        
    @FXML
    void createAccount(ActionEvent event) {
	    String gSelected = "";
	    if(rbMale.isFocused()) {
	    	gSelected = rbMale.getText();
	    }else if(rbFemale.isFocused()) {
	    	gSelected = rbFemale.getText();
	    }else if(rbOther.isFocused()) {
	    	gSelected = rbOther.getText();
	    }
	    
	    String career = "";
	    if(cbSoftware.isSelected() && cbTelematic.isSelected() && cbIndustrial.isSelected()) {
	    	career = cbSoftware.getText() + " "+ cbTelematic.getText() + " "+ cbIndustrial.getText();
	    }else if(cbSoftware.isSelected() && cbTelematic.isSelected() && !(cbIndustrial.isSelected())){
	    	career = cbSoftware.getText() + " "+ cbTelematic.getText();
	    }else if(cbSoftware.isSelected() && cbIndustrial.isSelected() && !(cbTelematic.isSelected())){
	    	career = cbSoftware.getText() + " "+ cbIndustrial.getText();
	    }else if(cbTelematic.isSelected() && cbIndustrial.isSelected() && !(cbSoftware.isSelected())){
	    	career = cbTelematic.getText() + " "+ cbIndustrial.getText();
	    }else if(cbSoftware.isSelected() && !(cbTelematic.isSelected() && cbIndustrial.isSelected())) {
	    	career = cbSoftware.getText();
	    }else if(cbTelematic.isSelected() && !(cbSoftware.isSelected() && cbIndustrial.isSelected())) {
	    	career = cbTelematic.getText();
	    }else if(cbIndustrial.isSelected() && !(cbSoftware.isSelected() && cbTelematic.isSelected())) {
	    	career = cbIndustrial.getText();
	    }
	    String browser = chbFBrowser.getSelectionModel().getSelectedItem();
	    
	    Classroom.addUser(txtUsername1.getText(), txtPassword1.getText(), gSelected, career, dpBirthday.getValue().toString(), browser);
       	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Account created");
		alert.setHeaderText(null);
		alert.setContentText("The new account has been created");
		alert.showAndWait();
    }
	

    @FXML
    void loadLogIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	Parent logInPane = fxmlLoader.load();
    	
    	registerPane.getChildren().clear();
    	registerPane.setTop(logInPane);
    	
    }

    @FXML
    void openFileExplorer(ActionEvent event) {
    	FileChooser fc = new FileChooser();
   	 	fc.setTitle("Open Resource File");
   	 	File selectedFile = fc.showOpenDialog(null);
   	 	if (selectedFile != null) {
   	 		txtProfilePhoto.setText(selectedFile.getPath());
   	 	}
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	Parent logOutPane = fxmlLoader.load();
    	
    	registerPane.getChildren().clear();
    	registerPane.setTop(logOutPane);
    }

}
