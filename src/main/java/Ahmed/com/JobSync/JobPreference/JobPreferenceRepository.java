package Ahmed.com.JobSync.JobPreference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPreferenceRepository extends JpaRepository<JobPreference , Long> {
}
