package edu.esprit.controller;

import edu.esprit.dao.EventsDao;
import edu.esprit.entity.Events;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class AfficherEventClientController implements Initializable {

    @FXML
    private Button sortButton;
    
    @FXML
    private Text Acceuil;

    @FXML
    private Text Boutique;

    @FXML
    private Text Contact;

    @FXML
    private Text Details;

    @FXML
    private Text Réclamation;

    @FXML
    private Text Seconnecter;

    @FXML
    private Text Sinscrire;

    @FXML
    private Button buttonSearch;

    @FXML
    private Pane eventsfield;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField search;

    @FXML
    private Text text1;

    @FXML
    private VBox cardlayout;

    @FXML
    private GridPane container;

    private EventsDao eventsDao = new EventsDao();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadEvents();
        // TODO
    }

    private void loadEvents() {
        container.getChildren().clear(); // clear previous cards

        int column = 0;
        int row = 1;
        try {
            List<Events> events = eventsDao.displayAll(); // get all events

            for (Events event : events) {
                FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/edu/esprit/view/EventCard.fxml"));
                Pane card = cardLoader.load();
                EventCardController eventCardController = cardLoader.getController();
                eventCardController.setData(event);
                cardlayout.getChildren().add(card);

                if(column ==2){
                    column = 0;
                    ++row;
                }
                container.add(card,column++, row);
                GridPane.setMargin(card, new Insets(10));
                card.setCursor(Cursor.HAND);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
private void handleSearchButtonAction(ActionEvent event) {
    String name = search.getText().trim();
    if (!name.isEmpty()) {
        List<Events> events = eventsDao.searchByName(name);
        loadEvents(events);
    } else {
        loadEvents();
    }
}

private void loadEvents(List<Events> events) {
    container.getChildren().clear(); // clear previous cards

    int column = 0;
    int row = 1;
    try {
        for (Events event : events) {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/edu/esprit/view/EventCard.fxml"));
            Pane card = cardLoader.load();
            EventCardController eventCardController = cardLoader.getController();
            eventCardController.setData(event);
            cardlayout.getChildren().add(card);

            if(column ==2){
                column = 0;
                ++row;
            }
            container.add(card,column++, row);
            GridPane.setMargin(card, new Insets(10));
            card.setCursor(Cursor.HAND);

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
private void sortEventsByDate() {
    List<Events> events = eventsDao.displayAll();
    events.sort(Comparator.comparing(Events::getDate));
    loadEvents(events);
}




}
