Details about the assingment and the way it was designed and some notes. - There are comments throughout the project also.

We will first look at the models package where we have our Developers, DevRole and VideoGames. All have getters and setters, constructors etc.
The Developers holds the DevRole. And then there are separate HashTables for the Developers and the Videogames. So we could list them etc.

Then we have the controllers and we have the:

API - the API holds all methods that will use the models class such as adding to the hashtables, listing etc.

DeveloperMergeSort - This is using Merge Sort to sort out the Developers based on their name. Merge sort uses a divide and conquer
                     approach that will call itself)recursively and
                     divides the array into two halves and then merges the sorted array back together.

MyArrayList - This is a user defined Array List.

MyHashTable -  A user-defined implementation of a Hash Table
               which provides fast retrieval and storage using keys. Collisions are handled using linked lists.

VideoQuickSorting - This is to sort the video titles - Quick sort uses a pivot element and partitions the array into elements less than the
                    pivot and elements > then the element the recursively sorts out the partitions.

VideoYearRelSort - and this is to sort the year released but listing the newest first. This builds one item at a time, and will put a new
                   element in into the correct position among the element previously sorted.

Driver holds the Menu - I did not use the Java FX interface as the assignment was taking so long but will be doing it through the summer
if I had more time I would have looked into it more. Also in the driver there are objects that are commented out so you can just leave them back
in and they will work. I am now sure if the xStream will work, I have it in the lib, if you need a video of me going through
the assigment, let me know. Thanks.


Overall, this project was a significant learning experience, particularly in implementing sorting methods and hashing.
It was time-consuming and challenging, especially the decision to separate developers from video games,
which required substantial refactoring. Writing tests and using debugging statements were crucial for identifying and fixing issue.
