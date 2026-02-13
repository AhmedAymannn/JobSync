package Ahmed.com.JobSync.Sync;

import Ahmed.com.JobSync.Job.Job;
import Ahmed.com.JobSync.JobPreference.JobPreference;
import Ahmed.com.JobSync.Sync.dtos.JobDto;
import Ahmed.com.JobSync.common.Enums.EmploymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobMapperTest {

    private JobMapper jobMapper;
    private JobPreference testPreference;
    private JobDto testJobDto;

    @BeforeEach
    void setUp() {
        jobMapper = new JobMapper();
        
        testPreference = new JobPreference();
        testPreference.setId(1L);
        
        testJobDto = new JobDto();
        testJobDto.setJobId("12345");
        testJobDto.setEmployerName("Test Company");
        testJobDto.setJobTitle("Software Engineer");
        testJobDto.setJobDescription("Test job description");
        testJobDto.setApplyLink("https://example.com/apply");
        testJobDto.setCity("New York");
        testJobDto.setCountry("USA");
        testJobDto.setPostedAt(LocalDateTime.now());
        testJobDto.setEmploymentType(EmploymentType.FULL_TIME);
    }

    @Test
    void toEntities_WithValidJobDtos_ReturnsJobEntities() {
        List<JobDto> jobDtos = Arrays.asList(testJobDto);
        
        List<Job> result = jobMapper.toEntities(jobDtos, testPreference);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        
        Job mappedJob = result.get(0);
        assertEquals("JSearch", mappedJob.getSource());
        assertEquals("12345", mappedJob.getExternalJobId());
        assertEquals("Software Engineer", mappedJob.getTitle());
        assertEquals("Test Company", mappedJob.getCompanyName());
        assertEquals("New York, USA", mappedJob.getLocation());
        assertEquals("https://example.com/apply", mappedJob.getJobUrl());
        assertEquals("Test job description", mappedJob.getDescription());
        assertEquals(testJobDto.getPostedAt(), mappedJob.getPostedAt());
        assertEquals(EmploymentType.FULL_TIME, mappedJob.getEmploymentType());
        assertEquals(testPreference, mappedJob.getJobPreference());
    }

    @Test
    void toEntities_WithNullJobDtos_ReturnsEmptyList() {
        List<Job> result = jobMapper.toEntities(null, testPreference);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toEntities_WithEmptyJobDtos_ReturnsEmptyList() {
        List<Job> result = jobMapper.toEntities(Arrays.asList(), testPreference);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toEntities_WithNullJobDto_ReturnsNull() {
        List<JobDto> jobDtos = Arrays.asList(testJobDto, null);
        
        List<Job> result = jobMapper.toEntities(jobDtos, testPreference);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNotNull(result.get(0));
        assertNull(result.get(0).getTitle()); // Should be null for null DTO
    }

    @Test
    void toEntities_WithMultipleJobDtos_ReturnsCorrectMapping() {
        JobDto jobDto2 = new JobDto();
        jobDto2.setJobId("67890");
        jobDto2.setEmployerName("Another Company");
        jobDto2.setJobTitle("Senior Developer");
        
        List<JobDto> jobDtos = Arrays.asList(testJobDto, jobDto2);
        
        List<Job> result = jobMapper.toEntities(jobDtos, testPreference);
        
        assertNotNull(result);
        assertEquals(2, result.size());
        
        Job job1 = result.get(0);
        Job job2 = result.get(1);
        
        assertEquals("12345", job1.getExternalJobId());
        assertEquals("Software Engineer", job1.getTitle());
        assertEquals("Test Company", job1.getCompanyName());
        
        assertEquals("67890", job2.getExternalJobId());
        assertEquals("Senior Developer", job2.getTitle());
        assertEquals("Another Company", job2.getCompanyName());
        
        // Both should have same preference reference
        assertEquals(testPreference, job1.getJobPreference());
        assertEquals(testPreference, job2.getJobPreference());
    }

    @Test
    void toEntities_WithNullCity_CreatesRemoteLocation() {
        testJobDto.setCity(null);
        testJobDto.setCountry(null);
        
        List<Job> result = jobMapper.toEntities(Arrays.asList(testJobDto), testPreference);
        
        Job mappedJob = result.get(0);
        assertEquals("Remote", mappedJob.getLocation());
    }

    @Test
    void toEntities_WithCityOnly_CreatesCityOnlyLocation() {
        testJobDto.setCity("New York");
        testJobDto.setCountry(null);
        
        List<Job> result = jobMapper.toEntities(Arrays.asList(testJobDto), testPreference);
        
        Job mappedJob = result.get(0);
        assertEquals("New York", mappedJob.getLocation());
    }

    @Test
    void toEntities_WithCityAndCountry_CreatesCombinedLocation() {
        testJobDto.setCity("New York");
        testJobDto.setCountry("USA");
        
        List<Job> result = jobMapper.toEntities(Arrays.asList(testJobDto), testPreference);
        
        Job mappedJob = result.get(0);
        assertEquals("New York, USA", mappedJob.getLocation());
    }
}
