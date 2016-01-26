package allstar.pms.controller.ajax;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.LikeEvent;
import allstar.pms.domain.Member;
import allstar.pms.service.LikeEventService;
import allstar.pms.service.MemberService;
import allstar.pms.util.MultipartHelper;
import net.coobird.thumbnailator.Thumbnails;

@Controller("ajax.MemberController")
@RequestMapping("/member/ajax/*")
public class MemberController {
  public static final String SAVED_DIR = "/attachfile";
  public static Logger log = Logger.getLogger(MemberController.class);
  @Autowired
  MemberService memberService;
  @Autowired
  LikeEventService likeEventService;
  @Autowired
  ServletContext servletContext;

  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, 
      @RequestParam(defaultValue = "desc") String align) throws Exception {

    List<Member> members = memberService.getMemberList(pageNo,pageSize,keyword,align);


    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", members);
    return resultMap;
  }



  @RequestMapping(value = "add", method = RequestMethod.POST)
  public AjaxResult add(Member member) throws Exception {

    memberService.register(member);

    return new AjaxResult("success",null);
  }

  @RequestMapping("detail")
  public Object detail(int mno) throws Exception {
    Member member = memberService.retrieve(mno);
    return new AjaxResult("success", member);
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public AjaxResult update(Member member, MultipartHttpServletRequest uploadedFile) throws Exception {
  
    log.info("member = " + member);
    if(member.getPhoto() == null) {
      Iterator<String> itr =  uploadedFile.getFileNames();
      if(itr.hasNext()) {
        MultipartFile mpf = uploadedFile.getFile(itr.next());
        System.out.println(mpf.getOriginalFilename() +" uploaded!");
        try {
            String path=uploadedFile.getServletContext().getRealPath("/");
            byte[] bytes = mpf.getBytes();
            String sep = System.getProperty("file.separator");
            String fileName = MultipartHelper.generateFilename(mpf.getOriginalFilename());
            String filePath = path+ sep + "member" + sep + "img" + sep;
            File file=new File(filePath + fileName);
            BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
            
            /* Thumbnails */
            Thumbnails.of(new File(filePath + fileName))
            .width(280)
            .outputQuality(0.8)
            .toFile(new File(filePath + "me_" + fileName));
            
            member.setPhoto(fileName);
        } catch (IOException e) {
          e.printStackTrace();
          return new AjaxResult("failure", null);
        }
      }
    }
    
    if (memberService.change(member) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }

  @RequestMapping("delete")
  public AjaxResult delete(
      String id, String pwd) throws Exception {
    if (memberService.remove(id, pwd) <= 0) {
      return new AjaxResult("failure", null);
    }
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("event")
  public AjaxResult likeEvent(
      int mno, int eno) throws Exception {
    System.out.println("mno = "+ mno);
    System.out.println("eno = "+ eno);

    likeEventService.register(new LikeEvent(mno, eno));
    
    return new AjaxResult("success", null);
  }

}
