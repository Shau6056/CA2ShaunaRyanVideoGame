package controllers;

import models.VideoGames;
import models.Developers;

// This class implements the MergeSort algorithm to sort an array of Developers by their names.
public class DeveloperMergeSort {

    // Public method to initiate the merge sort on the array.
    public static void mergeSort(Developers[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2; //finding the middle point of the array

            //again like the quicksort we are recursively calling method
            mergeSort(array, left, middle);//sort out the left
            mergeSort(array, middle + 1, right);//and the same for the right

            merge(array, left, middle, right);//then put them back together.
        }
    }

    // Private method to merge two sorted subarrays back into one sorted array.
    private static void merge(Developers[] array, int left, int middle, int right) {
        int arr1 = middle - left + 1; // Size of the left subarray
        int arr2 = right - middle; // Size of the right subarray

        // Temporary arrays to hold the data for the left and right subarrays
        Developers[] lef = new Developers[arr1];
        Developers[] rig = new Developers[arr2];

        // Copy data into the temporary left subarray
        for (int i = 0; i < arr1; ++i)
            lef[i] = array[left + i];
        // Copy data into the temporary right subarray
        for (int j = 0; j < arr2; ++j)
            rig[j] = array[middle + 1 + j];

        int i = 0, j = 0; // Initial indexes of the two subarrays
        int k = left; // Initial index of the merged subarray

        // Merge the temporary arrays back into the original array
        while (i < arr1 && j < arr2) {
            if (lef[i].getName().compareTo(rig[j].getName()) <= 0) { // Compare the names alphabetically
                array[k] = lef[i]; // If the left element is smaller or equal, place it in the merged array
                i++; // Move to the next element in the left subarray
            } else {
                array[k] = rig[j]; // If the right element is smaller, place it in the merged array
                j++; // Move to the next element in the right subarray
            }
            k++; // Move to the next position in the merged array
        }

        // Copy any remaining elements of the left subarray
        while (i < arr1) {
            array[k] = lef[i];
            i++;
            k++;
        }

        // Copy any remaining elements of the right subarray
        while (j < arr2) {
            array[k] = rig[j];
            j++;
            k++;
        }
    }
}
