```mermaid
classDiagram
    class Job {
        +Long id
        +String role
        +String company
        +Map~String, String~ jobDetails
        +Long getId()
        +void setId(Long id)
        +String getRole()
        +void setRole(String role)
        +String getCompany()
        +void setCompany(String company)
        +Map~String, String~ getJobDetails()
        +void setJobDetails(Map~String, String~ jobDetails)
    }

    class JobDTO {
        +Map~String, String~ jobDetails
        +Map~String, String~ getJobDetails()
        +void setJobDetails(Map~String, String~ jobDetails)
    }

    class JobRepository {
        <<interface>>
        +List~Job~ findAll()
        +Job save(Job job)
    }

    class JobService {
        -JobRepository jobRepository
        +JobService(JobRepository jobRepository)
        +Job saveJobFromDynamicData(String role, String company, List~Map~String, String~~ details)
        +List~Job~ getAllJobs()
    }

    class JobController {
        -JobService jobService
        +JobController(JobService jobService)
        +ResponseEntity~String~ createJobFromDynamicData(JobDataWrapper jobDataWrapper)
        +List~Job~ getAllJobs()
    }

    class JobDataWrapper {
        +String role
        +String company
        +List~Map~String, String~~ details
    }

    JobController --> JobService
    JobService --> JobRepository
    JobRepository --> Job
    JobController --> JobDataWrapper
    ```