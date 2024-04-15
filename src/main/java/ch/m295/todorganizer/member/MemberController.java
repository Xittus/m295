package ch.m295.todorganizer.member;

import ch.m295.todorganizer.base.MessageResponse;
import ch.m295.todorganizer.member.Member;
import ch.m295.todorganizer.member.MemberService;
import ch.m295.todorganizer.security.Roles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MemberController {
    private final MemberService memberService;

    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("api/member")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Member>> all() {
        List<Member> result = memberService.getMembers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/member/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Member> one(@PathVariable Long id) {
        Member member = memberService.getMember(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("api/member")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Member> newMember(@Valid @RequestBody Member member) {
        Member savedMember = memberService.insertMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    @PutMapping("api/member/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member, @PathVariable Long id) {
        Member savedMember = memberService.updateMember(member, id);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    @DeleteMapping("api/member/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteMember(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memberService.deleteMember(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
