package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.Member;

public interface MemberDao {
  
  List<Member> selectList(Map<String,Object> paramMap);

  int insert(Member member);

  int delete(String id);
  
  int update(Member member);

  Member selectOne(String id);

  Member selectOneByIdPassword (Map<String,Object> paramMap);
}


