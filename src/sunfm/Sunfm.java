/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunfm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author testing
 */
public class Sunfm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputLine;
        String html = "";
        String topList = "";
        try {

            URL sunfm = new URL("http://www.sunfm.lk/");
            BufferedReader in = new BufferedReader(new InputStreamReader(sunfm.openStream()));
            while ((inputLine = in.readLine()) != null) {
                html += inputLine;
            }
            Document doc = Jsoup.parse(html);
            Elements list = doc.select("div#linkslefttop");
            for (Element item : list) {
                //  System.out.print(item.select("div#playlistarttitle").text() + " : " + item.select("div#playlistpara").text() + "\n");
                topList += item.select("div#playlistarttitle").text() + " : " + item.select("div#playlistpara").text() + "\n";
            }

            File file = new File("sunfmTop40.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(topList);
            bw.close();
            in.close();
            System.out.println("Done");

        } catch (MalformedURLException ex) {
            Logger.getLogger(Sunfm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sunfm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
