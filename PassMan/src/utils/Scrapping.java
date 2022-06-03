package utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scrapping extends Thread {	
	
	/**
	 * Comprueba cambios en el codigo de la web
	 * @param email
	 * @param url
	 */
    public void scrap ( String email, String url, String titulo) {
    	Date myDate = new Date();

    	String fecha = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(myDate);
    	int numero=0;
    	FileWriter fw;
    	File fileAntiguo = null;
    	File fileActual = null;
    	
    		 if (getStatusConnectionCode(url) == 200 ) {
    				
    	            // Obtengo el HTML de la web en un objeto Document
    	            Document document = getHtmlDocument(url);
    				
    	            try {
    					fw = new FileWriter("C:\\txt\\webScrapping\\"+titulo + fecha + ".txt");
    					
    					fw.write(document.toString());
    					numero++;
    					fileActual = new File("C:\\txt\\webScrapping\\" +titulo + fecha + ".txt");
    					if(fileActual.delete()) {
    						System.out.println("GG");
    					}else {
    						System.out.println("Sorryyyyyyy");
    					}
    					fw.close();
    					File directorio = new File("C:\\txt\\webScrapping");
    					fileAntiguo = CompareFiles.buscar(titulo , directorio, fileActual.getName() );
    					
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    	            
    	            try {
    					
    					if(fileAntiguo != null) {
    						CompareFiles.LeerFichero(fileAntiguo, fileActual, email, url);
        					System.out.println("Se han comparado los ficheros");
        					if(fileAntiguo.delete()) {
        						System.out.println("GG");
        					}else {
        						System.out.println("Sorryyyyyyy");
        					}

    					}else {
    						System.out.println("No hay donde comparar");
    					}
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    		 }
    	
    		
    	}

    
 public static int getStatusConnectionCode(String url) {
		
        Response response = null;
		
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepci�n al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }
 
 /**
  * Obtiene el html de la web
  * @param url
  * @return
  */
    public static Document getHtmlDocument(String url) {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepci�n al obtener el HTML de la p�gina" + ex.getMessage());
        }

        return doc;

    }
    
}