package LakeLight.bankSystem.controller;

import LakeLight.bankSystem.domain.Account;
import LakeLight.bankSystem.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.init();
    }

    @Component
    static class InitService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Member member = new Member("man1", "11-11", "010-3364");
            Member member2 = new Member("man2", "22-22", "010-3384");

            em.persist(member);
            em.persist(member2);
        }
    }
}
