package allstar.pms.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import allstar.pms.dao.FreeBoardDao;
import allstar.pms.domain.FreeBoard;

@Controller
@RequestMapping("/freeBoard/*")
public class FreeBoardController {
  public static Logger log = Logger.getLogger(FreeBoardController.class);
  @Autowired FreeBoardDao freeBoardDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      Model model) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    List<FreeBoard> freeBoards = freeBoardDao.selectList(paramMap);
    
    model.addAttribute("freeBoards", freeBoards);
    
    return "freeboard/FreeBoardList";
  }
  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "freeboard/FreeBoardForm";
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public String add(FreeBoard freeBoard) throws Exception{
    
    freeBoardDao.insert(freeBoard);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("detail")
  public String detail(int no,Model model) throws Exception {
    FreeBoard freeBoard = freeBoardDao.selectOne(no);
    model.addAttribute("freeBoard", freeBoard);
    return "freeboard/FreeBoardDetail";
  }
  
  @RequestMapping(value="update", method=RequestMethod.POST)
  public String update(HttpServletRequest request,
      Model model) throws Exception {
    
    FreeBoard freeBoard = new FreeBoard();
    freeBoard.setNo(Integer.parseInt(request.getParameter("no")));
    freeBoard.setTitle(request.getParameter("title"));
    freeBoard.setContent(request.getParameter("content"));
    
    if (freeBoardDao.update(freeBoard) <= 0) {
      model.addAttribute("errorCode", "401");
      return "freeboard/FreeBoardAuthError";
    }
    return "redirect:list.do";
  }
  
  @RequestMapping("delete.do")
  public String delete(int no, String password,
      Model model) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    log.debug(paramMap.put("no", no)); 
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    if (freeBoardDao.delete(paramMap) <= 0) {
      model.addAttribute("errorCode", "401");
      return "freeboard/FreeBoardAuthError";
    }
    return "redirect:list.do";
  }
  
}
