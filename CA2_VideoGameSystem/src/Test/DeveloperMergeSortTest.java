package Test;

import controllers.DeveloperMergeSort;
import models.Developers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeveloperMergeSortTest {

    @Test
    public void testMergeSort() {
        Developers[] developersArray = {
                new Developers("John", "2010", "Japan", new String[]{"Witcher", "Assigns Creed", "Fifa"}),
                new Developers("Albi", "1977", "Ireland", new String[]{"Dublins here", "Field Plough"}),
                new Developers("Susan", "2022", "USA", new String[]{"Crash Bandicoot", "Fifa"}),
                new Developers("Bob", "1950", "USA", new String[]{"GTA", "Robin Hood"})
        };

        for(int i = 0; i < 4; i++){
        System.out.println(developersArray[i].getName());}

        DeveloperMergeSort.mergeSort(developersArray, 0, developersArray.length - 1);

        for(int i = 0; i < 4; i++){
            System.out.println(developersArray[i].getName());}

        String[] expectedNames = {"Albi", "Bob", "John", "Susan"};
        for (int i = 0; i < developersArray.length; i++) {
            assertEquals(expectedNames[i], developersArray[i].getName());
        }
    }
}