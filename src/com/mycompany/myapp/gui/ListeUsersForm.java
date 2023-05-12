/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;
/**
 *
 * @author Skander
 */
public class ListeUsersForm extends Form {
    
    
    public ListeUsersForm(Form previous) {
      setTitle("Liste Users");
        setLayout(BoxLayout.y());

        ArrayList<User> users = ServiceUser.getInstance().getAllUsers();
        for (User t : users) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

   public void addElement(User user) {

    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");
    Label idLabel = new Label("id: " + user.getId());
    Label usernameLabel = new Label("Username: " + user.getUsername());
    Label password = new Label("Password: " + user.getPassword());
    Label Email = new Label("Email: " + user.getEmail());
    Label Tel = new Label("Tel: " + user.getTel());

    Button editButton = new Button("Modifier");
  editButton.addActionListener(e -> {
    UpdateUserForm updateForm = new UpdateUserForm(this, user);
    updateForm.show();
});

   
    
    

    Button deleteButton = new Button("Supprimer");
  deleteButton.addActionListener(e -> {
    try {

       if( ServiceUser.getInstance().deleteUser(user.getId())){
            // Suppression réussie, enlever le container correspondant
            removeComponent(container);
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);

        } else 
            // Afficher un message d'erreur si la suppression échoue
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);
            this.refreshList();

        }
     catch (Exception ex) {
        // Afficher une exception si elle se produit
        ex.printStackTrace();
        Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression", "OK", null);
    }
    });


    container.add(BorderLayout.WEST, BoxLayout.encloseY(idLabel,
            usernameLabel, password, Email, Tel
    ));
    container.add(BorderLayout.EAST, BoxLayout.encloseY(
            editButton, deleteButton
    ));

    // Ajouter un séparateur rouge entre chaque élément
    Container separator = new Container();
    separator.setUIID("Separator");
    add(separator);

    add(container);
}

public void refreshList() {
        removeAll();
        ArrayList<User> users = ServiceUser.getInstance().getAllUsers();
        for (User s : users) {
            addElement(s);
        }
        revalidate();
}



    
    
    
}
