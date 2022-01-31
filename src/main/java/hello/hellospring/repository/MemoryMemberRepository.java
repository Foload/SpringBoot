package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(), member);
         return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
               //멤버에서 가져온 name이 파라미터로 넘어온 name과 같은지
                .findAny(); //같은경우에만 필터링, 찾으면 return (루프를 돌면서)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//store에 있는 value=member를 반환
    }

    public void clearStore(){
        store.clear();
    }
}
