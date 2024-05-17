package Test;

import controllers.DeveloperMergeSort;
import controllers.VideoYearRelSort;
import models.Developers;
import models.VideoGames;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VideoYearRelInsertionSort {

    @Test    public void testInsertionSortByYearOfRelease() {
        VideoGames[] videoGamesArray = {
                new VideoGames("The Witcher 3", "2015", "Play Station", "RPG", "Open world explore game"),
                new VideoGames("Battlefield", "2020", "Xbox", "Shooter", "A battle game"),
                new VideoGames("Sonic the Hedgehog", "2011", "Nintendo", "Adventure", "Level game"),
                new VideoGames("The Legend of Zelda", "2017", "Switch", "Adventure", "An open world adventure game")
        };


        for (int i = 0; i < videoGamesArray.length; i++) {
            System.out.println(videoGamesArray[i].getTitle() + " - " + videoGamesArray[i].getYearRel());
        }


        VideoYearRelSort.insertionSort(videoGamesArray);


        for (int i = 0; i < videoGamesArray.length; i++) {
            System.out.println(videoGamesArray[i].getTitle() + " - " + videoGamesArray[i].getYearRel());
        }


        String[] expectedOrder = {"Battlefield", "The Legend of Zelda", "The Witcher 3", "Sonic the Hedgehog"};


        for (int i = 0; i < videoGamesArray.length; i++) {
            assertEquals(expectedOrder[i], videoGamesArray[i].getTitle());
        }
    }
}
