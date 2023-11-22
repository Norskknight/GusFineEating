package database;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminPageController {
@Autowired private EmployeeControler EC;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, Employee> actionsColumn;
    @FXML
    private TextField employeeNameTextField;
    @FXML
    private TextField positionTextField;

    private ObservableList<Employee> EList;

    public void initialize() {
        EList = (FXCollections.observableArrayList(EC.findAll()));
        employeeTable.setItems(EList);

        // Configure the columns to display employee data
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
    
        employeeTable.setEditable(true);
        nameColumn.setEditable(true);
        positionColumn.setEditable(true);

        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
    // Rest of the code...

 //add employee
    @FXML
    private void openAddEmployeeDialog(){
        String employeeName = employeeNameTextField.getText(); 
        String employeeposition = positionTextField.getText(); 
        // Validate input and perform necessary operations

        if (employeeName.isEmpty() || employeeposition.isEmpty()) {
            // Display an error message or handle the invalid input
        } else {
            // Create a new Employee object
            Employee employee = new Employee();
            employee.setName(employeeName);
            employee.setPosition(employeeposition);
        
            // Add the ingredient to the table view or update the existing ingredient
            EC.save(employee);
            EList = (FXCollections.observableArrayList(EC.findAll()));
            employeeTable.setItems(EList);
            // Clear the input fields
            employeeNameTextField.clear();
            positionTextField.clear();
        }
    }

    //edit emoloyye name
     public void changeEmployeeName(CellEditEvent <Employee,String>editedCell){
         Employee employeeSelected = employeeTable.getSelectionModel().getSelectedItem();
         employeeSelected.setName(editedCell.getNewValue().toString());
         edit(employeeSelected);
     }
    //edit employee position
         public void changeEmployeePosition(CellEditEvent <Employee,String>editedCell){
         Employee employeeSelected = employeeTable.getSelectionModel().getSelectedItem();
         employeeSelected.setPosition(editedCell.getNewValue().toString());
         edit(employeeSelected);
     }
    @FXML
    private void edit(Employee employee){
        System.out.println(employee.getName());
        EC.update(employee);
        EList = (FXCollections.observableArrayList(EC.findAll()));
	    employeeTable.setItems(EList);
    }

    //delete
    @FXML
    private void deleteButtonPushed(){
    Employee selectedRows;
        selectedRows = employeeTable.getSelectionModel().getSelectedItem();
            EC.delete(selectedRows.getId());
        
        EList = (FXCollections.observableArrayList(EC.findAll()));
	    employeeTable.setItems(EList); 
    }
   
}
