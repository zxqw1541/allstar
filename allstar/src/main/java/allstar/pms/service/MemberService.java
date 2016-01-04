package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Member;

public interface MemberService {

  List<Member> getMemberList(int pageNo, int pageSize, String keyword, String align);

  void register(Member member);

  int remove(String id, String pwd);

  int change(Member member);

  Member retrieve(String id);

  Member retrieve(String id, String password);
}
