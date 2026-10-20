package com.nisarga.jobportal.repository;
import com.nisarga.jobportal.entity.JobApplication;
import com.nisarga.jobportal.entity.AppUser;
import com.nisarga.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
 boolean existsByApplicantAndJob(AppUser applicant, Job job);
 List<JobApplication> findByApplicantOrderByAppliedAtDesc(AppUser applicant);
 List<JobApplication> findByJobEmployerOrderByAppliedAtDesc(AppUser employer);
}
