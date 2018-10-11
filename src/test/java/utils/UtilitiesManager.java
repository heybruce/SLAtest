package utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import datamodel.TestResult;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UtilitiesManager {

    private static Configurations configs = new Configurations();
    private static PropertiesConfiguration propConfig = null;

    public static Configuration setPropertiesFile(String fileName) {
        try {
            FileBasedConfigurationBuilder.setDefaultEncoding(PropertiesConfiguration.class, "UTF-8");

            propConfig = configs.properties(UtilitiesManager.class.getClassLoader().getResource(fileName));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return propConfig;
    }

    public static long getCurrentUnixTime() {
        Date date = new Date();
        return date.getTime()/1000;
    }

    public static String getCurrentDataTimeString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getSystem () {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Windows";
        } else if (os.contains("nux") || os.contains("nix")) {
            return "Linux";
        }else if (os.contains("mac")) {
            return "Mac";
        }else if (os.contains("sunos")) {
            return "Solaris";
        }else {
            return "Other";
        }
    }

    public static void createDirectory(String dirPath) {
        Path path = Paths.get(dirPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void videoEncoding(String source, String destination) {
        try {
            String line;
            Process p = Runtime.getRuntime().exec(new String[]{"ffmpeg", "-i", source, "-pix_fmt", "yuv420p", destination});
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
            System.out.println("Video converted successfully!");
            in.close();
            deleteFile(source);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.deleteIfExists(path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static double getDifferencePercent(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        if (width != width2 || height != height2) {
            throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)"
                    , width, height, width2, height2));
        }

        long diff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        long maxDiff = 3L * 255 * width * height;

        return 100.0 * diff / maxDiff;
    }

    private static int pixelDiff(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }

    public static void unZip(String zipFile, String outputFolder){
        byte[] buffer = new byte[1024];
        try{
            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }
            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null) {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("Done");

        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JSR310Module());

        String outputFolder = System.getProperty("user.dir") + "/target/testresults/";
        try {
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }
            mapper.writeValue(new File(outputFolder + "/" + getCurrentUnixTime() + ".json"), object);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
