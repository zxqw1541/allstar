package allstar.pms.service.support;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.MemberDao;
import allstar.pms.domain.Member;
import allstar.pms.service.MemberService;

@Service
public class DefaultMemberService implements MemberService{
  @Autowired MemberDao memberDao;
  
  public  List<Member> getMemberList(int pageNo, int pageSize, String keyword, String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
  
    return memberDao.selectList(paramMap);
  }
  
  public  void register (Member member) {
    memberDao.insert(member);
  }

  public  void remove(String id) {
    memberDao.delete(id);
  }

  public  void change (Member member) {
    memberDao.update(member);
  }

  public  Member retrieve (String id) {
    return memberDao.selectOne(id);
  }

  public  Member retrieve (String id, String pwd){
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("pwd", pwd);
    
    return memberDao.selectOneByIdPassword (paramMap);
  }
}


