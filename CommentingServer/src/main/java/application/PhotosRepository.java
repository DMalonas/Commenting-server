package application;

import java.util.ArrayList;
import java.util.Iterator;

public class PhotosRepository {
	
	public static ArrayList<Photo> photos;
	
	public PhotosRepository() {
		photos = new ArrayList<Photo>();
	}
	
	
	public void insertNewPhote(Photo photo) {
		photos.add(photo);
	}
	
	
	public static boolean photoExists(Photo photo) {
		Iterator it = photos.iterator();
		while(it.hasNext()) {
			if (it.equals(photo)) {
				return true;
			}
		}
		return false;
	}
}
