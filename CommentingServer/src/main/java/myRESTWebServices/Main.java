package myRESTWebServices;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import application.PhotosRepository;
import application.UsersPopulation;
import utilities.MyUtilitiesClass;

import java.io.IOException;
import java.net.URI;

/**
 * Starts the grizzly http server.
 * @author 170011408
 *
 */
public class Main {
  // Base URI the Grizzly HTTP server will listen on
  public static final String BASE_URI = "http://localhost:8080/myphotocommentingapp/";

  /**
   * Starts the Grizzly HTTP server.
   * @return The base URI.
   */
  public static HttpServer startServer() {
    PhotosRepository photosRepository = new PhotosRepository();
    UsersPopulation usersPopulation = new UsersPopulation();
    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
    final ResourceConfig rc = new ResourceConfig().packages("myRESTWebServices");
    // create and start a new instance of grizzly http server
    // exposing the Jersey application at BASE_URI
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
  }


  /**
   * The main method of the server package.
   * @param args command line arguments (not needed here).
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    final HttpServer server = startServer();
    System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
    System.in.read();
    server.stop();
  }
}

