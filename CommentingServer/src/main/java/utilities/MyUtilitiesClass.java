package utilities;

import application.Comment;
import application.CommentCode;
import application.Photo;
import application.PhotosRepository;
import application.User;
import application.UsersPopulation;

/**
 * Utilities class to demonstrate the program.
 * @author 170011408
 *
 */
public class MyUtilitiesClass {
  /**
   * Create Initial populations.
   * @param photosRepository to create the photos population.
   * @param usersPopulation to create the users population.
   */
  public static void createInitialPopulations(PhotosRepository photosRepository,
      UsersPopulation usersPopulation) {
    createUsersPopulation(usersPopulation);
    createPhotosPopulation(photosRepository);
    createInitialCommentsPopulation(photosRepository, usersPopulation);
  }
  
  /**
   * Create photos population - creates photos and photo codes.
   * @param photosRepository the photos repository.
   */
  public static void createPhotosPopulation(PhotosRepository photosRepository) {
    int i;
    for (i = 0; i < 3; i++) { //CREATE 10 PHOTOS
      photosRepository.insertNewPhoto(new Photo(Integer.toString(i), "Dimitris"));
    }
    for (i = 3; i < 6; i++) { //CREATE 10 PHOTOS
      photosRepository.insertNewPhoto(new Photo(Integer.toString(i), "James"));
    }
    for (i = 6; i < 8; i++) { //CREATE 10 PHOTOS
      photosRepository.insertNewPhoto(new Photo(Integer.toString(i), "John"));
    }
    for (i = 8; i < 10; i++) { //CREATE 10 PHOTOS
      photosRepository.insertNewPhoto(new Photo(Integer.toString(i), "Rory"));
    }
  }

  /**
   * Create some demo comments.
   * @param photosRepository the photo repository.
   * @param usersPopulation the users population.
   */
  public static void createInitialCommentsPopulation(PhotosRepository photosRepository,
      UsersPopulation usersPopulation) {
    CommentCode commentCode = new CommentCode();
    commentCode.addLevel(1);
    Comment comment = new Comment(commentCode, "0", "Dimitris",
        "18-03-2018 11:28:52", "This photo is very nice");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    comment = new Comment(commentCode, "0", "John", "18-03-2018 11:29:22",
        "Dimitris, This photo is not nice");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("John").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(3);
    comment = new Comment(commentCode, "0", "Dimitris", "18-03-2018 11:32:12",
        "John, you are wrong");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(4);
    comment = new Comment(commentCode, "0", "John", "18-03-2018 11:40:44",
        "Dimitris, why am I wrong?");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("John").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(5);
    comment = new Comment(commentCode, "0", "Dimitris", "18-03-2018 11:45:44",
        "Because, I think, the picture has historic value");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(1);
    comment = new Comment(commentCode, "0", "James", "18-03-2018 11:50:51",
        "The picture may have historic value");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("James").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(2);
    comment = new Comment(commentCode, "0", "James", "18-03-2018 12:50:11",
        "I think it might be seventy years old");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("James").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(3);
    comment = new Comment(commentCode, "0", "Dimitris", "18-03-2018 13:40:02", "I think so too");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(3);
    commentCode.addLevel(1);
    comment = new Comment(commentCode, "0", "John", "18-03-2018 14:40:02",
        "This picture is indeed old");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("John").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(3);
    commentCode.addLevel(2);
    comment = new Comment(commentCode, "0", "John", "18-03-2018 13:40:12", 
        "I still believe its a low quality picture though");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("John").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(3);
    commentCode.addLevel(3);
    comment = new Comment(commentCode, "0", "Dimitris", 
        "18-03-2018 15:40:02", "To that I agree, as well");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(4);
    comment = new Comment(commentCode, "0", "James", "18-03-2018 16:40:19",
        "I also agree to that");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("James").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(2);
    commentCode.addLevel(5);
    comment = new Comment(commentCode, "0", "John", "18-03-2018 17:40:02",
        "Then it seems that we all agree");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("John").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(3);
    commentCode.addLevel(1);
    comment = new Comment(commentCode, "0", "James", "", "Yes");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("James").addComment(comment);
    commentCode = new CommentCode();
    commentCode.addLevel(1);
    commentCode.addLevel(3);
    commentCode.addLevel(2);
    comment = new Comment(commentCode, "0", "Dimitris", "18-03-2018 20:40:02", "Yes");
    photosRepository.returnPhotoByCode("0").addComment(comment);
    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
  }
  
  /**
   * Create Users population.
   * @param usersPopulation the users population.
   */
  public static void createUsersPopulation(UsersPopulation usersPopulation) {
    usersPopulation.addUser(new User("Dimitris", "1234", false));
    usersPopulation.addUser(new User("John", "5678", false));
    usersPopulation.addUser(new User("James", "9101112", false));
    usersPopulation.addUser(new User("Rory", "13141516", false));
    usersPopulation.addUser(new User("Edwin", "17181920", true));
  }
  
  public static void removeInitialPopulations(PhotosRepository photosRepository,
    UsersPopulation usersPopulation) {
	usersPopulation.removeAll();
	photosRepository.removeAll();
  }
}