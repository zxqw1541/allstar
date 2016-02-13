package allstar.pms.controller.ajax;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import allstar.pms.domain.JoinTeam;
import allstar.pms.domain.LikeEvent;
import allstar.pms.domain.Member;
import allstar.pms.domain.Team;
import allstar.pms.service.JoinTeamService;
import allstar.pms.service.LikeEventService;
import allstar.pms.service.MemberService;
import allstar.pms.service.TeamService;
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
  TeamService teamService;
  @Autowired
  JoinTeamService joinTeamService;
  @Autowired
  LikeEventService likeEventService;
  @Autowired
  ServletContext servletContext;

  @RequestMapping("list")
  public Object list(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, @RequestParam(defaultValue = "desc") String align)
          throws Exception {

    List<Member> members = memberService.getMemberList(pageNo, pageSize, keyword, align);

    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", members);
    return resultMap;
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public AjaxResult add(Member member) throws Exception {
    if (memberService.idRedundancyCheck(member.getId()).size() > 0) {
      return new AjaxResult("existID", null);
    }
    
    if (memberService.register(member) <= 0) {
      return new AjaxResult("error", null);
    }
    return new AjaxResult("success", null);
  }

  @RequestMapping("detail")
  public Object detail(int mno) throws Exception {
    List<LikeEvent> likeEvent = likeEventService.getLikeEventListByMno(mno);
    Member member = memberService.retrieve(mno);
    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("likeEvents", likeEvent);
    resultMap.put("members", member);
    log.debug(likeEvent);
    log.debug(member);
    return resultMap;
  }

  @RequestMapping(value = "update", method = RequestMethod.POST)
  public AjaxResult update(String likeList, Member member, MultipartHttpServletRequest uploadedFile) throws Exception {

    if (memberService.emailRedundancyCheck(member.getMno(), member.getEmail()).size() > 0) {
      return new AjaxResult("existEmail", null);
    }
    
    if (memberService.telRedundancyCheck(member.getMno(), member.getTel()).size() > 0) {
      return new AjaxResult("existTel", null);
    }
    
    log.info("member = " + member);
    if (member.getPhoto() == null) {
      Iterator<String> itr = uploadedFile.getFileNames();
      if (itr.hasNext()) {
        MultipartFile mpf = uploadedFile.getFile(itr.next());
        System.out.println(mpf.getOriginalFilename() + " uploaded!");
        try {
          String path = uploadedFile.getServletContext().getRealPath("/");
          byte[] bytes = mpf.getBytes();
          String sep = System.getProperty("file.separator");
          String fileName = MultipartHelper.generateFilename(mpf.getOriginalFilename());
          String filePath = path + sep + "member" + sep + "img" + sep;
          File file = new File(filePath + fileName);
          BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
          stream.write(bytes);
          stream.close();

          /* Thumbnails */
          Thumbnails.of(new File(filePath + fileName)).width(280).outputQuality(0.8)
              .toFile(new File(filePath + "me_" + fileName));

          // 대회 상세 댓글에 섬네일
          MultipartHelper.generateThumbnail(filePath, fileName, MultipartHelper.CATE_MEMBER_COMM);
          member.setPhoto(fileName);
        } catch (IOException e) {
          e.printStackTrace();
          return new AjaxResult("failure", null);
        }
      }
    }
    if (likeList != null) {
      likeEventService.remove(member.getMno());
      
      LikeEvent likeEvent = new LikeEvent();
      List<Integer> likes = new ArrayList<>();
      likeList = likeList.substring(1, likeList.length() - 1);
      System.out.println(likeList);
      for (String likeToken : likeList.split(",")) {
        if (likeToken.equals("null"))
          continue;
        likes.add(Integer.parseInt(likeToken.substring(1, likeToken.length() - 1)));
      }
      for (int result : likes) {
        likeEvent.setMno(member.getMno());
        likeEvent.setEno(result);
        likeEventService.register(likeEvent);
      }
    }

    if (memberService.change(member) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }

  @RequestMapping("delete")
  public AjaxResult delete(String id, String pwd) throws Exception {
    if (memberService.remove(id, pwd) <= 0) {
      return new AjaxResult("failure", null);
    }

    return new AjaxResult("success", null);
  }

  @RequestMapping("event")
  public AjaxResult likeEvent(int mno, int eno) throws Exception {
    System.out.println("mno = " + mno);
    System.out.println("eno = " + eno);

    LikeEvent lk = new LikeEvent();
    lk.setEno(eno);
    lk.setMno(mno);
    likeEventService.register(lk);

    return new AjaxResult("success", null);
  }

  @RequestMapping(value = "joint", method = RequestMethod.POST)
  public AjaxResult joinTeamList(JoinTeam joinTeam) {
    Team team = teamService.retrieve(joinTeam.getTno());

    if (joinTeamService.retrieve(joinTeam) != null) {
      return new AjaxResult("already", null);
    }

    if (team.getJoinNum() == team.getTotalNum()) {
      return new AjaxResult("full", null);
    }

    try {
      joinTeamService.register(joinTeam);
      /*teamService.changeJoinCount(joinTeam.getTno());*/
    } catch (Exception e) {
      return new AjaxResult("failure", null);
    }

    return new AjaxResult("success", null);
  }

  @RequestMapping("teamJoin")
  public Object getJoinTeamT(int tno) {
    List<JoinTeam> joinTeams = joinTeamService.getJoinTeamByTeam(tno);

    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", joinTeams);
    return resultMap;
  }

  @RequestMapping("changeState")
  public AjaxResult changeState(JoinTeam joinTeam) {
    log.debug(joinTeam);
    joinTeamService.changeState(joinTeam);
    if (joinTeam.getState() == 1) {
      teamService.changeJoinCount(joinTeam.getTno());
    }

    return new AjaxResult("success", null);
  }

  @RequestMapping("memberJoin")
  public Object getJoinTeam(int mno) {
    System.out.println("memberjoin : " + mno);
    List<JoinTeam> joinTeams = joinTeamService.getJoinTeamByMember(mno);

    System.out.println("-----------------------------------------------------------------");
    for (JoinTeam jt : joinTeams)
      System.out.println(jt);
    System.out.println("-----------------------------------------------------------------");
    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", joinTeams);
    return resultMap;
  }

  @RequestMapping("memberJoined")
  public Object getJoinedTeam(int mno) {
    List<JoinTeam> joinTeams = joinTeamService.getJoinTeamByMember(mno);

    System.out.println("-----------------------------------------------------------------");
    for (JoinTeam jt : joinTeams) {
      List<JoinTeam> captain = joinTeamService.getCaptainTeamByMember(jt.getTno());
      for (JoinTeam ct : captain)
        jt.setMember(ct.getMember());
    }
    System.out.println("-----------------------------------------------------------------");

    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", joinTeams);
    return resultMap;
  }

  @RequestMapping("likeEvent")
  public Object getLikeEventListByMno(int mno) {

    List<LikeEvent> likeEvents = likeEventService.getLikeEventListByMno(mno);
    log.debug(likeEvents);
    List<Integer> likes = new ArrayList<>();
    
    for (int i = 0; i <likeEvents.size(); i++) {
      likes.add(likeEvents.get(i).getEno());
      System.out.println(likes.get(i));
    }
    System.out.println(likes);
    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", likes);
    return resultMap;
  }

}
