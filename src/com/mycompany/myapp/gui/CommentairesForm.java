/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commentaire;
import com.mycompany.entities.Statut;
import com.mycompany.myapp.services.ServiceCommentaire;
import com.mycompany.myapp.utils.Statics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.io.InputStreamReader;
import java.util.Arrays;



/**
 *
 * @author user
 */

    public class CommentairesForm extends Form {
        
            private ArrayList<String> badWordsList = new ArrayList<>(Arrays.asList("shit", "kill", "hram"));

        
    public CommentairesForm(Form previous, ArrayList<Commentaire> commentairesList, Statut statut) {
        setTitle("Commentaires");
        setLayout(BoxLayout.y());

        // Ajouter un espace vide pour séparer le formulaire des commentaires
        addComponent(BorderLayout.center(new Label("")));

        // Ajouter le formulaire au centre de l'écran
        Container formContainer;
            formContainer = BoxLayout.encloseY(
                    new Label("Ajouter un commentaire"));
               TextField tfusername = new TextField("","username");

               TextField tfdescription = new TextField("", "description");

                       



              
        
        Button addcmtBtn = new Button("Ajouter");
 addcmtBtn.addActionListener(e -> {
            if (tfusername.getText().isEmpty() || tfdescription.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs !", "OK", null);
                return;
            }
 
    
            String description = tfdescription.getText();
            // Vérifier si le commentaire contient un mot interdit
            Commentaire c = new Commentaire();
    c.setUsername(tfusername.getText());
    c.setDescription(tfdescription.getText());
    c.setId_s(statut.getId());
    ServiceCommentaire.getInstance().addCmt(c);
    
            for (String badWord : badWordsList) {
                if (description.contains(badWord)) {
                    Dialog.show("Erreur", "Le commentaire contient un mot interdit : " + "****", "OK", null);
                    return;
                }
            }

    
   
    if (ServiceCommentaire.getInstance().addCmt(c)) {
        Dialog.show("Succès", "Le commentaire a été ajouté avec succès", "OK", null);
        tfusername.setText("");
        tfdescription.setText("");
        refreshList();
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de l'ajout du commentaire", "OK", null);
    }
});

                
formContainer.addAll(tfusername,tfdescription,addcmtBtn);

addComponent(BorderLayout.center(formContainer));

        // Appeler le service JSON pour récupérer les commentaires pour ce statut
        String url = Statics.BASE_URL + "AllComments/"+statut.getId();
        ConnectionRequest request = new ConnectionRequest(url);
        request.addResponseListener(e -> {
            ArrayList<Commentaire> commentaires = new ArrayList<>();
            try {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
                for (Map<String, Object> commentaire : (ArrayList<Map<String, Object>>) response.get("root")) {
                    Commentaire c = new Commentaire();
                    c.setId((int) Float.parseFloat(commentaire.get("id").toString()));
                    c.setUsername(commentaire.get("username").toString());

                    c.setDescription(commentaire.get("description").toString());
                    // Ajouter d'autres propriétés de Commentaire si nécessaire
                    commentaires.add(c);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Ajouter chaque commentaire à la liste
            for (Commentaire c : commentaires) {
                addElement(c);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        // Ajouter un bouton "Retour" pour revenir à la liste des statuts
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

  

   public void addElement(Commentaire commentaire) {
    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");
    Label usernameLabel = new Label("Username :" + commentaire.getUsername());
    Label descriptionLabel = new Label("Description :" + commentaire.getDescription());

    container.add(BorderLayout.WEST, BoxLayout.encloseY(
            usernameLabel, descriptionLabel
    ));

    // Ajouter les boutons "Modifier" et "Supprimer"
    Button modifierButton = new Button("Modifier");
    modifierButton.addActionListener(e -> {
    UpdateComForm updateCom = new UpdateComForm(this, commentaire);
    updateCom.show();
    });
    
    
    
    Button supprimerButton = new Button("Supprimer");
    supprimerButton.addActionListener(e -> {
        try {

       if( ServiceCommentaire.getInstance().deleteCommentaire(commentaire.getId())){
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
   
   public void refreshList() {
    removeAll();
  
    revalidate();
}

}
