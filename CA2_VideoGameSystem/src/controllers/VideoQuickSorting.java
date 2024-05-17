package controllers;

import models.VideoGames;


//Using the quick sort method to sort the video games
public class VideoQuickSorting {

    public static void quickSort(VideoGames[] a, int lowerIndex, int higherIndex) {//we send in our array of video games, then the index of the lower and higher
        int leftIndex = lowerIndex;//we set them as left and right
        int rightIndex = higherIndex;
        VideoGames pivot = a[lowerIndex + (higherIndex - lowerIndex) / 2];//then we have the type videogames called pivot and will be the middle


        //This is for the partitioning process
        while (leftIndex <= rightIndex) {//while the left is less then or equal to the right - meaning we are finding elements that are on the left that should be on the right
            while (a[leftIndex].compareTo(pivot) < 0) leftIndex++;//we use String comparison to compare alphabetically is greater then 0 then we will move to the next left
            while (a[rightIndex].compareTo(pivot) > 0) rightIndex--;//and the same but using the right comparing to the middle(pivot) and then go down on the right side

            if (leftIndex <= rightIndex) {//if left is less then or equal to the right
                VideoGames swap = a[leftIndex];//temp used to swap
                a[leftIndex] = a[rightIndex];//swap them over
                a[rightIndex] = swap;

            //then increment to next.
                leftIndex++;
                rightIndex--;
            }
        }

        if (lowerIndex < rightIndex) quickSort(a, lowerIndex, rightIndex);//we are recursively calling our own method from this sort out the left
        if (leftIndex < higherIndex) quickSort(a, leftIndex, higherIndex);//sort out the right
    }
}


