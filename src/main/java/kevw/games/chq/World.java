/*
 * World.java
 *
 * Created on June 24, 2005, 12:41 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package kevw.games.chq;

import java.awt.Point;
import java.io.*;

/**
 *
 * @author p28369
 */
public class World {
	
	/** Creates a new instance of World */
	public World() {
		//region = new CBR[NUM_CBR];
	}

	private static final int NUM_BASE = 12;
	private static final int NUM_RESOURCE = 20;
	private static final int NUM_CITY = 50;
	private static final int NUM_CAPITAL = 2;
	private static final int NUM_CBR = NUM_CAPITAL + NUM_CITY + NUM_BASE + NUM_RESOURCE;
	private static final int NUM_REGION = 184;
	private static final int MAXREGIONS = NUM_REGION;

	private CBR[] cbrs;

	public int Read_Regions() {
		try {
			FileInputStream regionFile = new FileInputStream("C:\\Games\\CHQ\\regions.dat");
			DataInputStream ois = new DataInputStream(regionFile);

			Point[] region = new Point[MAXREGIONS];
			for (int i=0 ; i<MAXREGIONS ; i++)	{
				region[i] = new Point();
				region[i].x = ois.readShort();;
				region[i].y = ois.readShort();;
				for (int j=0; j<8 ; j++){
					short n=ois.readShort();
				}
				System.out.println(region[i].toString());
			}
			ois.close();
			regionFile.close();
		} catch (IOException ex) {
			System.err.println("Could not open the data file: regions.dat.\n");
			return 0;
		}

		/* Initialize cities using random_city_seed. */
//		srand(random_city_seed);	/* Re-seed random number generator.*/
//
//		for ( i=0; i<NUM_CBR; i++ ) {
//			try_again:
//
//			/* FR: Corrected City Random for position of cities in "even" 0-319. */
//			/* FR: Last was limited to 0-255. At least 1/3 of right side of map  */
//			/* FR: is not place any cities/oils. */
//			x= getrandom( 1, MAX_X-2) ;
//			y= getrandom( 1, MAX_Y-2) ;
//			if( 0==validate_point( &x, &y, i ) )
//				 goto try_again;
//
//			/* Got good point.  Create region for it.  */
//			Add_Region( x, y, i ) ;
//		}	/* For each of the NUM_CBR regions. */

		return 1;
	}
}
