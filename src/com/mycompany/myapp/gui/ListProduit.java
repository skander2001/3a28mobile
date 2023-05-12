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
import com.mycompany.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author USER
 */
public class ListProduit extends Form {
    
public ListProduit (Form previous) {
        setTitle("Forum");
        setLayout(BoxLayout.y());
        
    TextField searchField = new TextField("", "Search");
    Button searchButton = new Button("Search");
    searchButton.addActionListener(e -> searchProduit(searchField.getText()));
    
    // Add search field and button to form
    add(searchField);
    add(searchButton);
    
    Button sortButton = new Button("Sort by Price");
sortButton.addActionListener(e -> sortProduitsByPrice());
add(sortButton);

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
        for (Produit t : produits) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
public void addElement(Produit prod) {

    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");

    Label nomLabel = new Label("nom: " + prod.getNom());
    Label descriptionLabel = new Label("description: " + prod.getDescription());
    Label prixLabel = new Label("prix: " + prod.getPrix());
    //Label imageLabel = new Label("image: " + prod.getImage());
    Label quantitéLabel = new Label("stock= " + prod.getQte_stock());
    //Label categorieLabel = new Label("cat: " + prod.getId_ctg());

    Button editButton = new Button("Modifier");
    editButton.addActionListener(e -> {
    ModifProduit updateForm = new ModifProduit(this, prod);
    updateForm.show();




    // Afficher la nouvelle page
});

    
    
    

    Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(e -> {
    try {

       if( ServiceProduit.getInstance().deletProduit(prod.getId())){
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
            nomLabel, descriptionLabel, prixLabel, quantitéLabel 
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
    ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
    for (Produit p : produits) {
        addElement(p);
    }
    revalidate();
}

 public void searchProduit(String searchText) {
    removeAll();
    ArrayList<Produit> produits = ServiceProduit.getInstance().searchProduit(searchText);
    for (Produit p : produits) {
        addElement(p);
    }
    revalidate();
}
     
public void sortProduitsByPrice() {
    ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
    
    Collections.sort(produits, new Comparator<Produit>() {
        @Override
        public int compare(Produit p1, Produit p2) {
            return Double.compare(p1.getPrix(), p2.getPrix());
        }
    });

    removeAll();
    for (Produit p : produits) {
        addElement(p);
    }
    revalidate();
}
    
}
