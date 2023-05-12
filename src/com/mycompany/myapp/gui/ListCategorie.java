/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ListCategorie extends Form {
        public ListCategorie (Form previous) {
        setTitle("Forum");
        setLayout(BoxLayout.y());
                
    TextField searchField = new TextField("", "Search");
    Button searchButton = new Button("Search");
    searchButton.addActionListener(e -> searchCategorie(searchField.getText()));
    
    // Add search field and button to form
    add(searchField);
    add(searchButton);

        ArrayList<Categorie> categories = ServiceCategorie.getInstance().getAllCategorie();
        for (Categorie cat : categories) {
            addElement(cat);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
        
       public void addElement(Categorie cat) {

    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");

    Label nomctgLabel = new Label("nom: " + cat.getNom_ctg());

    Button editButton = new Button("Modifier");
    editButton.addActionListener(e -> {
    ModifCategorie updateForm = new ModifCategorie(this, cat);
    updateForm.show();




    // Afficher la nouvelle page
});
    
    Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(e -> {
    try {

       if( ServiceCategorie.getInstance().deletCategorie(cat.getId())){
            // Suppression réussie, enlever le container correspondant
            removeComponent(container);
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);

        } else 
            // Afficher un message d'erreur si la suppression échoue
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);
            refreshList();

        }
     catch (Exception ex) {
        // Afficher une exception si elle se produit
        ex.printStackTrace();
        Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression", "OK", null);
    }
    });

           container.add(BorderLayout.WEST, BoxLayout.encloseY(
            nomctgLabel
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
    ArrayList<Categorie> categories = ServiceCategorie.getInstance().getAllCategorie();
    for (Categorie c : categories) {
        addElement(c);
    }
    revalidate();
}
   
        public void searchCategorie(String searchText) {
    removeAll();
    ArrayList<Categorie> categories = ServiceCategorie.getInstance().searchCategorie(searchText);
    for (Categorie c : categories) {
        addElement(c);
    }
    revalidate();
}
    
}
