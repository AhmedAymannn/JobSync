package Ahmed.com.JobSync.Sync;

import Ahmed.com.JobSync.Job.Job;
import Ahmed.com.JobSync.Job.JobService;
import Ahmed.com.JobSync.JobPreference.JobPreference;
import Ahmed.com.JobSync.JobPreference.JobPreferenceService;
import Ahmed.com.JobSync.Sync.dtos.JSearchResponseDto;
import Ahmed.com.JobSync.Sync.dtos.JobDto;
import Ahmed.com.JobSync.Sync.dtos.JobQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobSyncServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobPreferenceService jobPreferenceService;

    @Mock
    private JSearchClient jSearchClient;

    @Mock
    private JobMapper jobMapper;

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobSyncService jobSyncService;

    private JobPreference jobPreference;
    private Job job;

    @BeforeEach
    void setUp() {
        jobPreference = new JobPreference();
        jobPreference.setId(1L);
        jobPreference.setJob_title("Software Engineer");
        jobPreference.setLocation("Remote");
        jobPreference.setIsRemote(true);

        job = new Job();
        job.setId(1L);
        job.setTitle("Software Engineer");
        job.setCompanyName("Test Company");
        job.setLocation("Remote");
    }

    @Test
    void syncJobs_WithValidUserId() {
        JSearchResponseDto responseDto = new JSearchResponseDto();
        responseDto.setStatus("OK");
        responseDto.setData(Arrays.asList(new JobDto()));
        
        when(jobPreferenceService.getUserPrefByUserId(anyLong())).thenReturn(jobPreference);
        when(jSearchClient.fetchRawJobs(any(JobQuery.class))).thenReturn(responseDto);
        when(jobMapper.toEntities(any(List.class), any(JobPreference.class))).thenReturn(Arrays.asList(job));
        when(jobService.existsByExternalJobId(anyString())).thenReturn(false);
        when(jobService.saveAll(any(List.class))).thenReturn(Arrays.asList(job));

        jobSyncService.syncJobs(1L);

        verify(jobPreferenceService).getUserPrefByUserId(1L);
        verify(jSearchClient).fetchRawJobs(any(JobQuery.class));
        verify(jobMapper).toEntities(any(List.class), eq(jobPreference));
        verify(jobService).existsByExternalJobId(anyString());
        verify(jobService).saveAll(Arrays.asList(job));
    }

    @Test
    void syncJobs_UserHasNoPreferences() {
        when(jobPreferenceService.getUserPrefByUserId(anyLong())).thenReturn(null);

        jobSyncService.syncJobs(1L);

        verify(jobPreferenceService).getUserPrefByUserId(1L);
        verify(jSearchClient, never()).fetchRawJobs(any(JobQuery.class));
        verify(jobMapper, never()).toEntities(any(List.class), any(JobPreference.class));
        verify(jobService, never()).existsByExternalJobId(anyString());
        verify(jobService, never()).saveAll(any(List.class));
    }

    @Test
    void syncJobs_ApiCallFails() {
        when(jobPreferenceService.getUserPrefByUserId(anyLong())).thenReturn(jobPreference);
        when(jSearchClient.fetchRawJobs(any(JobQuery.class))).thenThrow(new RuntimeException("API Error"));

        assertThrows(RuntimeException.class, () -> jobSyncService.syncJobs(1L));

        verify(jobPreferenceService).getUserPrefByUserId(1L);
        verify(jSearchClient).fetchRawJobs(any(JobQuery.class));
        verify(jobMapper, never()).toEntities(any(List.class), any(JobPreference.class));
        verify(jobService, never()).existsByExternalJobId(anyString());
        verify(jobService, never()).saveAll(any(List.class));
    }

    @Test
    void syncJobs_EmptyApiResponse() {
        JSearchResponseDto responseDto = new JSearchResponseDto();
        responseDto.setStatus("OK");
        responseDto.setData(Arrays.asList());
        
        when(jobPreferenceService.getUserPrefByUserId(anyLong())).thenReturn(jobPreference);
        when(jSearchClient.fetchRawJobs(any(JobQuery.class))).thenReturn(responseDto);
        when(jobMapper.toEntities(any(List.class), any(JobPreference.class))).thenReturn(Arrays.asList());

        jobSyncService.syncJobs(1L);

        verify(jobPreferenceService).getUserPrefByUserId(1L);
        verify(jSearchClient).fetchRawJobs(any(JobQuery.class));
        verify(jobMapper).toEntities(Arrays.asList(), jobPreference);
        verify(jobService, never()).existsByExternalJobId(anyString());
        verify(jobService, never()).saveAll(any(List.class));
    }

    @Test
    void syncJobs_WithExistingJobs() {
        JSearchResponseDto responseDto = new JSearchResponseDto();
        responseDto.setStatus("OK");
        responseDto.setData(Arrays.asList(new JobDto()));
        
        when(jobPreferenceService.getUserPrefByUserId(anyLong())).thenReturn(jobPreference);
        when(jSearchClient.fetchRawJobs(any(JobQuery.class))).thenReturn(responseDto);
        when(jobMapper.toEntities(any(List.class), any(JobPreference.class))).thenReturn(Arrays.asList(job));
        when(jobService.existsByExternalJobId(anyString())).thenReturn(true); // Job already exists
        when(jobService.saveAll(any(List.class))).thenReturn(Arrays.asList());

        jobSyncService.syncJobs(1L);

        verify(jobPreferenceService).getUserPrefByUserId(1L);
        verify(jSearchClient).fetchRawJobs(any(JobQuery.class));
        verify(jobMapper).toEntities(any(List.class), eq(jobPreference));
        verify(jobService).existsByExternalJobId(anyString());
        verify(jobService, never()).saveAll(any(List.class)); // Should not save existing jobs
    }
}
