/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.services.ServicesFeed;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class FeedForm extends Form {
    public FeedForm(Form previous,ArrayList<Feedback> feedList,Evenement ev){
         setTitle("Feedbacks");
        setLayout(BoxLayout.y());

        // Ajouter un espace vide pour séparer le formulaire des commentaires
        addComponent(BorderLayout.center(new Label("")));

        // Ajouter le formulaire au centre de l'écran
        Container formContainer = BoxLayout.encloseY(
                new Label("Ajouter un Feedback"),
                
                new TextField("", "Text"),
                new Button("Ajouter")
        );
        addComponent(BorderLayout.center(formContainer));
         String url = Statics.BASE_URL + "Allfeedback/"+ev.getId();
        ConnectionRequest request = new ConnectionRequest(url);
        request.addResponseListener(e -> {
            ArrayList<Feedback> feedbacks = new ArrayList<>();
            try {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
                for (Map<String, Object> feedback : (ArrayList<Map<String, Object>>) response.get("root")) {
                    Feedback f = new Feedback();
                    f.setId((int) Float.parseFloat(feedback.get("id").toString()));
                   

                    f.setText(feedback.get("text").toString());
                    // Ajouter d'autres propriétés de Commentaire si nécessaire
                   feedbacks.add(f);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Ajouter chaque commentaire à la liste
            for (Feedback f : feedbacks) {
                addElement(f);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        // Ajouter un bouton "Retour" pour revenir à la liste des events
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    public void addElement(Feedback feedback) {
        Container container = new Container(new BorderLayout());
        container.setUIID("ListRenderer");
        

        Label textLabel = new Label("Text :" +feedback.getText());

        container.add(BorderLayout.WEST, BoxLayout.encloseY(
                textLabel
        ));
        Button modifierButton = new Button("Modifier");
    modifierButton.addActionListener(e -> {
    UpdateFeedForm updateFeed = new UpdateFeedForm(this, feedback);
    updateFeed.show();
    });
    Button supprimerButton = new Button("Supprimer");
    supprimerButton.addActionListener(e -> {
        try {

       if( ServicesFeed.getInstance().deleteFeed(feedback.getId())){
            // Suppression réussie, enlever le container correspondant
            removeComponent(container);
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);

        } else 
            // Afficher un message d'erreur si la suppression échoue
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);

        }
     catch (Exception ex) {
        // Afficher une exception si elle se produit
        ex.printStackTrace();
        Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression", "OK", null);
    }
    });
    Container buttonsContainer = BoxLayout.encloseX(modifierButton, supprimerButton);
    container.add(BorderLayout.SOUTH, buttonsContainer);
    

        // Ajouter un séparateur rouge entre chaque élément
        Container separator = new Container();
        separator.setUIID("Separator");
        add(separator);

        add(container);
    }
    
}
