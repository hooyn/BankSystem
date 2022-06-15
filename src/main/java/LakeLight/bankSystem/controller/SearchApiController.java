package LakeLight.bankSystem.controller;

import LakeLight.bankSystem.dto.MemberAccountCondition;
import LakeLight.bankSystem.dto.MemberAccountDto;
import LakeLight.bankSystem.repository.MemberAccountRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchApiController {

    private final MemberAccountRepository memberAccountRepository;

    @PostMapping("/search")
    public Result search(@RequestBody SearchConditionRequest request){
        MemberAccountCondition memberAccountCondition = new MemberAccountCondition(request.getUsername(), request.getAccount_number());
        List<MemberAccountDto> search = memberAccountRepository.search(memberAccountCondition);

        return new Result(true, 200, search, "검색 성공");
    }

    @Data
    static class SearchConditionRequest{
        private String username;
        private String account_number;
    }
}
