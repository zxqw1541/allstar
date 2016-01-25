package allstar.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.BoarComm;
import allstar.pms.service.BoarCommService;

@Controller("ajax.BoarCommController")
@RequestMapping("/boarComm/ajax/*")
public class BoarCommController { 
  
  //public static final String SAVED_DIR = "/attachfile";
  public static Logger log = Logger.getLogger(BoarCommController.class);
  @Autowired BoarCommService boarCommService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("list")
  public Object list(int bno) throws Exception {
    
    List<BoarComm> boarComms = null;
    
    boarComms = boarCommService.getBoarCommListByBoard(bno);
    
    System.out.println("--------------------------------------------------");
    for(BoarComm b: boarComms)
      System.out.println(b);
    System.out.println("--------------------------------------------------");
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("boarComms", boarComms);
    
    return resultMap;
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(BoarComm boarComm) throws Exception {
    log.info("ajax/add");
    log.info(boarComm);
    boarCommService.register(boarComm);
    return new AjaxResult("success", null);
  }
    @RequestMapping(value = "delete", method = RequestMethod.GET)
  public AjaxResult delete(int bcno) throws Exception {
    if (boarCommService.remove(bcno) <= 0) {
      return new AjaxResult("failure", null);
    } 

    return new AjaxResult("success", null);
  }
}
