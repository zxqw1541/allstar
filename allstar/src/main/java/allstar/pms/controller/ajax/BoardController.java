package allstar.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Board;
import allstar.pms.domain.Event;
import allstar.pms.service.BoardService;
import allstar.pms.service.EventService;

@Controller("ajax.BoardController")
@RequestMapping("/board/ajax/*")
public class BoardController { 
  
  //public static final String SAVED_DIR = "/attachfile";
  public static Logger log = Logger.getLogger(BoardController.class);
  @Autowired BoardService boardService;
  @Autowired EventService eventService;
  @Autowired ServletContext servletContext;
  
  
  
  @RequestMapping("all")
  public AjaxResult countAll(
      @RequestParam(defaultValue = "null") String event,
      @RequestParam(defaultValue = "null") String date,
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2) {
    
    int count = boardService.countAllBoard(event, date, search1, search2);
    
    return new AjaxResult("success",count);
    }

  
  
  
  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue = "null") String event,
      @RequestParam(defaultValue = "null") String date,
      @RequestParam(defaultValue = "null") String reply,
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2,
      @RequestParam(defaultValue="-1") int eno) {
    
    System.out.println("pageNo=" + pageNo);
    System.out.println("pageSize=" + pageSize);
    List<Event> events = eventService.getEventList();
    List<Board> boards = null;
    
    if (eno == -1)
      boards = boardService.getBoardList(pageNo, pageSize, event, date, reply, search1, search2);
    else 
      boards = boardService.getBoardList(pageNo, pageSize, eno, event, date, reply, search1, search2);
    
    System.out.println("--------------------------------------------------");
    for(Board b: boards)
      System.out.println(b);
    System.out.println("--------------------------------------------------");
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("boards", boards);
    resultMap.put("events", events);
    
    return resultMap;
  }
  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }
      
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(Board board) throws Exception {
    log.info("ajax/add");
    log.info(board);
    boardService.register(board);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("detail")
  public AjaxResult detail(int no) throws Exception {
    Board board = boardService.retrieve(no);
    log.info("ajax/detail:" + board);
    return new AjaxResult("success", board);
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(Board board) throws Exception {
    System.out.println("---------------------------" );
    System.out.println("update: " + board);
    System.out.println("---------------------------" );
    if (boardService.change(board) <= 0)
      return new AjaxResult("failure", null);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value = "delete", method = RequestMethod.GET)
  public AjaxResult delete(int no) throws Exception {
    if (boardService.remove(no) <= 0) {
      return new AjaxResult("failure", null);
    } 

    return new AjaxResult("success", null);
  }
}
