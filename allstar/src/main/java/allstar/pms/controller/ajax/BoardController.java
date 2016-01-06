package allstar.pms.controller.ajax;

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
import allstar.pms.service.BoardService;

@Controller("ajax.BoardController")
@RequestMapping("/board/ajax/*")
public class BoardController { 
  
  //public static final String SAVED_DIR = "/attachfile";
  public static Logger log = Logger.getLogger(BoardController.class);
  @Autowired BoardService boardService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) throws Exception {
    
    List<Board> boards = boardService.getBoardList(
        pageNo, pageSize, keyword, align);
    
    return new AjaxResult("success", boards);
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
  public AjaxResult update(Board board/*, MultipartFile file*/) throws Exception {
    /*
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }
    */
    
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
