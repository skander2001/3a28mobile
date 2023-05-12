/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.codename1.io.ConnectionRequest;
import com.mycompany.entities.Commentaire;
import com.mycompany.entities.Statut;
import com.mycompany.myapp.services.ServiceStatut;
import com.mycompany.myapp.utils.Statics;


/**
 *
 * @author user
 */
public class ListStatutsForm extends Form {

    public ListStatutsForm(Form previous) {
        setTitle("Forum");
        setLayout(BoxLayout.y());

        TextField searchField = new TextField("", "Recherche");

                Button searchButton = new Button("Rechercher");

        // Add ActionListener to search button
        searchButton.addActionListener(e -> {
    String searchQuery = searchField.getText();
    ArrayList<Statut> statuts = ServiceStatut.getInstance().getStatutsFromJson(searchQuery);
    refreshList(statuts);
});
        
        
       ComboBox<String> typeComboBox = new ComboBox<>("StreetArt", "Poterie", "Musique", "Danse" , "Peinture" , "Sculpture");
          Button filterButton = new Button("Filtrer");
      
          
          filterButton.addActionListener(e -> 
          {String selectedType = typeComboBox.getSelectedItem();
    ArrayList<Statut> filteredStatuts = ServiceStatut.getInstance().getFilteredStatuts(selectedType);
    removeAll();
    for (Statut s : filteredStatuts) {
        addElement(s);
    }
    revalidate();
});
                add(ComponentGroup.enclose(searchField, searchButton));

    add(ComponentGroup.enclose(typeComboBox, filterButton));
         
        ArrayList<Statut> statuts = ServiceStatut.getInstance().getAllStatuts();
        for (Statut t : statuts) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
    
    }



  
   public void addElement(Statut stat) {

    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");

    Label usernameLabel = new Label("Username: " + stat.getUsername());
    Label titreLabel = new Label("Titre: " + stat.getTitre());
    Label contenuLabel = new Label("Contenu: " + stat.getContenu());
    Label likesLabel = new Label("Likes: " + stat.getNbrLike());

    
    Button editButton = new Button("Modifier");
    editButton.addActionListener(e -> {
    UpdateStatutForm updateForm = new UpdateStatutForm(this, stat);
    updateForm.show();
});
    
    
    
Button commentairesButton = new Button("Commentaires");
commentairesButton.addActionListener(e -> {
    Statut statut = stat;
   ArrayList<Commentaire> commentaires = null;
try {
        // appel au service JSON pour récupérer les commentaires pour le statut sélectionné
            String url = Statics.BASE_URL + "AllComments/"+statut.getId();

            ConnectionRequest request = new ConnectionRequest(url);
    request.setPost(false);
    request.setContentType("application/json");
    NetworkManager.getInstance().addToQueueAndWait(request);
    JSONParser parser = new JSONParser();
    Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
    commentaires = (ArrayList<Commentaire>) response.get("root");
} catch (IOException ex) {
    ex.printStackTrace();
}
if (commentaires != null ) {
    CommentairesForm commentairesForm = new CommentairesForm(this, commentaires, statut);
    commentairesForm.show();
} else {
    Dialog.show("Aucun commentaire", "Aucun commentaire pour ce statut", "OK", null);
}
});
    

    Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(e -> {
    try {

       if( ServiceStatut.getInstance().deletStatut(stat.getId())){
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
            usernameLabel, titreLabel, contenuLabel, likesLabel
    ));
    container.add(BorderLayout.EAST, BoxLayout.encloseY(
            editButton, deleteButton , commentairesButton
    ));

    // Ajouter un séparateur rouge entre chaque élément
    Container separator = new Container();
    separator.setUIID("Separator");
    add(separator);

    add(container);
}

public void refreshList() {
    removeAll();
    ArrayList<Statut> statuts = ServiceStatut.getInstance().getAllStatuts();
    for (Statut s : statuts) {
        addElement(s);
    }
    revalidate();

}
public void refreshList(ArrayList<Statut> statuts) {
    removeAll();
    for (Statut s : statuts) {
        addElement(s);
    }
    revalidate();
}

}
