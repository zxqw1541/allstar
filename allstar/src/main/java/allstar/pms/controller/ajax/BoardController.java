package allstar.pms.controller.ajax;

import java.util.ArrayList;
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
import allstar.pms.service.BoarCommService;
import allstar.pms.service.BoardService;
import allstar.pms.service.EventService;

@Controller("ajax.BoardController")
@RequestMapping("/board/ajax/*")
public class BoardController { 
  
  //public static final String SAVED_DIR = "/attachfile";
  public static Logger log = Logger.getLogger(BoardController.class);
  @Autowired BoardService boardService;
  @Autowired EventService eventService;
  @Autowired BoarCommService boarCommService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("pn")
  public Object pnList(int bno) {
    List<Board> boards = boardService.getBnoList();
    
    int index = -1;
    int boardSize = boards.size();
    Board board = null;
    // 해당 게시글 인덱스 찾기
    for(int i = 0; i < boardSize; i++) {
      board = boards.get(i);
      if(bno == board.getNo()) {
        index = i;
        break;
      }
    }
    HashMap<String,Object> resultMap = new HashMap<>();
    // 게시글이 하나 일 때
    if(boardSize == 1) {
      resultMap.put("status", "success");
      resultMap.put("next", null);
      resultMap.put("prev", null);
      return resultMap;
    } 
    
    // 이전 게시글이 없을때
    if(index == 0) {
      resultMap.put("next", boards.get(index + 1));
      resultMap.put("prev", null);
    } 
    // 다음 게시글이 없을때
    else if(index == boardSize -1) {
      resultMap.put("next", null);
      resultMap.put("prev", boards.get(index - 1));
    } 
    // 이전 다음 게시글이 모두 있을 때
    else {
      resultMap.put("next", boards.get(index + 1));
      resultMap.put("prev", boards.get(index - 1));
    }
    resultMap.put("status", "success");
    return resultMap;
  }
  
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
      @RequestParam(defaultValue = "no") String keyword, 
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue = "null") String event,
      @RequestParam(defaultValue = "null") String date,
      @RequestParam(defaultValue = "null") String reply,
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2,
      @RequestParam(defaultValue="-1") int eno) {
    
    List<Event> events = eventService.getEventList();
    List<Board> boards = null;
    List<Integer> boarComment = new ArrayList<>();
    System.out.println("pageNo=" + pageNo);
    System.out.println("pageSize=" + pageSize);
    if (eno == -1)
      boards = boardService.getBoardList(keyword, pageNo, pageSize, event, date, reply, search1, search2);
    else 
      boards = boardService.getBoardList(keyword, pageNo, pageSize, eno, event, date, reply, search1, search2);
    
    for(Board board: boards){
       boarComment.add(boarCommService.countAllCommFromBoard(board.getNo()));
    }
    System.out.println(boarComment);
        System.out.println("--------------------------------------------------");
    for(Board b: boards)
      System.out.println(b);
    System.out.println("--------------------------------------------------");
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("boards", boards);
    resultMap.put("events", events);
    resultMap.put("comm", boarComment);
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
  
  @RequestMapping("views")
  public AjaxResult getViews(int no) throws Exception {
    log.info(no);
    return new AjaxResult("success", boardService.upView(no));
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
