package allstar.pms.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class MultipartHelper {
  public static final int CATE_TEAM = 1;
  public static final int CATE_COMPDETAIL_TEAM = 2;
  public static final int CATE_COMPETITION = 3;
  public static final int CATE_MEMBER_COMM = 4;
  
  public static void generateFile(MultipartFile mpf, String path) throws Exception {
      byte[] bytes = mpf.getBytes();
      File file = new File(path);
      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
      stream.write(bytes);
      stream.close();
  }
  
  public static void generateThumbnail(String path, String name, int category) throws Exception {
    switch (category) {
    case CATE_TEAM:
      Thumbnails.of(new File(path + name))
      .width(280)
      .outputQuality(0.8)
      .toFile(new File(path + "tl_" + name));
      break;
    case CATE_COMPDETAIL_TEAM:
      Thumbnails.of(new File(path + name))
      .forceSize(45,45)
      .outputQuality(0.8)
      .toFile(new File(path + "tcd_" + name));
      break;
    case CATE_MEMBER_COMM:
      Thumbnails.of(new File(path + name))
      .forceSize(35,35)
      .outputQuality(0.8)
      .toFile(new File(path + "mc_" + name));
      break;
    }
  }
  
  public static String generateFilename(String originFilename) {
    int dotPos = originFilename.lastIndexOf(".");
    String ext = "";
    if (dotPos != -1) {
      ext = originFilename.substring(dotPos);
    }
    
    return String.format("file-%d-%d%s", 
            System.currentTimeMillis(), count(), ext); 
  }
  
static int count = 0;
  
  synchronized private static int count() {
    if (count > 10000) {
      count = 0;
    }
    return ++count;
  }
}
