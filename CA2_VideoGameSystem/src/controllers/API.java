package controllers;

import models.VideoGames;
import models.Developers;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class API {
    private MyHashTable<String, VideoGames> videoGamesHash;
    private MyHashTable<String, Developers> developersHash;

    public API() {
        this.videoGamesHash = new MyHashTable<>(300);
        this.developersHash = new MyHashTable<>(100);
    }

    public void addVideoGame(String title, String yearRel, String platform, String genre, String gamePlayDescription) {
        if (videoGamesHash.get(title) != null) {
            return;
        }
        VideoGames game = new VideoGames(title, yearRel, platform, genre, gamePlayDescription);
        videoGamesHash.put(title, game);
    }

    public void addDeveloper(String name, String dateOfInc, String country, String[] famousTitles) {
        if (developersHash.get(name) != null) {
            return;
        }
        Developers developer = new Developers(name, dateOfInc, country, famousTitles);
        developersHash.put(name, developer);
    }

    public void addDevRoleToDeveloper(String name, String devRole, String devRoleDescription) {
        Developers dev = getDeveloperByName(name);
        if (dev != null) {
            dev.addRole(devRole, devRoleDescription);
        } else {
            System.out.println("Developer with name " + name + " does not exist.");
        }
    }

    public void assignDeveloperToGame(String gameTitle, String developerName) {
        VideoGames game = videoGamesHash.get(gameTitle);
        Developers developer = developersHash.get(developerName);
        if (game != null && developer != null) {
            game.addDeveloper(developer);
        } else {
            if (game == null) {
                System.out.println("Game with title " + gameTitle + " does not exist.");
            }
            if (developer == null) {
                System.out.println("Developer with name " + developerName + " does not exist.");
            }
        }
    }

    public MyArrayList<VideoGames> getAllVideoGames() {
        MyArrayList<VideoGames> gamesList = new MyArrayList<>();
        for (int i = 0; i < videoGamesHash.getCapacity(); i++) {
            MyLinkedList<MyHashTable.Entry<String, VideoGames>> bucket = videoGamesHash.getBucket(i);
            if (bucket != null) {
                Node<MyHashTable.Entry<String, VideoGames>> current = bucket.getHead();
                while (current != null) {
                    gamesList.add(current.getData().getValue());
                    current = current.getNext();
                }
            }
        }
        return gamesList;
    }

    public void updateVideoGame(String title, String newYearRel, String newPlatform, String newGenre, String newGamePlayDescription) {
        VideoGames game = getVideoGame(title);
        if (game != null) {
            game.setYearRel(newYearRel);
            game.setPlatform(newPlatform);
            game.setGenre(newGenre);
            game.setGamePlayDes(newGamePlayDescription);
        }
    }

    public void updateDeveloper(String name, String newDateOfInc, String newCountry, String[] newFamousTitles) {
        Developers developer = getDeveloperByName(name);
        if (developer != null) {
            developer.setDateOfInc(newDateOfInc);
            developer.setCountry(newCountry);
            developer.setFamousTitles(newFamousTitles);
        }
    }

    public MyArrayList<Developers> getAllDevelopers() {
        MyArrayList<Developers> developersList = new MyArrayList<>();
        for (int i = 0; i < developersHash.getCapacity(); i++) {
            MyLinkedList<MyHashTable.Entry<String, Developers>> bucket = developersHash.getBucket(i);
            if (bucket != null) {
                Node<MyHashTable.Entry<String, Developers>> current = bucket.getHead();
                while (current != null) {
                    developersList.add(current.getData().getValue());
                    current = current.getNext();
                }
            }
        }
        return developersList;
    }

    public MyArrayList<VideoGames> sortVideoGamesByTitle() {
        MyArrayList<VideoGames> videoGames = getAllVideoGames();
        VideoGames[] videoGamesArray = new VideoGames[videoGames.size()];
        for (int i = 0; i < videoGames.size(); i++) {
            videoGamesArray[i] = videoGames.get(i);
        }
        VideoQuickSorting.quickSort(videoGamesArray, 0, videoGamesArray.length - 1);

        MyArrayList<VideoGames> sortedVideoGames = new MyArrayList<>();
        for (VideoGames game : videoGamesArray) {
            sortedVideoGames.add(game);
        }
        return sortedVideoGames;
    }
    public VideoGames getVideoGame(String title) {
        return videoGamesHash.get(title);
    }

    public MyArrayList<Developers> sortDevelopersByName() {
        MyArrayList<Developers> developers = getAllDevelopers();
        Developers[] developersArray = new Developers[developers.size()];
        for (int i = 0; i < developers.size(); i++) {
            developersArray[i] = developers.get(i);
        }
        DeveloperMergeSort.mergeSort(developersArray, 0, developersArray.length - 1);

        MyArrayList<Developers> sortedDevelopers = new MyArrayList<>();
        for (Developers developer : developersArray) {
            sortedDevelopers.add(developer);
        }
        return sortedDevelopers;
    }
    public String sortVideogamesByYear() {
        MyArrayList<VideoGames> videoGames = getAllVideoGames();
        VideoGames[] videoGamesArray = new VideoGames[videoGames.size()];
        for (int i = 0; i < videoGames.size(); i++) {
            videoGamesArray[i] = videoGames.get(i);
        }
        VideoYearRelSort.insertionSort(videoGamesArray);

        for (VideoGames game : videoGamesArray) {
            System.out.println(game);
        }
        return videoGames.toString();
    }

    public VideoGames getAllVideoGameByTitle(String title) {
        return videoGamesHash.get(title);
    }

    public Developers getDeveloperByName(String name) {
        return developersHash.get(name);
    }

    public Developers getDeveloperByCountry(String country) {
        return developersHash.get(country);
    }

    public MyArrayList<VideoGames> searchVideoGameByYear(String year) {
        MyArrayList<VideoGames> results = new MyArrayList<>();
        for (int i = 0; i < videoGamesHash.getCapacity(); i++) {
            MyLinkedList<MyHashTable.Entry<String, VideoGames>> bucket = videoGamesHash.getBucket(i);
            if (bucket != null) {
                Node<MyHashTable.Entry<String, VideoGames>> current = bucket.getHead();
                while (current != null) {
                    if (current.getData().getValue().getYearRel().equalsIgnoreCase(year)) {
                        results.add(current.getData().getValue());
                    }
                    current = current.getNext();
                }
            }
        }
        return results;
    }

    public void deleteVideoGameTitle(String title) {
        videoGamesHash.remove(title);
    }

    public MyArrayList<VideoGames> searchVideoGamesByPlatform(String platform) {
        MyArrayList<VideoGames> results = new MyArrayList<>();
        for (int i = 0; i < videoGamesHash.getCapacity(); i++) {
            MyLinkedList<MyHashTable.Entry<String, VideoGames>> bucket = videoGamesHash.getBucket(i);
            if (bucket != null) {
                Node<MyHashTable.Entry<String, VideoGames>> current = bucket.getHead();
                while (current != null) {
                    if (current.getData().getValue().getPlatform().equalsIgnoreCase(platform)) {
                        results.add(current.getData().getValue());
                    }
                    current = current.getNext();
                }
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        // List of classes to include in serialization
        Class<?>[] classes = new Class[]{
                API.class, VideoGames.class, Developers.class,
                MyHashTable.class, MyLinkedList.class, Node.class,
                VideoQuickSorting.class, DeveloperMergeSort.class, VideoYearRelSort.class
        };

        // Setting up XStream object with default security and allowed classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        // Deserialization from XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        videoGamesHash = (MyHashTable<String, VideoGames>) in.readObject();
        developersHash = (MyHashTable<String, Developers>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(videoGamesHash);
        out.writeObject(developersHash);
        out.close();
    }

    public String fileName() {
        return "data.xml";
    }
}
