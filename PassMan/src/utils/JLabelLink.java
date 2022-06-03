package utils;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JLabelLink extends JLabel{

    private String text="";
    private String TextLink=null;
    private URI uri;

    public JLabelLink(){
        super();
        this.setCursor(Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));
        this.setPreferredSize( new Dimension(34,14) );
        this.setVisible(true);
        addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    Abrir_URL(uri);
                }
            @Override
                public void mouseEntered(MouseEvent e) {
                    setText(text,false);
                }
            @Override
                public void mouseExited(MouseEvent e) {
                    setText(text,true); repaint();
                }
        });

    }


    public void setLink( String link )
    {        
        try {
            uri = new URI(link);
        } catch (URISyntaxException e) {
        	JOptionPane.showMessageDialog(null, "Link Incorrecto");
        }
    }


    public void setTextLink( String texto )
    {
        this.TextLink = texto;
    }


    @Override
    public void setText( String value ){                
        setText(  value ,false );
    }



    public String getTextSinFormato(){
        return text;
    }

/**
 * Formato para el texto
 * @param text
 * @param inout
 */
    private void setText(String text, boolean inout){
        String css = "<style type='text/css'>"
                + ".link {text-decoration: none;color:rgb(255,0,0);}"
                + ".link_hover{color:rgb(255,0,0);text-decoration:underline;}"
                + "</style>";

        String clase = (inout)? "link":"link_hover";
        String html_text = (TextLink!=null)?text.replace(TextLink, "<span class='"+clase+"' >" +TextLink + "</span>"):text;        
        super.setText("<html>"+ css +"<span>"+ html_text + "<span/></html>");

        this.text = text;
    }

/**
 * Abre enlace en navegador predeterminado
 * @param uri
 */
    private void Abrir_URL(URI uri) {
      if (Desktop.isDesktopSupported()) {
          Desktop desktop = Desktop.getDesktop();
          try {
             desktop.browse(uri);
          } catch (IOException e) {
             System.err.println("Error: No se pudo abrir el enlace" + e.getMessage() );
          }
      } else {
          System.err.println("Error: No se puede abrir enlaces web.");
      }
    }

}