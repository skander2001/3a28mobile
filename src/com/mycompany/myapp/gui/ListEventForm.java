/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Feedback;
import com.mycompany.myapp.services.ServicesEvent;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ListEventForm extends Form {

    public ListEventForm(Form previous) {
        setTitle("List Event");
       setLayout(BoxLayout.y());
        TextField searchField = new TextField("", "Search");
    Button searchButton = new Button("Search");
    searchButton.addActionListener(e -> searchEvents(searchField.getText()));
    Button sortButton = new Button("Sort by Price");
sortButton.addActionListener(e -> sortEventsByPrice());
add(sortButton);
    
    // Add search field and button to form
    add(searchField);
    add(searchButton);
    
       
        ArrayList<Evenement> evenements = ServicesEvent.getInstance().findAll();
        for (Evenement t : evenements) {
             addElement(t);
            
            

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
     
    
     public void addElement(Evenement event) {
 Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");
    Label Titre= new Label("titre: " + event.getTitre());
    Label description= new Label("desc: " + event.getDescription());
    Label prix=new Label("prix:"+event.getPrix());
          

    Button editButton = new Button("Modifier");
    editButton.addActionListener(e -> {
    UpdateEventForm updateForm = new UpdateEventForm(this, event);
    updateForm.show();




    // Afficher la nouvelle page
});
     Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(e -> {
    try {

       if( ServicesEvent.getInstance().deletEvent(event.getId())){
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
    Button commentairesButton = new Button("feedbacks");
commentairesButton.addActionListener(e -> {
    Evenement evenement = event;
   ArrayList<Feedback> feedbacks = null;
try {
        // appel au service JSON pour récupérer les commentaires pour le statut sélectionné
            String url = Statics.BASE_URL +"Allfeedback/"+evenement.getId();

            ConnectionRequest request = new ConnectionRequest(url);
    request.setPost(false);
    request.setContentType("application/json");
    NetworkManager.getInstance().addToQueueAndWait(request);
    JSONParser parser = new JSONParser();
    Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
    feedbacks = (ArrayList<Feedback>) response.get("root");
} catch (IOException ex) {
    ex.printStackTrace();
}
if (feedbacks != null ) {
    FeedForm commentairesForm = new FeedForm(this, feedbacks, evenement);
    commentairesForm.show();
} else {
    Dialog.show("Aucun feedback", "Aucun feedback ", "OK", null);
}
});
     container.add(BorderLayout.WEST, BoxLayout.encloseY(
            Titre, description,prix
    ));
    container.add(BorderLayout.EAST, BoxLayout.encloseY(
            editButton,deleteButton,commentairesButton
    ));
     Container separator = new Container();
    separator.setUIID("Separator");
    add(separator);

    add(container);
    
    
     }
     public void refreshList() {
    removeAll();
    ArrayList<Evenement> evenements = ServicesEvent.getInstance().findAll();
    for (Evenement e : evenements) {
        addElement(e);
    }
    revalidate();
}
     public void searchEvents(String searchText) {
    removeAll();
    ArrayList<Evenement> evenements = ServicesEvent.getInstance().searchEvent(searchText);
    for (Evenement e : evenements) {
        addElement(e);
    }
    revalidate();
}
     public void sortEventsByPrice() {
    ArrayList<Evenement> events = ServicesEvent.getInstance().findAll();
    
    Collections.sort(events, new Comparator<Evenement>() {
        @Override
        public int compare(Evenement p1, Evenement p2) {
            return Double.compare(p1.getPrix(), p2.getPrix());
        }
    });

    removeAll();
    for (Evenement e : events) {
        addElement(e);
    }
    revalidate();
}
     
}
    
    

    
    

