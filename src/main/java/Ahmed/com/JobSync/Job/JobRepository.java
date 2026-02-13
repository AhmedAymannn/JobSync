package Ahmed.com.JobSync.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository <Job , Long> {
    boolean existsByExternalJobId(String externalJobId);
}
