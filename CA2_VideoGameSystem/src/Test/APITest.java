package Test;

import controllers.API;
import controllers.DeveloperMergeSort;
import controllers.MyArrayList;
import controllers.VideoQuickSorting;
import models.Developers;
import models.VideoGames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class APITest {

    private API api; // Setting up the API object

    @BeforeEach
    public void setUp() {
        api = new API(); // Instantiating the API object
    }

    @Test
    public void testAddAndGetVideoGame() {
        // Add a new video game name titles etc
        String title = "The Witcher";
        String yearRel = "2021";
        String platform = "Xbox";
        String genre = "Open World";
        String gamePlayDescription = "Fighting, exploring game";

        api.addVideoGame(title, yearRel, platform, genre, gamePlayDescription);

        VideoGames retrievedGame = api.getAllVideoGameByTitle(title);

        assertNotNull(retrievedGame, "The retrieved game should not be null");
        assertEquals(title, retrievedGame.getTitle(), "Title should match");
        assertEquals(yearRel, retrievedGame.getYearRel(), "Year of release should match");
        assertEquals(platform, retrievedGame.getPlatform(), "Platform should match");
        assertEquals(genre, retrievedGame.getGenre(), "Genre should match");
        assertEquals(gamePlayDescription, retrievedGame.getGamePlayDes(), "Gameplay description should match");
    }

    //Writing test for Listing all the games as that is currently NOT working will put in fix. The above adding feature is working
    //so not sure as of now what it is.

    //Writing some objects to add to the api. The test has come back that the there is 0 in the array when there should be 5.

    //I am going to check that the add to the hash table array is working. I put a debug statement in the add and they are coming up so that is working.
    //I have come to the conclusion that the issue is with the listallvideogames. - The issue was actually with the HashTable class, the get
    //capcity in the hashtable was returning the size instead of the table length - another issue was the no-pointer with the use of the Math.abs
    //using debug statements in this was very helpful to solve the issue.
    @Test
    public void testGetAllVideoGames() {
        // Add multiple video games
        api.addVideoGame("Towns in Ireland", "2015", "Phone App", "RPG", "Building game of Towns");
        api.addVideoGame("Witcher", "2018", "Xbox", "Open World", "Open world explorer game");
        api.addVideoGame("Crash Bandicoot", "1999", "PS5", "Level game", "Skilled level game");
        api.addVideoGame("GTA", "2011", "Xbox", "Open World", "Game with open world and levels 18+");
        api.addVideoGame("Fallout", "2014", "PS4", "Open World", "Open world explorer game");

        // Retrieve all video games
        MyArrayList<VideoGames> videoGames = api.getAllVideoGames();

        // Assert the size of the video games list
        assertEquals(5, videoGames.size(), "Size of video games list should be 5");

        // Convert MyArrayList to array
        VideoGames[] videoGamesArray = new VideoGames[videoGames.size()];
        for (int i = 0; i < videoGames.size(); i++) {
            videoGamesArray[i] = videoGames.get(i);
        }

        // Sort the video games by title using quick sort
        VideoQuickSorting.quickSort(videoGamesArray, 0, videoGamesArray.length - 1);

        // Print sorted video games for debugging// worked! Yay!
        for (VideoGames game : videoGamesArray) {
          //  System.out.println(game.getTitle());
        }

        // Assert the titles of the video games
        assertEquals("Crash Bandicoot", videoGamesArray[0].getTitle());
        assertEquals("Fallout", videoGamesArray[1].getTitle());
        assertEquals("GTA", videoGamesArray[2].getTitle());
        assertEquals("Towns in Ireland", videoGamesArray[3].getTitle());
        assertEquals("Witcher", videoGamesArray[4].getTitle());

        // Assert the years of release
        assertEquals("1999", videoGamesArray[0].getYearRel());
        assertEquals("2014", videoGamesArray[1].getYearRel());
        assertEquals("2011", videoGamesArray[2].getYearRel());
        assertEquals("2015", videoGamesArray[3].getYearRel());
        assertEquals("2018", videoGamesArray[4].getYearRel());
    }

    @Test

    public void testAllDeveloperAdd()
    {
        api.addDeveloper("John", "2010", "Japan", new String[]{"Witcher", "Assigns Creed", "Fifa"});
        api.addDeveloper("Bob", "1977", "Ireland", new String[]{"Dublins here", "Field Plough"});
        api.addDeveloper("Susan", "2022", "USA", new String[]{"Crash Bandicoot", "Fifa"});
        api.addDeveloper("James", "1950", "USA", new String[]{"GTA", "Robin Hood"});
        api.addDeveloper("Albi", "", "USA", new String[]{"GTA", "Robin Hood"});

        MyArrayList<Developers> developers = api.getAllDevelopers();

        assertEquals(5, developers.size(), "Size of video games list should be 5");

        // Convert MyArrayList to array
        Developers[] devArray = new Developers[developers.size()];
        for (int i = 0; i < developers.size(); i++) {
            devArray[i] = developers.get(i);
        }

        // Sort the video games by title using quick sort
        DeveloperMergeSort.mergeSort(devArray, 0, devArray.length - 1);

        // Print sorted video games for debugging// worked! Yay!
        for (Developers developers1 : devArray) {
            System.out.println(developers1.getName());
        }

        // Assert the titles of the video games
        assertEquals("Albi", devArray[0].getName());
        assertEquals("Bob", devArray[1].getName());
        assertEquals("James", devArray[2].getName());
        assertEquals("John", devArray[3].getName());
        assertEquals("Susan", devArray[4].getName());

    }



}
