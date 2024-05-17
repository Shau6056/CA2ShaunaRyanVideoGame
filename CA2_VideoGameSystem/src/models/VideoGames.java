package models;

import controllers.MyHashTable;

public class VideoGames implements Comparable<VideoGames> {

    private String title;
    private String yearRel;
    private String platform;
    private String genre;
    private String gamePlayDes;
    private MyHashTable<String, Developers> developers;

    public VideoGames(String title, String yearRel, String platform, String genre, String gamePlayDes) {
        this.title = title;
        this.yearRel = yearRel;
        this.platform = platform;
        this.genre = genre;
        this.gamePlayDes = gamePlayDes;
        this.developers = new MyHashTable<>(50);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearRel() {
        return yearRel;
    }

    public void setYearRel(String yearRel) {
        this.yearRel = yearRel;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGamePlayDes() {
        return gamePlayDes;
    }

    public void setGamePlayDes(String gamePlayDes) {
        this.gamePlayDes = gamePlayDes;
    }
    public void addDeveloper(Developers developer) {
        developers.put(developer.getName(), developer);
    }

    @Override
    public String toString() {
        return "Video Games {" +
                "Title='" + title + '\'' +
                ", Year Released='" + yearRel + '\'' +
                ", Platform='" + platform + '\'' +
                ", Genre='" + genre + '\'' +
                ", Game Play Description='" + gamePlayDes + '\'' +
                '}';
    }



    @Override
    public int compareTo(VideoGames game) {//just comparing the titles
        return this.title.compareTo(game.title);
    }
}
