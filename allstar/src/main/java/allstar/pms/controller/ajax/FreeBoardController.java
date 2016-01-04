package allstar.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.dao.FreeBoardDao;
import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.FreeBoard;

@Controller("ajax.FreeBoardController")
@RequestMapping("/freeBoard/ajax/*")
public class FreeBoardController {
  @Autowired FreeBoardDao freeBoardDao;

  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);

    List<FreeBoard> freeBoards = freeBoardDao.selectList(paramMap);

    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", freeBoards);

    return resultMap;
  }

  @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "freeBoard/FreeBoardForm";
  }

  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(FreeBoard freeBoard) throws Exception{

    freeBoardDao.insert(freeBoard);
    return new AjaxResult("success", null);
  }

  @RequestMapping("detail")
  public Object detail(int no) throws Exception {
    FreeBoard freeBoard = freeBoardDao.selectOne(no);
    return new AjaxResult("success", freeBoard);
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(FreeBoard freeBoard) throws Exception {

    if (freeBoardDao.update(freeBoard) <= 0) {
      return new AjaxResult("failure", null);
    }

    return new AjaxResult("success", null);
  }

  @RequestMapping("delete.do")
  public AjaxResult delete(int no, String password) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);

    if (freeBoardDao.delete(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    }

    return new AjaxResult("success", null);
  }

}
