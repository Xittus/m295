package ch.m295.todorganizer.member;

import ch.m295.todorganizer.base.MessageResponse;
import ch.m295.todorganizer.member.Member;
import ch.m295.todorganizer.member.MemberRepository;
import ch.m295.todorganizer.storage.EntityNotFoundException;

import java.util.List;

public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> getMembers() {
        return repository.findByOrderByNameAsc();
    }

    public Member getMember(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Member.class));
    }

    public Member insertMember(Member member) {
        return repository.save(member);
    }

    public Member updateMember(Member member, Long id) {
        return repository.findById(id)
                .map(memberOrig -> {
                    memberOrig.setName(member.getName());
                    return repository.save(memberOrig);
                })
                .orElseGet(() -> repository.save(member));
    }

    public MessageResponse deleteMember(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Member " + id + " deleted");
    }
}
