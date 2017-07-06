package com.github.xadro3.firebot9k;

import java.util.*;

/**
 * Created by fabio on 06.07.2017.
 */
public class FireMath {

public static double fireMath(String message){

    double solution = 0;
    ListIterator spliterator;
    String helperString = message.substring(11);

    char[] toSplit = helperString.toCharArray();

    List<char[]> asList = Arrays.asList(toSplit);

    List<Character> splitList = new ArrayList<Character>();
    for (char c : toSplit) {
        splitList.add(c);
    }

    spliterator = splitList.listIterator();

    while (spliterator.hasNext()){

        //if(spliterator.next()<11)

    }





    return solution;
    }

}
