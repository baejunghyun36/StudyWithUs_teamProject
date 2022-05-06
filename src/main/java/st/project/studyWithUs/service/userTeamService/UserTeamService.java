package st.project.studyWithUs.service.userTeamService;



import org.springframework.stereotype.Service;
import st.project.studyWithUs.domain.UserTeam;

import java.util.List;

@Service
public interface UserTeamService {

    void save(UserTeam userStudyTeam);

    List<UserTeam> findAll();

    void dropStudyTeam(Long tId, Long uId);
}
