/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.utils;

/**
 *
 * @author tim
 */
public class TestUtils {

    public static int validYear(String year) throws NonValidYearException{
	if(year.isEmpty())
            return -1;
        try{
	    int y = Integer.parseInt(year);
	    	if(y >= 0 && y < 3000)
		    return y;
		else
		    throw new NonValidYearException();
	}catch(NumberFormatException e){
	    throw new NonValidYearException();
	}
    }
}
