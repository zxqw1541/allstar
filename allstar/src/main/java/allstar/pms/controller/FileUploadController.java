package allstar.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import allstar.pms.util.MultipartHelper;

@Controller
@RequestMapping("/file/*")
public class FileUploadController {
  
  @Autowired ServletContext servletContext;
  
  @RequestMapping("add")
  public Object add(MultipartFile file) throws IllegalStateException, IOException {
    System.out.println(file.getOriginalFilename());
    String fileName = MultipartHelper.generateFilename(file.getOriginalFilename());
    System.out.println(fileName);
    System.out.println(servletContext.getRealPath("/file") + "\\" + fileName);    
    File f = new File(servletContext.getRealPath("/file") + "\\" + fileName);
    file.transferTo(f);
 
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    return resultMap;
  }

}
