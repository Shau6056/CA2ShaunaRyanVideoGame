package controllers;


//For this I am using insertion sort this is to sort the year of release so if users what to find the
//newly released game this can sort this for them. The bases of this sorting algoritm is it scans over
//only the elements needed to understand where the correct position of the current elemment needs to be.

import models.VideoGames;

public class VideoYearRelSort {

    public static void insertionSort(VideoGames[] array) {//this is sorting on the year of release
        for (int i = 1; i < array.length; i++) { //it isnt starting at 0 as it sees that as sorted and goes through the array
            VideoGames key = array[i];//now that is being inserted into the new place
            int j = i - 1;//and j is set to the last in the array

            // Compare years as integers
            while (j >= 0 && Integer.parseInt(array[j].getYearRel()) < Integer.parseInt(key.getYearRel())) {
                array[j + 1] = array[j];//when this is met then move to the right to make room for the key
                j = j - 1;
            }
            array[j + 1] = key;//and then put that in at the new position.
        }
    }
}
