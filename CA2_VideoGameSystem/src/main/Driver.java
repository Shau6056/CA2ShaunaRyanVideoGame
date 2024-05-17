package main;

import controllers.API;
import controllers.MyArrayList;
import models.VideoGames;
import models.Developers;

import java.util.Scanner;

public class Driver {
    private static final Scanner sc = new Scanner(System.in);
    public static final API api = new API();

    public static void main(String[] args) {
        /*// You can leave these in if you want rather than having to had them in the whole time

        api.addVideoGame("Towns in Ireland", "2015", "Phone App", "RPG", "Building game of Towns");
        api.addVideoGame("Witcher", "2018", "Xbox", "Open World", "Open world explorer game");
        api.addVideoGame("Crash Bandicoot", "1999", "PS5", "Level game", "Skilled level game");
        api.addVideoGame("GTA", "2011", "Xbox", "Open World", "Game with open world and levels 18+");
        api.addVideoGame("Fallout", "2014", "PS4", "Open World", "Open world explorer game");

                api.addDeveloper("John", "2010", "Japan", new String[]{"Witcher", "Assigns Creed", "Fifa"});
                api.addDeveloper("Bob", "1977", "Ireland", new String[]{"Dublins here", "Field Plough"});
                api.addDeveloper("Susan", "2022", "USA", new String[]{"Crash Bandicoot", "Fifa"});
                api.addDeveloper("James", "1950", "USA", new String[]{"GTA", "Robin Hood"});
                api.addDeveloper("Albi", "", "USA", new String[]{"GTA", "Robin Hood"});


        */
        try {
            api.load();
        } catch (Exception e) {
            System.out.println("No data found or error loading data. Starting with an empty system.");
        }

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Developer Options");
            System.out.println("2. Video Options");
            System.out.println("3. Save Data");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    developerOptions();
                    break;
                case 2:
                    videoOptions();
                    break;
                case 3:
                    try {
                        api.save();
                        System.out.println("Data saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void developerOptions() {
        while (true) {
            System.out.println("Developer Options:");
            System.out.println("1. Add Developer");
            System.out.println("2. Update Developer");
            System.out.println("3. List All Developers");
            System.out.println("4. Add Developer to game");
            System.out.println("5. Add Role to Developer");
            System.out.println("6. Back to Main Menu");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addDeveloper();
                    break;
                case 2:
                    updateDeveloper();
                    break;
                case 3:
                    listAllDevelopers();
                    break;
                case 4:
                   addVideoGameToDev();
                    break;
                case 5:
                    addDevRoleToDeveloper();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void videoOptions() {
        while (true) {
            System.out.println("Video games Options:");
            System.out.println("1. Add Video Game");
            System.out.println("2. Update Video Game");
            System.out.println("3. List All Video Games");
            System.out.println("4. Search");
            System.out.println("5. See newest to oldest games");
            System.out.println("6. Delete Video game");
            System.out.println("7. Back to Main Menu");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addVideoGame();
                    break;
                case 2:
                    updateVideoGame();
                    break;
                case 3:
                    listAllVideoGames();
                    break;
                case 4:
                    searchByTitle();
                    break;
                case 5:
                    sortAndListAllVideoGamesByYearOfRelease();
                    break;
                case 6:
                    deleteVideoGame();
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addVideoGame() {
        System.out.println("Enter the video game title:");
        String title = sc.nextLine();
        System.out.println("Enter the year of release:");
        String yearRel = sc.nextLine();
        System.out.println("Enter the platform:");
        String platform = sc.nextLine();
        System.out.println("Enter the genre:");
        String genre = sc.nextLine();
        System.out.println("Enter the gameplay description:");
        String gamePlayDescription = sc.nextLine();

        api.addVideoGame(title, yearRel, platform, genre, gamePlayDescription);
        System.out.println("Video game added successfully.");
    }

    public static void updateVideoGame() {
        System.out.println("Enter the video game title you want to update:");
        String title = sc.nextLine();
        System.out.println("Enter the new year of release:");
        String yearRel = sc.nextLine();
        System.out.println("Enter the new platform:");
        String platform = sc.nextLine();
        System.out.println("Enter the new genre:");
        String genre = sc.nextLine();
        System.out.println("Enter the new gameplay description:");
        String gamePlayDescription = sc.nextLine();

        api.updateVideoGame(title, yearRel, platform, genre, gamePlayDescription);
        System.out.println("Video game updated successfully.");
    }

    public static void addVideoGameToDev() {
        System.out.println("What is the name of the developer? Must be in the system.");
        String name = sc.nextLine();
        Developers dev = api.getDeveloperByName(name);

        if (dev == null) {
            System.out.println("Developer not found. Please add the developer first.");
            return;
        }

        System.out.println("What game would you like to add to the developer's name?");
        String gameTitle = sc.nextLine();
        VideoGames game = api.getVideoGame(gameTitle);

        if (game == null) {
            System.out.println("Game not found. Please add the game first.");
            return;
        }

        System.out.println("What is the role of the developer in this game? (Designer, Programmer, Artist)");
        String role = sc.nextLine();

        if (!role.equals("Designer") && !role.equals("Programmer") && !role.equals("Artist")) {
            System.out.println("Invalid role. Please enter a valid role (Designer, Programmer, Artist).");
            return;
        }

        api.assignDeveloperToGame(gameTitle, name);
        dev.addRole(role, gameTitle);

        System.out.println("Developer " + name + " has been assigned to the game " + gameTitle + " as " + role + ".");
    }

    public static void deleteVideoGame() {
        System.out.println("Enter the video game title you want to delete:");
        String title = sc.nextLine();
        api.deleteVideoGameTitle(title);
        System.out.println("Video game deleted successfully.");
    }

    public static void listAllVideoGames() {
        MyArrayList<VideoGames> sortedVideoGames = api.sortVideoGamesByTitle();
        for (int i = 0; i < sortedVideoGames.size(); i++) {
            System.out.println(sortedVideoGames.get(i));
        }
    }


    public static void addDevRoleToDeveloper() {
        System.out.println("Enter the developer name:");
        String name = sc.nextLine();
        System.out.println("Enter the role name: Make sure it is VALID or will NOT work Designer, Programmer, Artist ");
        String roleName = sc.nextLine();
        System.out.println("Enter the role description:");
        String roleDescription = sc.nextLine();

        api.addDevRoleToDeveloper(name, roleName, roleDescription);
        System.out.println("Role added to developer successfully.");
    }

    public static void sortAndListAllVideoGamesByYearOfRelease() {
        api.sortVideogamesByYear();
    }

    public static void searchByTitle()
    {
        System.out.println("Enter the Game name:");
        String name = sc.nextLine();
        api.getVideoGame(name);
        VideoGames video = api.getVideoGame(name);

        System.out.println("We found the game : "+ video.toString());


    }

    public static void addDeveloper() {
        System.out.println("Enter the developer name:");
        String name = sc.nextLine();
        System.out.println("Enter the date of incorporation:");
        String dateOfInc = sc.nextLine();
        System.out.println("Enter the country of origin:");
        String country = sc.nextLine();
        System.out.println("Enter the famous titles (comma separated):");
        String[] famousTitles = sc.nextLine().split(",");

        api.addDeveloper(name, dateOfInc, country, famousTitles);
        System.out.println("Developer added successfully.");
    }

    public static void updateDeveloper() {
        System.out.println("Enter the developer name you want to update:");
        String name = sc.nextLine();
        System.out.println("Enter the new date of incorporation:");
        String dateOfInc = sc.nextLine();
        System.out.println("Enter the new country of origin:");
        String country = sc.nextLine();
        System.out.println("Enter the new famous titles (comma separated):");
        String[] famousTitles = sc.nextLine().split(",");

        api.updateDeveloper(name, dateOfInc, country, famousTitles);
        System.out.println("Developer updated successfully.");
    }

    public static void listAllDevelopers() {
        MyArrayList<Developers> sortedDevelopers = api.sortDevelopersByName();
        for (int i = 0; i < sortedDevelopers.size(); i++) {
            System.out.println(sortedDevelopers.get(i));
        }
    }

}
